package com.slugin.notes.repository;

import com.slugin.notes.entity.Note;
import org.springframework.data.repository.CrudRepository;


public interface NoteRepository extends CrudRepository<Note, Integer> {
    Note findNoteById(int id);
    Iterable<Note> findNotesByContentContainsOrHeaderContains(String content, String header);
}
