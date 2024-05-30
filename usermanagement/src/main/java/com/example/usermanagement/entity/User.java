package com.example.usermanagement.entity;

public record User(int id, String loginId, String password, String name, String createdAt, String updatedAt) {
}
