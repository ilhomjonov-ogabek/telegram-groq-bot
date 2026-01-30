package org.example.springbootjpabuddy.repository;

import org.example.springbootjpabuddy.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}