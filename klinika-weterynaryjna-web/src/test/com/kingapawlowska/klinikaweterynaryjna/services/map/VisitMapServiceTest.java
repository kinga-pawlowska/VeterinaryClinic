package com.kingapawlowska.klinikaweterynaryjna.services.map;

import com.kingapawlowska.klinikaweterynaryjna.model.Owner;
import com.kingapawlowska.klinikaweterynaryjna.model.Pet;
import com.kingapawlowska.klinikaweterynaryjna.model.PetType;
import com.kingapawlowska.klinikaweterynaryjna.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {

    VisitMapService visitMapService;

    PetTypeMapService petTypeMapService;
    PetMapService petMapService;
    OwnerMapService ownerMapService;

    private final Long visitId = 1L;

    @BeforeEach
    void setUp() {
        visitMapService = new VisitMapService();

        petTypeMapService = new PetTypeMapService();
        petMapService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeMapService, petMapService);

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeMapService.save(dog);
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
        ownerMapService.save(owner1);
        Visit catVisit = new Visit();
        catVisit.setPet(mikesPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Doggy");
        catVisit.setId(visitId);
        visitMapService.save(catVisit);
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = visitMapService.findAll();
        assertEquals(1, visitSet.size());
    }

    @Test
    void findByExistingId() {
        Visit visit = visitMapService.findById(visitId);
        assertEquals(visitId, visit.getId());
    }

    @Test
    void findByNotExistingId() {
        Visit visit = visitMapService.findById(5L);
        assertNull(visit);
    }

    @Test
    void findVisitByIdNullId() {
        Visit visit = visitMapService.findById(null);
        assertNull(visit);
    }

    @Test
    void save() {
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedDogPetType = petTypeMapService.save(cat);
        Owner owner1 = new Owner();
        owner1.setFirstName("Lilianna");
        owner1.setLastName("Weston");
        owner1.setAddress("486 blabla");
        owner1.setCity("Tokyo");
        owner1.setTelephone("666666666");
        Pet liliannasPet = new Pet();
        liliannasPet.setPetType(savedDogPetType);
        liliannasPet.setOwner(owner1);
        liliannasPet.setBirthDate(LocalDate.now());
        liliannasPet.setName("Hope");
        owner1.getPets().add(liliannasPet);
        ownerMapService.save(owner1);
        Visit catVisit = new Visit();
        catVisit.setPet(liliannasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        catVisit.setId(2L);
        visitMapService.save(catVisit);

        assertEquals(catVisit.getId(), cat.getId());
    }

    @Test
    void saveDuplicateId() {
        Long id = 1L;
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedDogPetType = petTypeMapService.save(cat);
        Owner owner1 = new Owner();
        owner1.setFirstName("Lilianna");
        owner1.setLastName("Weston");
        owner1.setAddress("486 blabla");
        owner1.setCity("Tokyo");
        owner1.setTelephone("666666666");
        Pet liliannasPet = new Pet();
        liliannasPet.setPetType(savedDogPetType);
        liliannasPet.setOwner(owner1);
        liliannasPet.setBirthDate(LocalDate.now());
        liliannasPet.setName("Hope");
        owner1.getPets().add(liliannasPet);
        ownerMapService.save(owner1);
        Visit catVisit = new Visit();
        catVisit.setPet(liliannasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        catVisit.setId(id);
        Visit savedVisit = visitMapService.save(catVisit);

        assertEquals(id, savedVisit.getId());
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Long id = 1L;
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedDogPetType = petTypeMapService.save(cat);
        Owner owner1 = new Owner();
        owner1.setFirstName("Lilianna");
        owner1.setLastName("Weston");
        owner1.setAddress("486 blabla");
        owner1.setCity("Tokyo");
        owner1.setTelephone("666666666");
        Pet liliannasPet = new Pet();
        liliannasPet.setPetType(savedDogPetType);
        liliannasPet.setOwner(owner1);
        liliannasPet.setBirthDate(LocalDate.now());
        liliannasPet.setName("Hope");
        owner1.getPets().add(liliannasPet);
        ownerMapService.save(owner1);
        Visit catVisit = new Visit();
        catVisit.setPet(liliannasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        Visit savedVisit = visitMapService.save(catVisit);

        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
        assertEquals(2, visitMapService.findAll().size());
    }

    @Test
    void deleteVisit() {
        visitMapService.delete(visitMapService.findById(visitId));
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void deleteWithWrongObject() {
        Visit visit = new Visit();
        visit.setId(5L);
        visitMapService.delete(visit);
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteWithNullObject() {

        Visit visit = new Visit();
        visitMapService.delete(visit);
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        visitMapService.delete(null);
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteByIdCorrectId() {

        visitMapService.deleteById(visitId);
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        visitMapService.deleteById(5L);
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        visitMapService.deleteById(null);
        assertEquals(1, visitMapService.findAll().size());
    }
}