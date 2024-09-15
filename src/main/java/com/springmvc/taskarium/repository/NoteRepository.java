package com.springmvc.taskarium.repository;


import com.springmvc.taskarium.model.entity.Note;
import com.springmvc.taskarium.model.entity.Task;
import com.springmvc.taskarium.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findAllByTaskOrderByNoteIdDesc(Task task);

    List<Note> findAllByTaskAndUserOrderByNoteIdDesc(Task task, User user);

    Optional<Note> findByNoteIdAndUser(Long noteId, User user);

    List<Note> findAllByUser(User user);
}
