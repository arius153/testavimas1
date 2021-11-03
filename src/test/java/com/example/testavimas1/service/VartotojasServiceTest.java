package com.example.testavimas1.service;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.repository.VartotojasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VartotojasServiceTest {

    @Mock
    VartotojasRepository vartotojasRepository;

    @InjectMocks
    VartotojasService vartotojasService;

    @Test
    void testFindAll() {
        Vartotojas v = new Vartotojas();
        List<Vartotojas> vartotojai = new ArrayList<>();
        vartotojai.add(v);
        when(vartotojasRepository.findAll()).thenReturn(vartotojai);
        Iterable<Vartotojas> found = vartotojasService.findAll();
        verify(vartotojasRepository).findAll();
        assertEquals(1, StreamSupport.stream(found.spliterator(), false).count());
    }

    @Test
    void testFindById() {
        Vartotojas v = new Vartotojas(1L, "Arijus", "8611111234", null);
        when(vartotojasRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(v)); // static method when daromas tada, kai metodas returnina reiksme
        Vartotojas found = vartotojasService.findById(1L);
        verify(vartotojasRepository).findById(Mockito.anyLong());
        assertNotNull(found);
    }

    @Test
    void testAdd() {
        Vartotojas v = new Vartotojas(1L, "Arijus", "8611111234", null);
        when(vartotojasRepository.save(Mockito.any(Vartotojas.class))).thenReturn(v);
        Vartotojas added = vartotojasService.add(v);
        verify(vartotojasRepository).save(Mockito.any(Vartotojas.class));
        assertNotNull(added);
    }

    @Test
    void testUpdate() {
        Vartotojas v = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasService.update(v);
        verify(vartotojasRepository).save(Mockito.any(Vartotojas.class));
    }

    @Test
    void testDeleteById() {
        vartotojasService.deleteById(1L);
        verify(vartotojasRepository).deleteById(Mockito.anyLong());
    }
}
