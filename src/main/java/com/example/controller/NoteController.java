package com.example.controller;

import com.example.entity.Note;
import com.example.exception.NoteNotFoundException;
import com.example.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
@Validated
@Controller
@RequestMapping("/note")
public class NoteController {
    private static final String PATH_NOTE_HTML_FILE = "note_configure/note";
    private static final String PATH_NOTE_EDIT_HTML_FILE = "note_configure/note_edit";
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ModelAndView getNoteAll() {
        ModelAndView result = new ModelAndView(PATH_NOTE_HTML_FILE);
        Map<Long, Note> note = noteService.mapAll();
        return result.addObject("noteAll", note.values());
    }

    @PostMapping("/delete")
    public String deleteNoteById(@Valid @NotNull @RequestParam("id") Long id) throws NoteNotFoundException {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView editNote(@Valid @NotNull @RequestParam("id") Long id) throws NoteNotFoundException {
        ModelAndView result = new ModelAndView(PATH_NOTE_EDIT_HTML_FILE);
        Note noteById = noteService.getById(id);
        return result.addObject("noteById", noteById);
    }

    @PostMapping("/edit")
    public String updateNote(@Valid @NotNull @ModelAttribute("note") Note note) throws NoteNotFoundException {
        noteService.update(note);
        return "redirect:/note/list";
    }
}