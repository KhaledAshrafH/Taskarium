package com.springmvc.taskarium.repository;

import com.springmvc.taskarium.model.entity.Task;
import com.springmvc.taskarium.model.entity.User;
import com.springmvc.taskarium.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByStatusAndUser(TaskStatus status, User user);

    Optional<Task> findByTaskIdAndStatusAndUser(Long id, TaskStatus status,User user);

    Optional<Task> findByTaskIdAndUser(Long id,User user);
}
