package com.example.service;

import com.example.entity.Note;
import com.example.exception.NoteNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public class NoteFakeRepository {
    private final Map<Long, Note> noteMap = new HashMap<>();

    public Map<Long, Note> mapAll() {
        return noteMap;
    }

    public Note add(Note note) {
        Long id = generateUniqueId();
        note.setId(id);
        noteMap.put(id, note);
        return note;
    }

    public void deleteById(Long id) throws NoteNotFoundException {
        if (noteMap.containsKey(id)) {
            noteMap.remove(id);
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    public void update(Note note) throws NoteNotFoundException {
        Optional<Map.Entry<Long, Note>> updateSearchById = noteMap.entrySet().stream()
                .filter(n -> Objects.equals(n.getKey(), note.getId()))
                .findFirst();
        if (updateSearchById.isPresent()) {
            Note updateNote = updateSearchById.get().getValue();
            updateNote.setTitle(note.getTitle());
            updateNote.setContext(note.getContext());
        } else {
            throw new NoteNotFoundException();
        }
    }

    public Note getById(Long id) throws NoteNotFoundException {
        if (noteMap.containsKey(id)) {
            return noteMap.get(id);
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    private static long generateUniqueId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }
}