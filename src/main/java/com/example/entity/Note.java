package com.example.entity;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class Note {
    private Long id;
    @Size(min = 3, max = 250, message = "Title must be between 3 and 100 characters")
    private String title;
    @Size(min = 3, max = 250, message = "Context must be between 3 and 100 characters")
    private String context;

    public Note(String title, String context) {
        this.title = title;
        this.context = context;
    }
}
