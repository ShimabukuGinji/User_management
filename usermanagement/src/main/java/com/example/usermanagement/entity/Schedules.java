package com.example.usermanagement.entity;

public record Schedules(int id, int userId, int categoryId, String loginId, String password, String name, String createdAt, String updatedAt) {
}