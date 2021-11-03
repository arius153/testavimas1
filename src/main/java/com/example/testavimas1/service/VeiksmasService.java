package com.example.testavimas1.service;

import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.model.Veiksmas;
import com.example.testavimas1.model.VeiksmasDTO;
import com.example.testavimas1.repository.VartotojasRepository;
import com.example.testavimas1.repository.VeiksmasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VeiksmasService {

    @Autowired
    VeiksmasRepository veiksmasRepository;
    @Autowired
    VartotojasRepository vartotojasRepository;
    @Autowired
    VartotojasService vartotojasService;

    public Iterable<Veiksmas> findAll() {
        return veiksmasRepository.findAll();
    }

    public Veiksmas findById(Long id) {
        return veiksmasRepository.findById(id).orElse(null);
    }

    public Veiksmas add(Veiksmas veiksmas) {
        return veiksmasRepository.save(veiksmas);
    }

    public Veiksmas add(VeiksmasDTO veiksmas) {
        Vartotojas vartotojas = vartotojasService.findById(veiksmas.getVartotojoId());
        Veiksmas veiksmasToAdd = veiksmasRepository.save(Veiksmas.builder()
                .vartotojas(vartotojas)
                .veiksmas(veiksmas.getVeiksmas())
                .data(veiksmas.getData())
                .build());
        return veiksmasRepository.save(veiksmasToAdd);
    }

    public void deleteById(Long id) {
        veiksmasRepository.deleteById(id);
    }

    public void delete(Veiksmas veiksmas) {
        veiksmasRepository.delete(veiksmas);
    }

    public void update(Veiksmas veiksmas) {
        veiksmasRepository.save(veiksmas);
    }

    public void update(VeiksmasDTO veiksmas) {
        Veiksmas veiksmasFromDb = veiksmasRepository.findById(veiksmas.getId()).orElse(null);
        if (veiksmasFromDb == null) {
            return;
        }
        veiksmasFromDb.setVeiksmas(veiksmas.getVeiksmas());
        veiksmasFromDb.setData(veiksmas.getData());
        veiksmasFromDb.setVartotojas(vartotojasService.findById(veiksmas.getVartotojoId()));
        veiksmasRepository.save(veiksmasFromDb);
    }

    public VeiksmasDTO mapVeiksmasToDTO(Veiksmas veiksmas) {
        VeiksmasDTO veiksmasDTO = VeiksmasDTO.builder()
                .id(veiksmas.getId())
                .veiksmas(veiksmas.getVeiksmas())
                .data(veiksmas.getData())
                .vartotojoId(veiksmas.getVartotojas().getId())
                .build();
        return veiksmasDTO;
    }
}
