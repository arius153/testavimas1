package com.example.testavimas1.controller;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.service.VartotojasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VartotojasRESTController {

    @Autowired
    VartotojasService vartotojasService;

    @GetMapping("/vartotojas-json")
    public List<Vartotojas> getVartotojai() {
        var vartotojaiFromDb = vartotojasService.findAll();
        List<Vartotojas> vartotojai = new ArrayList<>();
        vartotojaiFromDb.forEach(vartotojai::add);
        return vartotojai;
    }

    @GetMapping("/vartotojas-json/{id}")
    public Vartotojas getVartotojasById(@PathVariable long id) {
        return vartotojasService.findById(id);
    }

    @PostMapping("/add-vartotojas-json")
    public Vartotojas add(Vartotojas vartotojas) {
        return vartotojasService.add(vartotojas);
    }

    @PutMapping("/update-vartotojas-json")
    public void update(Vartotojas vartotojas) {
        if (vartotojas.getId() == null) {
            return;
        }
        vartotojasService.update(vartotojas);
    }

    @DeleteMapping("/delete-vartotojas-json/{id}")
    public void delete(@PathVariable long id) {
        vartotojasService.deleteById(id);
    }

}
