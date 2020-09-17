package com.kingapawlowska.klinikaweterynaryjna.controllers;

import com.kingapawlowska.klinikaweterynaryjna.model.Vet;
import com.kingapawlowska.klinikaweterynaryjna.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetService;

    @Mock
    Model model;

    @InjectMocks
    VetController controller;

    MockMvc mockMvc;

    Vet vet;
    Set<Vet> vets;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        controller = new VetController(vetService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/vets/index")).
                andExpect(status().isOk()).
                andExpect(view().name("vets/index"));
    }

    @Test
    void listVets() {
//        //        String viewName = controller.listVets(model);
////
////        assertEquals("vets/index", viewName);
////        Mockito.verify(vetService, Mockito.times(1)).findAll();
////        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("vets"), Mockito.anySet());
////
//
//        // given
//        Set<Vet> vets = new HashSet<>();
//        vets.add(new Vet());
//        vets.add(new Vet());
//
//        when(vetService.findAll()).thenReturn(vets);
//
//        ArgumentCaptor<Set<Vet>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
//
//        // when
//        String viewName = controller.listVets(model);
//
//        // then
//        assertEquals("vets/index", viewName);
//        verify(vetService, Mockito.times(1)).findAll();
//        verify(model, Mockito.times(1)).addAttribute(Mockito.eq("vets"), argumentCaptor.capture());
//        Set<Vet> setInController = argumentCaptor.getValue();
//        assertEquals(2, setInController.size());
    }

    @Test
    void getVetsJSON() {
        Set<Vet> vets = vetService.findAll();
        assertEquals(vets, controller.getVetsJSON());
    }
}