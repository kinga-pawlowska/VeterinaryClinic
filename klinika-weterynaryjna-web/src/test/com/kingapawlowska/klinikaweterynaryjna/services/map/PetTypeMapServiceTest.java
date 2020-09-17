package com.kingapawlowska.klinikaweterynaryjna.services.map;

import com.kingapawlowska.klinikaweterynaryjna.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    private PetTypeMapService petTypeMapService;

    private final Long petTypeId = 1L;
    private final String petTypeName = "Fox";

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().id(petTypeId).name(petTypeName).build());
    }

    @Test
    void findAllPetTypes() {
        Set<PetType> petTypeSet = petTypeMapService.findAll();
        assertEquals(1, petTypeSet.size());
    }

    @Test
    void findPetTypeByIdExistingId() {
        PetType petType = petTypeMapService.findById(petTypeId);
        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void findPetTypeByIdNotExistingId() {
        PetType petType = petTypeMapService.findById(5L);
        assertNull(petType);
    }

    @Test
    void findPetTypeByIdNullId() {
        PetType petType = petTypeMapService.findById(null);
        assertNull(petType);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        String name = "fox";
        PetType petType2 = PetType.builder().id(id).name(name).build();
        PetType savedPetType = petTypeMapService.save(petType2);
        assertEquals(id, savedPetType.getId());
    }

    @Test
    void saveDuplicateId() {
        Long id = 1L;
        PetType petType2 = PetType.builder().id(id).build();
        PetType savedPetType = petTypeMapService.save(petType2);
        assertEquals(id, savedPetType.getId());
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        PetType savedPetType = petTypeMapService.save(PetType.builder().build());
        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
        assertEquals(2, petTypeMapService.findAll().size());
    }

    @Test
    void deletePet() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteWithWrongObject() {

        PetType petType = PetType.builder().id(5L).build();

        petTypeMapService.delete(petType);

        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteWithNullObject() {

        PetType petType = PetType.builder().build();

        petTypeMapService.delete(petType);

        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        petTypeMapService.delete(null);

        assertEquals(1, petTypeMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        petTypeMapService.deleteById(petTypeId);

        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        petTypeMapService.deleteById(5L);

        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        petTypeMapService.deleteById(null);

        assertEquals(1, petTypeMapService.findAll().size());
    }
}