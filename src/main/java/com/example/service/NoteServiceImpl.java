package com.example.service;

import com.example.entity.Note;
import com.example.exception.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteFakeRepository noteFakeRepository;

    @Autowired
    public NoteServiceImpl(NoteFakeRepository noteFakeRepository) {
        this.noteFakeRepository = noteFakeRepository;
    }

    @Override
    public Map<Long, Note> mapAll() {
        return noteFakeRepository.mapAll();
    }

    @Override
    public Note add(Note note) {
        return noteFakeRepository.add(note);
    }

    @Override
    public void deleteById(long id) throws NoteNotFoundException {
        noteFakeRepository.deleteById(id);
    }

    @Override
    public void update(Note note) throws NoteNotFoundException {
        noteFakeRepository.update(note);
    }

    @Override
    public Note getById(long id) throws NoteNotFoundException {
        return noteFakeRepository.getById(id);
    }
}