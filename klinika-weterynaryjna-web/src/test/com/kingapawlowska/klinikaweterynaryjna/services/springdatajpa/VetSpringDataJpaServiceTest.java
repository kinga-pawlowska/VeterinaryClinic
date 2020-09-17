package com.kingapawlowska.klinikaweterynaryjna.services.springdatajpa;

import com.kingapawlowska.klinikaweterynaryjna.model.Speciality;
import com.kingapawlowska.klinikaweterynaryjna.model.Vet;
import com.kingapawlowska.klinikaweterynaryjna.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetSpringDataJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSpringDataJpaService service;

    Vet returnVet;

    @BeforeEach
    void setUp() {
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(new Speciality("Oculist"));
        returnVet = Vet.builder().specialities(specialitySet).build();
    }

    @Test
    void findAll() {
        Set<Vet> returnVetSet = new HashSet<>();
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(new Speciality("Oculist"));
        returnVetSet.add(Vet.builder().specialities(specialitySet).build());
        returnVetSet.add(Vet.builder().specialities(specialitySet).build());
        when(vetRepository.findAll()).thenReturn(returnVetSet);

        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        Vet vet = service.findById(1L);

        assertNotNull(vet);
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet vet = service.findById(1L);

        assertNull(vet);

    }

    @Test
    void save() {
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(new Speciality("Oculist"));
        Vet vetToSave = Vet.builder().specialities(specialitySet).build();

        when(vetRepository.save(any())).thenReturn(returnVet);

        Vet savedVet = service.save(vetToSave);

        assertNotNull(savedVet);

        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVet);

        //default is 1 times
        verify(vetRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(vetRepository).deleteById(anyLong());
    }
}