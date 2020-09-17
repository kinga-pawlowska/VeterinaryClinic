package com.kingapawlowska.klinikaweterynaryjna.services.springdatajpa;

import com.kingapawlowska.klinikaweterynaryjna.model.Owner;
import com.kingapawlowska.klinikaweterynaryjna.model.Pet;
import com.kingapawlowska.klinikaweterynaryjna.model.PetType;
import com.kingapawlowska.klinikaweterynaryjna.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSpringDataJpaServiceTest {

    public static final String NAME = "Nutka";
    public static final LocalDate BIRTHDAATE = LocalDate.of(2007,12,24);
    public static final PetType PET_TYPE = new PetType("Dog");
    public static final Long ID = 1L;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSpringDataJpaService service;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        final Owner owner = Owner.builder().build();
        returnPet = Pet.builder().id(ID).name(NAME).birthDate(BIRTHDAATE).petType(PET_TYPE).owner(owner).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetsSet = new HashSet<>();
        returnPetsSet.add(Pet.builder().id(1L).build());
        returnPetsSet.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(returnPetsSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());

    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(1L);

        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(1L);

        assertNull(pet);
    }

    @Test
    void save() {

        Pet petToSave = Pet.builder().id(1L).build();

        when(petRepository.save(any())).thenReturn(returnPet);

        Pet savedPet = service.save(petToSave);

        assertNotNull(savedPet);

        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPet);

        //default is 1 times
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(petRepository).deleteById(anyLong());
    }
}