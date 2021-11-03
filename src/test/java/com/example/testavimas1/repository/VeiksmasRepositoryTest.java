package com.example.testavimas1.repository;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.model.Veiksmas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VeiksmasRepositoryTest {

    @Autowired
    VeiksmasRepository veiksmasRepository;
    @Autowired
    VartotojasRepository vartotojasRepository;

    @Test
    public void testSave() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(vartotojas);
        Veiksmas veiksmas = new Veiksmas(2L, "insert", LocalDate.now(), vartotojas);
        veiksmasRepository.save(veiksmas);
        Veiksmas veiksmasFromDb = veiksmasRepository.findById(veiksmas.getId()).orElse(null);
        assertNotNull(veiksmasFromDb);
        assertEquals(veiksmas, veiksmasFromDb);

    }

    @Test
    public void testFindAll() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(vartotojas);
        Veiksmas veiksmas1 = new Veiksmas(2L, "insert", LocalDate.now(), vartotojas);
        Veiksmas veiksmas2 = new Veiksmas(3L, "delete", LocalDate.now(), vartotojas);
        veiksmasRepository.save(veiksmas1);
        veiksmasRepository.save(veiksmas2);
        Iterable<Veiksmas> veiksmai = veiksmasRepository.findAll();
        List<Veiksmas> result = new ArrayList<>();
        veiksmai.forEach(result::add);
        assertEquals(2, result.size());
    }

    @Test
    public void testDelete() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(vartotojas);
        Veiksmas veiksmas1 = new Veiksmas(2L, "insert", LocalDate.now(), vartotojas);
        veiksmasRepository.save(veiksmas1);
        Veiksmas veiksmasFromDb = veiksmasRepository.findById(2L).orElse(null);
        assertNotNull(veiksmasFromDb);
        veiksmasRepository.delete(veiksmasFromDb);
        Iterable<Veiksmas> veiksmai = veiksmasRepository.findAll();
        List<Veiksmas> result = new ArrayList<>();
        veiksmai.forEach(result::add);
        assertEquals(0, result.size());
    }

    @Test
    void testDeleteById() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(vartotojas);
        Veiksmas veiksmas1 = new Veiksmas(2L, "insert", LocalDate.now(), vartotojas);
        veiksmasRepository.save(veiksmas1);
        Iterable<Veiksmas> veiksmai = veiksmasRepository.findAll();
        List<Veiksmas> result = new ArrayList<>();
        veiksmai.forEach(result::add);
        assertEquals(1, result.size());
        veiksmasRepository.deleteById(2L);
        Iterable<Veiksmas> veiksmai2 = veiksmasRepository.findAll();
        List<Veiksmas> result2 = new ArrayList<>();
        veiksmai2.forEach(result2::add);
        assertEquals(0, result2.size());
    }
}
