package com.example.service;

import com.example.entity.Note;

import java.util.Map;

public interface NoteService {
    Map<Long, Note> mapAll();

    Note add(Note note);

    void deleteById(long id);

    void update(Note note);

    Note getById(long id);
}
