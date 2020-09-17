package com.kingapawlowska.klinikaweterynaryjna.services.springdatajpa;

import com.kingapawlowska.klinikaweterynaryjna.model.Speciality;
import com.kingapawlowska.klinikaweterynaryjna.repositories.SpecialityRepository;
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
class SpecialitySpringDataJpaServiceTest {

    public static final String DESCRIPTION = "Oculist";

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialitySpringDataJpaService service;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = Speciality.builder().description(DESCRIPTION).build();
    }

    @Test
    void findAll() {
        Set<Speciality> returnSpecialitySet = new HashSet<>();
        returnSpecialitySet.add(Speciality.builder().description("anySpeciality1").build());
        returnSpecialitySet.add(Speciality.builder().description("anySpeciality2").build());

        when(specialityRepository.findAll()).thenReturn(returnSpecialitySet);

        Set<Speciality> specialities = service.findAll();

        assertNotNull(specialities);
        assertEquals(2, specialities.size());
    }

    @Test
    void findById() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));

        Speciality speciality = service.findById(1L);

        assertNotNull(speciality);
    }

    @Test
    void findByIdNotFound() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.empty());

        Speciality speciality = service.findById(1L);

        assertNull(speciality);

    }

    @Test
    void save() {
        Speciality specialityToSave = Speciality.builder().description("anySpeciality").build();

        when(specialityRepository.save(any())).thenReturn(returnSpeciality);

        Speciality savedSpeciality = service.save(specialityToSave);

        assertNotNull(savedSpeciality);

        verify(specialityRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnSpeciality);

        //default is 1 times
        verify(specialityRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(specialityRepository).deleteById(anyLong());
    }
}