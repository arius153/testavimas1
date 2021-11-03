package com.example.testavimas1.controller;

import com.example.testavimas1.model.Veiksmas;
import com.example.testavimas1.model.VeiksmasDTO;
import com.example.testavimas1.service.VartotojasService;
import com.example.testavimas1.service.VeiksmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class VeiksmaiController {

    @Autowired
    private VeiksmasService veiksmasService;
    @Autowired
    private VartotojasService vartotojasService;

    //@RequestMapping(value = "/", method= RequestMethod.GET)
    @GetMapping("/veiksmai")
    public String getVeiksmai(ModelMap model) {
        model.put("veiksmai", veiksmasService.findAll());
        return "veiksmai"; // view resolver /WEB-INF/jsp/welcome.jsp
    }

    @GetMapping("/add-veiksmas")
    public String showAddPage(ModelMap model) {
        model.addAttribute("vartotojai", vartotojasService.findAll());
        model.addAttribute("veiksmas", new VeiksmasDTO());
        return "add-veiksmas";
    }

    @PostMapping("/add-veiksmas")
    public String add(ModelMap model, @ModelAttribute("veiksmas") VeiksmasDTO veiksmas, BindingResult result) {
        if (result.hasErrors()) {
            return "add-veiksmas";
        }
        veiksmasService.add(veiksmas);
        return "redirect:/veiksmai";
    }

    @GetMapping("/update-veiksmas/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable long id) {
        Veiksmas veiksmas = veiksmasService.findById(id);
        VeiksmasDTO veiksmasDTO = veiksmasService.mapVeiksmasToDTO(veiksmas);
        model.addAttribute("vartotojai", vartotojasService.findAll());
        model.addAttribute("veiksmas", veiksmasDTO);
        return "add-veiksmas";
    }

    @PostMapping("/update-veiksmas/{id}")
    public String update(ModelMap model, @ModelAttribute("veiksmas") VeiksmasDTO veiksmas, BindingResult result) {
        if (result.hasErrors()) {
            return "add-veiksmas";
        }
        veiksmasService.update(veiksmas);
        return "redirect:/veiksmai";
    }

    @GetMapping("delete-veiksmas/{id}")
    public String delete(@PathVariable long id) {
        veiksmasService.deleteById(id);
        return "redirect:/veiksmai";
    }


}
