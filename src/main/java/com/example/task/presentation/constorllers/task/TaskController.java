package com.example.task.presentation.constorllers.task;

import com.example.task.application.login.LoginUser;
import com.example.task.application.task.TaskApplicationService;
import com.example.task.application.task.create.TaskCreateCommand;
import com.example.task.application.task.delete.TaskDeleteCommand;
import com.example.task.application.task.get.TaskGetCommand;
import com.example.task.application.task.getbyuserId.TaskGetByUserIdCommand;
import com.example.task.application.task.transition.TaskTransitionCommand;
import com.example.task.application.task.update.TaskUpdateCommand;
import com.example.task.domain.models.task.TaskStatus;
import com.example.task.domain.models.task.TaskStatusTransitions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskApplicationService taskApplicationService;

    @ModelAttribute("taskForm")
    public TaskForm taskForm() {
        return new TaskForm();
    }

    @GetMapping
    public String getByUserId(@AuthenticationPrincipal LoginUser loginUser, Model model) {
        int userId = loginUser.getUserId();
        var command = new TaskGetByUserIdCommand(userId);
        var result = taskApplicationService.getByUserId(command);
        model.addAttribute("tasks", result.getTasks());
        return "task/find";
    }

    @GetMapping("/{id}")
    public String get(@AuthenticationPrincipal LoginUser loginUser,
                      @PathVariable("id") Integer id,
                      Model model) {
        var userId = loginUser.getUserId();
        var command = new TaskGetCommand(id);
        var task = taskApplicationService.get(command).getTask();
        model.addAttribute("task", task);

        var allowedStatus = new TaskStatusTransitions().getAllowedStatus(task.getTaskStatus());
        allowedStatus.add(task.getTaskStatus());
        model.addAttribute("taskStatusSet", allowedStatus);
        return "task/show";
    }

    @GetMapping("/new")
    public String create(TaskForm taskForm, Model model) {
        return "task/new";
    }

    @PostMapping("/new")
    public String create(@AuthenticationPrincipal LoginUser loginUser,
                         @Validated TaskForm taskForm,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "task/new";
        }

        int userId = loginUser.getUserId();
        var command = new TaskCreateCommand(
                taskForm.getTaskTitle(),
                taskForm.getTaskDescription(),
                userId,
                taskForm.getDueDate()
        );
        taskApplicationService.create(command);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, TaskForm taskForm, Model model) {
        var command = new TaskGetCommand(id);
        var task = taskApplicationService.get(command).getTask();
        taskForm.setTaskId(task.getTaskId());
        taskForm.setTaskTitle(task.getTaskTitle());
        taskForm.setTaskDescription(task.getTaskDescription());
        taskForm.setDueDate(task.getDueDate());
        return "task/edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@AuthenticationPrincipal LoginUser loginUser,
                         @PathVariable("id") Integer id,
                         @Validated TaskForm taskForm,
                         BindingResult result,
                         Model model,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            taskForm.setTaskId(id);
            return "task/edit";
        }

        int userId = loginUser.getUserId();
        var command = new TaskUpdateCommand(
                id,
                userId,
                taskForm.getTaskTitle(),
                taskForm.getTaskDescription(),
                taskForm.getDueDate()
        );
        taskApplicationService.update(command);

        return "redirect:/tasks/{id}";
    }


    @PostMapping("/transition/{id}")
    public String transition(@AuthenticationPrincipal LoginUser loginUser,
                             @PathVariable("id") Integer id,
                             @RequestParam("taskStatus") TaskStatus status) {
        int userId = loginUser.getUserId();
        var command = new TaskTransitionCommand(id, userId, status);
        taskApplicationService.transition(command);
        return "redirect:/tasks/{id}";
    }

    @PostMapping("/delete/{id}")
    public String delete(@AuthenticationPrincipal LoginUser loginUser,
                         @PathVariable("id") Integer id) {
        int userId = loginUser.getUserId();
        var command = new TaskDeleteCommand(id, userId);
        taskApplicationService.delete(command);
        return "redirect:/tasks";
    }
}
