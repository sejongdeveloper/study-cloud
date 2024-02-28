package com.study.todo.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
    private static int count;


    public int getCount() {
        return TodoRepository.count;
    }

    public void plusCount(int count) {
        TodoRepository.count += count;
    }
}
