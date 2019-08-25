package com.slugin.notes.controller;

import com.slugin.notes.entity.Note;
import com.slugin.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String main(Model model) {
        noteService.findAll(model);
        return "main";
    }

    @PostMapping
    public String filter(@RequestParam(required = false) String filter, Model model) {
        noteService.filter(filter, model);
        return "main";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @PostMapping("/add")
    public String add(
            @Valid Note note,
            BindingResult bindingResult,
            Model model
    ) {
        return noteService.add(note, bindingResult, model);
    }

    @GetMapping("/note/{id}")
    public String getNoteById(@PathVariable("id") int id, Model model) {
        noteService.getNoteById(id, model);
        return "note";
    }

    @PostMapping("/note/{id}")
    public String edit(
            @PathVariable("id") int id,
            @Valid Note note,
            BindingResult bindingResult,
            Model model
    ) {
        return noteService.edit(id, note, bindingResult, model);
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        noteService.delete(id);
        return "redirect:/";
    }



}