package com.kingapawlowska.klinikaweterynaryjna.controllers;

import com.kingapawlowska.klinikaweterynaryjna.model.BaseEntity;
import com.kingapawlowska.klinikaweterynaryjna.model.Vet;
import com.kingapawlowska.klinikaweterynaryjna.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model){

//        // sorting a Set
        Set<Vet> vetSet = vetService.findAll();
        List<Vet> sortedVetList = vetSet
                .stream()
                .sorted(Comparator.comparing(BaseEntity::getId))
                .collect(Collectors.toList());

//        model.addAttribute("vets", vetService.findAll());
        model.addAttribute("vets", sortedVetList);
        return "vets/index";
    }

    @GetMapping("api/vets")
    public @ResponseBody Set<Vet> getVetsJSON() {
        return vetService.findAll();
    }
}
