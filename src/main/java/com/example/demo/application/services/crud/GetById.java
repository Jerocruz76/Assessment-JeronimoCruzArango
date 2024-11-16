package com.example.demo.application.services.crud;

public interface GetById<Entity, ID>{
    public Entity getById(ID id);
}
