package com.example.exception;

public class NoteNotFoundException extends Exception {
    private static final String EXCEPTION_MESSAGE = "Note are id '%s' does not exist.";
    private static final String CAN_NOT_UPDATE_NOTE = "Failed to update a note ";

    public NoteNotFoundException() {
        super(CAN_NOT_UPDATE_NOTE);
    }

    public NoteNotFoundException(Long id) {
        super(String.format(EXCEPTION_MESSAGE, id));
    }
}
