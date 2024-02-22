package com.example;

import com.example.entity.Note;
import com.example.service.NoteService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Main {
    private final NoteService noteService;

    @Autowired
    public Main(NoteService noteService) {
        this.noteService = noteService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init() {
        noteService.add(new Note("Monday", "swim"));
        noteService.add(new Note("Wednesday", "relax"));
        noteService.add(new Note("Tuesday", "read"));
        noteService.add(new Note("Saturday", "work"));
        log.info("Application run!");
    }
}
