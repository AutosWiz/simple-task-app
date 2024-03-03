package com.example.task.application.task;

import com.example.task.application.task.common.TaskData;
import com.example.task.application.task.create.TaskCreateCommand;
import com.example.task.application.task.create.TaskCreateResult;
import com.example.task.application.task.delete.TaskDeleteCommand;
import com.example.task.application.task.get.TaskGetCommand;
import com.example.task.application.task.get.TaskGetResult;
import com.example.task.application.task.getall.TaskGetAllResult;
import com.example.task.application.task.getbyuserId.TaskGetByUserIdCommand;
import com.example.task.application.task.getbyuserId.TaskGetByUserIdResult;
import com.example.task.application.task.transition.TaskTransitionCommand;
import com.example.task.application.task.update.TaskUpdateCommand;
import com.example.task.domain.models.task.*;
import com.example.task.domain.models.user.UserId;
import com.example.task.domain.models.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TaskApplicationService {
    private final TaskFactory taskFactory;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @PostAuthorize("returnObject.task.userId == authentication.principal.userId")
    public TaskGetResult get(TaskGetCommand command) {
        var id = new TaskId(command.getTaskId());
        var task = taskRepository.findById(id);
        if (task.isEmpty()) throw new RuntimeException("タスクが見つかりません。");

        var data = TaskData.toData(task.get());

        return new TaskGetResult(data);
    }

    public TaskGetAllResult getAll() {
        var tasks = taskRepository.findAll();
        var taskDataList = tasks.stream()
                .map(TaskData::toData)
                .toList();
        return new TaskGetAllResult(taskDataList);
    }

    public TaskGetByUserIdResult getByUserId(TaskGetByUserIdCommand command) {
        var userId = new UserId(command.getUserId());
        var tasks = taskRepository.findByUserId(userId);
        var taskDataList = tasks.stream()
                .map(TaskData::toData)
                .toList();
        return new TaskGetByUserIdResult(taskDataList);
    }

    @Transactional
    public TaskCreateResult create(TaskCreateCommand command) {
        var userId = new UserId(command.getUserId());
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません。"));

        var title = new TaskTitle(command.getTitle());
        var description = new TaskDescription(command.getDescription());
        var task = taskFactory.create(title, description, command.getDueDate(), userId);

        taskRepository.save(task);

        return new TaskCreateResult(task.getTaskId().getValue());
    }

    @Transactional
    public void update(TaskUpdateCommand command) {
        var userId = new UserId(command.getUserId());
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません。"));

        var id = new TaskId(command.getTaskId());
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません。"));

        if (!userId.equals(task.getUserId())) {
            throw new RuntimeException("タスクを更新できません。");
        }

        var title = new TaskTitle(command.getTitle());
        task.changeTitle(title);

        var description = new TaskDescription(command.getDescription());
        task.changeDescription(description);

        task.changeDueDate(command.getDueDate());

        taskRepository.save(task);
    }

    @Transactional
    public void transition(TaskTransitionCommand command) {
        var id = new TaskId(command.getTaskId());
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません。"));

        var userId = new UserId(command.getUserId());

        if (!userId.equals(task.getUserId())) {
            throw new RuntimeException("タスクを更新できません。");
        }

        task.changeTaskStatus(command.getStatus());

        taskRepository.save(task);
    }

    @Transactional
    public void delete(TaskDeleteCommand command) {
        var id = new TaskId(command.getTaskId());
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません。"));

        var userId = new UserId(command.getUserId());
        if (!userId.equals(task.getUserId())) {
            throw new RuntimeException("タスクを削除できません。");
        }

        taskRepository.delete(task);
    }
}
