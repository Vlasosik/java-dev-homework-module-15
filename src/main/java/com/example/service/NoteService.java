package com.example.service;

import com.example.entity.Note;
import com.example.exception.NoteNotFoundException;

import java.util.Map;

public interface NoteService {
    Map<Long, Note> mapAll();

    Note add(Note note);

    void deleteById(long id) throws NoteNotFoundException;

    void update(Note note) throws NoteNotFoundException;

    Note getById(long id) throws NoteNotFoundException;
}
