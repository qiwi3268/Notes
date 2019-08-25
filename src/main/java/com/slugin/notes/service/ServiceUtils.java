package com.slugin.notes.service;

import com.slugin.notes.entity.Note;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ServiceUtils {
    static boolean checkErrors(@Valid Note note, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(collector);
            model.mergeAttributes(errorsMap);
            return true;
        }

        if (!note.hasFirstLine()) {
            model.addAttribute("error", "Please fill the header or text");
            return true;
        }
        return false;
    }

    static void sortNotesById(Model model, Iterable<Note> notes) {
        List<Note> sortedNotes = new ArrayList<>();
        notes.forEach(sortedNotes::add);
        sortedNotes.sort(Comparator.comparing(Note::getId));
        model.addAttribute("notes", sortedNotes);
    }
}
