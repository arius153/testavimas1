package com.example.testavimas1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VartotojasTest {

    @Test
    void testNewObject() {
        Vartotojas v = Vartotojas.builder()
                .vardas("Arijus")
                .telNr("861111111")
                .build();
        assertAll("Test vartotojas constructor",
                () -> assertEquals("Arijus", v.getVardas()),
                () -> assertEquals("861111111", v.getTelNr()));
    }

    @Test
    void testCompareTo() {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "861111111", null);
        Vartotojas v2 = new Vartotojas(1L, "Arijus", "861111111", null);
        assertEquals(0, v1.compareTo(v2));
    }

    @Test
    void testEqualsObject() {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "861111111", null);
        Vartotojas v2 = new Vartotojas(1L, "Arijus", "861111111", null);
        assertEquals(v1, v2);
    }
}
