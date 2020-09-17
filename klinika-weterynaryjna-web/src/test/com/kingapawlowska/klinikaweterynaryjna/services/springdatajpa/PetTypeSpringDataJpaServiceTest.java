package com.kingapawlowska.klinikaweterynaryjna.services.springdatajpa;

import com.kingapawlowska.klinikaweterynaryjna.model.PetType;
import com.kingapawlowska.klinikaweterynaryjna.repositories.PetTypeRepository;
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
class PetTypeSpringDataJpaServiceTest {

    public static final String NAME = "Dog";

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSpringDataJpaService service;

    PetType returnPetType;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(1L).name(NAME).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypeSet = new HashSet<>();
        returnPetTypeSet.add(PetType.builder().id(1L).name("anyName1").build());
        returnPetTypeSet.add(PetType.builder().id(2L).name("anyName2").build());

        when(petTypeRepository.findAll()).thenReturn(returnPetTypeSet);

        Set<PetType> petTypeSet = service.findAll();

        assertNotNull(petTypeSet);
        assertEquals(2, petTypeSet.size());

    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));

        PetType petType = service.findById(1L);

        assertNotNull(petType);
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        PetType petType = service.findById(1L);

        assertNull(petType);

    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(1L).build();

        when(petTypeRepository.save(any())).thenReturn(returnPetType);

        PetType savedPetType = service.save(petTypeToSave);

        assertNotNull(savedPetType);

        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPetType);

        //default is 1 times
        verify(petTypeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(petTypeRepository).deleteById(anyLong());
    }
}