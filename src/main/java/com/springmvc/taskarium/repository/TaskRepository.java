package com.springmvc.taskarium.repository;

import com.springmvc.taskarium.model.entity.Task;
import com.springmvc.taskarium.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByStatus(TaskStatus status);
}
