package com.example.entity;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Note {
    private Long id;
    private String title;
    private String context;

    public Note(String title, String context) {
        this.title = title;
        this.context = context;
    }
}
