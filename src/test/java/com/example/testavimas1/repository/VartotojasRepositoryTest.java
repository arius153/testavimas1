package com.example.testavimas1.repository;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.model.Veiksmas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VartotojasRepositoryTest {

    @Autowired
    VartotojasRepository vartotojasRepository;
    @Autowired
    VeiksmasRepository veiksmasRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSave() {
        Vartotojas v = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(v);

        Vartotojas vartotojasFromDb = vartotojasRepository.findById(v.getId()).orElse(null);
        assertNotNull(vartotojasFromDb);
        assertEquals(v, vartotojasFromDb);
    }

    @Test
    public void testFindAll() {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        Vartotojas v2 = new Vartotojas(2L, "Arijus", "8611111234", null);
        vartotojasRepository.save(v1);
        vartotojasRepository.save(v2);
        Iterable<Vartotojas> vartotojai = vartotojasRepository.findAll();
        assertNotNull(vartotojai);
        List<Vartotojas> result = new ArrayList<>();
        vartotojai.forEach(result::add);
        assertEquals(2, result.size());
    }

    @Test
    public void testDelete() {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(v1);
        Vartotojas vartotojasFromDb = vartotojasRepository.findById(1L).orElse(null);
        assertNotNull(vartotojasFromDb);
        vartotojasRepository.delete(vartotojasFromDb);
        Iterable<Vartotojas> vartotojai = vartotojasRepository.findAll();
        List<Vartotojas> result = new ArrayList<>();
        vartotojai.forEach(result::add);
        assertEquals(0, result.size());
    }

    @Test
    void testDeleteById() {
        Vartotojas v1 = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(v1);
        Iterable<Vartotojas> vartotojai = vartotojasRepository.findAll();
        List<Vartotojas> result = new ArrayList<>();
        vartotojai.forEach(result::add);
        assertEquals(1, result.size());
        vartotojasRepository.deleteById(1L);
        Iterable<Vartotojas> vartotojai2 = vartotojasRepository.findAll();
        List<Vartotojas> result2 = new ArrayList<>();
        vartotojai2.forEach(result2::add);
        assertEquals(0, result2.size());
    }

    @Test
    void testDeleteByIdWithChildren() {
        Vartotojas vartotojas = new Vartotojas(1L, "Arijus", "8611111234", null);
        vartotojasRepository.save(vartotojas);
        Veiksmas veiksmas1 = new Veiksmas(2L, "insert", LocalDate.now(), vartotojas);
        Veiksmas veiksmas2 = new Veiksmas(3L, "delete", LocalDate.now(), vartotojas);
        veiksmasRepository.save(veiksmas1);
        veiksmasRepository.save(veiksmas2);
        Iterable<Vartotojas> vartotojai = vartotojasRepository.findAll();
        List<Vartotojas> result = new ArrayList<>();
        vartotojai.forEach(result::add);
        assertEquals(1, result.size());
        Iterable<Veiksmas> veiksmai = veiksmasRepository.findAll();
        List<Veiksmas> result1 = new ArrayList<>();
        veiksmai.forEach(result1::add);
        assertEquals(2, result1.size());
        testEntityManager.flush();
        testEntityManager.clear();
        vartotojasRepository.deleteById(1L);
        Iterable<Vartotojas> vartotojai1 = vartotojasRepository.findAll();
        List<Vartotojas> result3 = new ArrayList<>();
        vartotojai1.forEach(result3::add);
        assertEquals(0, result3.size());
        Iterable<Veiksmas> veiksmai2 = veiksmasRepository.findAll();
        List<Veiksmas> result4 = new ArrayList<>();
        veiksmai2.forEach(result4::add);
        assertEquals(0, result4.size());
    }
}
