package com.springmvc.taskarium.repository;


import com.springmvc.taskarium.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findAllByTaskIdOrderByNoteIdDesc(Long id);
    List<Note> findAll();
}
