package com.backend.rootLab.DTOS.Notifications;


import lombok.Data;

@Data
public class NotificationRequestDTO {

    private String userId;

    private String message;
}