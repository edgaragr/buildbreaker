package com.backsource.buildbreaker.controllers;

import com.backsource.buildbreaker.models.Event;
import com.backsource.buildbreaker.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class EventController {

    @Autowired
    private EventRepository repository;

    @RequestMapping("/event")
    public String event (Model model) {
        model.addAttribute("events", repository.findAll());

        return "event";
    }

    @RequestMapping("/create")
    public String create(Model model){
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String name, @RequestParam String date, @RequestParam String description,
                       @RequestParam Double amount) {

        Event event = new Event();
        event.setAmount(amount);

        LocalDateTime localDateTime = LocalDateTime.parse(date);
        event.setDate(localDateTime);

        event.setDescription(description);
        event.setName(name);

        repository.save(event);

        return "redirect:/show/" + event.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("event", repository.findOne(id));

        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Event event = repository.findOne(id);
        repository.delete(event);

        return "redirect:/event";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("event", repository.findOne(id));

        return "edit";
    }

    @RequestMapping("/update")
    public String update (@RequestParam String id, @RequestParam String name, @RequestParam String date, @RequestParam String description,
                          @RequestParam Double amount) {

        Event event = repository.findOne(id);
        event.setAmount(amount);

        LocalDateTime localDateTime = LocalDateTime.parse(date);
        event.setDate(localDateTime);

        event.setDescription(description);
        event.setName(name);

        repository.save(event);

        return "redirect:/show/" + event.getId();
    }
}