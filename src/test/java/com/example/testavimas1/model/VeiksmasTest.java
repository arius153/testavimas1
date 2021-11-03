package com.example.testavimas1.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class VeiksmasTest {

    @Test
    void testNewObject() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "861111111", null);
        LocalDate dateNow = LocalDate.now();
        Veiksmas v = Veiksmas.builder()
                .veiksmas("insert")
                .data(dateNow)
                .vartotojas(vartotojas)
                .build();
        assertAll("Test veiksmas constructor",
                () -> assertEquals("insert", v.getVeiksmas()),
                () -> assertEquals(dateNow, v.getData()),
                () -> assertEquals(vartotojas, v.getVartotojas()));
    }

    @Test
    void testCompareTo() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "861111111", null);
        LocalDate dateNow = LocalDate.now();
        Veiksmas v1 = Veiksmas.builder()
                .id(1L)
                .veiksmas("insert")
                .data(dateNow)
                .vartotojas(vartotojas)
                .build();
        Veiksmas v2 = Veiksmas.builder()
                .id(1L)
                .veiksmas("insert")
                .data(dateNow)
                .vartotojas(vartotojas)
                .build();
        assertEquals(0, v1.compareTo(v2));
    }

    @Test
    void testEqualsObject() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "861111111", null);
        LocalDate dateNow = LocalDate.now();
        Veiksmas v1 = Veiksmas.builder()
                .id(1L)
                .veiksmas("insert")
                .data(dateNow)
                .vartotojas(vartotojas)
                .build();
        Veiksmas v2 = Veiksmas.builder()
                .id(1L)
                .veiksmas("insert")
                .data(dateNow)
                .vartotojas(vartotojas)
                .build();
        assertEquals(v1, v2);
    }

}
