package com.example.controller;

import com.example.entity.Note;
import com.example.exception.NoteNotFoundException;
import com.example.service.NoteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class NoteControllerV1 {
    private static final String PATH_NOTE_HTML_FILE = "note_configure/note";
    private static final String PATH_NOTE_EDIT_HTML_FILE = "note_configure/note_edit";
    private static final String REDIRECT_NOTE_LIST = "redirect:/note/list";
    private static final String THEME = "theme";
    private final NoteService noteService;

    @Autowired
    public NoteControllerV1(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ModelAndView getNoteAll(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView result = new ModelAndView(PATH_NOTE_HTML_FILE);
        String theme = getThemeCookie(request);
        if (theme == null || !theme.equals("black")) {
            setThemeCookie(response, "white");
            theme = "white";
        }
        Map<Long, Note> note = noteService.mapAll();
        return result.addObject("noteAll", note.values()).addObject(THEME, theme);
    }

    @PostMapping("/delete")
    public String deleteNoteById(@Valid @NotNull @RequestParam("id") Long id) throws NoteNotFoundException {
        noteService.deleteById(id);
        return REDIRECT_NOTE_LIST;
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
        return REDIRECT_NOTE_LIST;
    }
    @PostMapping("/changeTheme")
    public String changeTheme(HttpServletResponse response, @RequestParam(THEME) String theme){
        setThemeCookie(response, theme);
        return REDIRECT_NOTE_LIST;
    }

    private void setThemeCookie(HttpServletResponse response, String theme) {
        Cookie themeCookie = new Cookie(THEME, theme);
        response.addCookie(themeCookie);
    }

    private String getThemeCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(THEME)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}