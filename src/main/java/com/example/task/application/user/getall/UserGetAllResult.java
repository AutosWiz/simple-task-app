package com.example.task.application.user.getall;

import com.example.task.application.user.common.UserData;
import lombok.Data;

import java.util.List;

@Data
public class UserGetAllResult {
    private final List<UserData> userDataList;
}
