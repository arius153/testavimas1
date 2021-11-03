package com.example.testavimas1.controller;

import com.example.testavimas1.model.Veiksmas;
import com.example.testavimas1.model.VeiksmasDTO;
import com.example.testavimas1.service.VeiksmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VeiksmaiRESTController {

    @Autowired
    VeiksmasService veiksmasService;

    @GetMapping("/veiksmas-json")
    public List<Veiksmas> getVeiksmai() {
        var veiksmaiFromDb = veiksmasService.findAll();
        List<Veiksmas> veiksmai = new ArrayList<>();
        veiksmaiFromDb.forEach(veiksmai::add);
        return veiksmai;
    }

    @GetMapping("/veiksmas-json/{id}")
    public Veiksmas getVeiksmasById(@PathVariable long id) {
        return veiksmasService.findById(id);
    }

    @PostMapping("/add-veiksmas-json")
    public Veiksmas add(VeiksmasDTO veiksmas) {
        return veiksmasService.add(veiksmas);
    }

    @PutMapping("/update-veiksmas-json")
    public void update(VeiksmasDTO veiksmas) {
        if (veiksmas.getId() == null) {
            return;
        }
        veiksmasService.update(veiksmas);
    }

    @DeleteMapping("/delete-veiksmas-json/{id}")
    public void delete(@PathVariable long id) {
        veiksmasService.deleteById(id);
    }

}
