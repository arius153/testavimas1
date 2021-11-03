package com.example.testavimas1.service;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.repository.VartotojasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VartotojasService {

    @Autowired
    VartotojasRepository vartotojasRepository;

    public Iterable<Vartotojas> findAll() {
        return vartotojasRepository.findAll();
    }

    public Vartotojas findById(Long id) {
        return vartotojasRepository.findById(id).orElse(null);
    }

    public Vartotojas add(Vartotojas vartotojas) {
        return vartotojasRepository.save(vartotojas);
    }

    public void deleteById(Long id) {
        vartotojasRepository.deleteById(id);
    }

    public void delete(Vartotojas vartotojas) {
        vartotojasRepository.delete(vartotojas);
    }

    public void update(Vartotojas vartotojas) {
        vartotojasRepository.save(vartotojas);
    }

}
