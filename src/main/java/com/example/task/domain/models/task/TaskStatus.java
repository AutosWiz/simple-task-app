package com.example.task.domain.models.task;


public enum TaskStatus {
    NEW, // 新規
    WORKING, // 着手
    WAITING, // 未着手
    COMPLETED, // 完了
    DISCONTINUED, // 中止
    PENDING;// 保留
}
