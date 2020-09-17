package com.kingapawlowska.klinikaweterynaryjna.services.springdatajpa;

import com.kingapawlowska.klinikaweterynaryjna.model.Owner;
import com.kingapawlowska.klinikaweterynaryjna.model.Pet;
import com.kingapawlowska.klinikaweterynaryjna.model.PetType;
import com.kingapawlowska.klinikaweterynaryjna.model.Visit;
import com.kingapawlowska.klinikaweterynaryjna.repositories.OwnerRepository;
import com.kingapawlowska.klinikaweterynaryjna.repositories.PetTypeRepository;
import com.kingapawlowska.klinikaweterynaryjna.repositories.VisitRepository;
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

class VisitSpringDataJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @Mock
    OwnerRepository ownerRepository;



    @InjectMocks
    VisitSpringDataJpaService service;

    Visit returnVisit;

    @BeforeEach
    void setUp() {

        Long visitId = 1L;
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeRepository.save(dog);
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerRepository.save(owner1);

        returnVisit = Visit.builder().date(LocalDate.of(2020,2,3)).description("blabla").pet(mikesPet).build();
    }

    @Test
    void findAll() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeRepository.save(dog);
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerRepository.save(owner1);

        Set<Visit> returnVisitsSet = new HashSet<>();
        returnVisitsSet.add(Visit.builder().date(LocalDate.of(2020,2,3)).description("blabla").pet(mikesPet).build());
        returnVisitsSet.add(Visit.builder().date(LocalDate.of(2021,3,4)).description("blabla2").pet(mikesPet).build());

        when(visitRepository.findAll()).thenReturn(returnVisitsSet);

        Set<Visit> visitSet = service.findAll();

        assertNotNull(visitSet);
        assertEquals(2, visitSet.size());
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));

        Visit visit = service.findById(1L);

        assertNotNull(visit);
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit visit = service.findById(1L);

        assertNull(visit);

    }

    @Test
    void save() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeRepository.save(dog);
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerRepository.save(owner1);

        Visit visitToSave = Visit.builder().date(LocalDate.of(2020,2,3)).description("blabla").pet(mikesPet).build();

        when(visitRepository.save(any())).thenReturn(returnVisit);

        Visit savedVisit = service.save(visitToSave);

        assertNotNull(savedVisit);

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVisit);

        //default is 1 times
        verify(visitRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(visitRepository).deleteById(anyLong());
    }
}