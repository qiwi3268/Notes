package com.slugin.notes.service;

import com.slugin.notes.entity.Note;
import com.slugin.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public void findAll(Model model) {
        Iterable<Note> notes = noteRepository.findAll();
        ServiceUtils.sortNotesById(model, notes);
    }

    public void filter(@RequestParam(required = false) String filter, Model model) {
        Iterable<Note> notes;
        if (filter != null && !filter.isEmpty()) {
            notes = noteRepository.findNotesByContentContainsOrHeaderContains(filter, filter);
        } else {
            notes = noteRepository.findAll();
        }
        ServiceUtils.sortNotesById(model, notes);
    }

    public String add(
            @Valid Note note,
            BindingResult bindingResult,
            Model model
    ) {
        note.initFirstLine();
        model.addAttribute("note", note);
        if (ServiceUtils.checkErrors(note, bindingResult, model)) return "add";
        noteRepository.save(note);
        return "redirect:/";
    }

    public void getNoteById(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", noteRepository.findNoteById(id));
    }

    public String edit(
            @PathVariable("id") int id,
            @Valid Note note,
            BindingResult bindingResult,
            Model model
    ) {
        note.initFirstLine();
        Note formerNote = noteRepository.findNoteById(id);
        if (ServiceUtils.checkErrors(note, bindingResult, model)) return "add";
        formerNote.setHeader(note.getHeader());
        formerNote.setContent(note.getContent());
        formerNote.initFirstLine();
        noteRepository.save(formerNote);
        return "redirect:/";
    }

    public void delete(@PathVariable("id") int id) {
        noteRepository.delete(noteRepository.findNoteById(id));
    }








}
