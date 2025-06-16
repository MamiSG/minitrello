package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.Users;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskRepository.findByUser(user);
    }

    public Task updateTaskStatus(Long id, String status) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Não autorizado para atualizar esta tarefa");
        }
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Não autorizado para remover esta tarefa");
        }
        taskRepository.delete(task);
    }
}