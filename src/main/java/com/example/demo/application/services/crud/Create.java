package com.example.demo.application.services.crud;

public interface Create<EntityRequest,  Entity>{
    public Entity create(EntityRequest request);
}
