package com.kingapawlowska.klinikaweterynaryjna.services.map;

import com.kingapawlowska.klinikaweterynaryjna.model.Speciality;
import com.kingapawlowska.klinikaweterynaryjna.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {

    VetMapService vetMapService;

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialityMapService());
        Vet vet = new Vet();
        vet.setId(1L);
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(new Speciality("Oculist"));
        vet.setSpecialities(specialities);
        vetMapService.save(vet);
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = vetMapService.findAll();
        assertEquals(1, vetSet.size());
    }

    @Test
    void findVetByIdExistingId() {
        Vet vet = new Vet();
        vet.setId(5L);
        vetMapService.save(vet);
        Vet searchedVet = vetMapService.findById(vet.getId());
        assertEquals(vet.getId(), searchedVet.getId());
    }

    @Test
    void findVetByIdNotExistingId() {
        Vet searchedVet = vetMapService.findById(5L);
        assertNull(searchedVet);
    }

    @Test
    void findVetTypeByIdNullId() {
        Vet vet = vetMapService.findById(null);
        assertNull(vet);
    }

    @Test
    void save() {
        Long id = 5L;
        Vet vet = new Vet();
        vet.setId(id);
        Vet savedVetType = vetMapService.save(vet);
        assertEquals(id, savedVetType.getId());
    }

    @Test
    void saveDuplicateId() {
        Long id = 1L;
        Vet vet = new Vet();
        vet.setId(id);
        Vet savedVet = vetMapService.save(vet);
        assertEquals(id, savedVet.getId());
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Vet vet = new Vet();
        vet.setId(null);
        Vet savedVet = vetMapService.save(vet);
        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
        assertEquals(2, vetMapService.findAll().size());
    }

    @Test
    void deleteVet() {
        Long id = 2L;
        Vet vet = new Vet();
        vet.setId(id);
        vetMapService.delete(vetMapService.findById(vet.getId()));
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteWithWrongObject() {
        Long id = 2L;
        Vet vet = new Vet();
        vet.setId(id);
        vetMapService.delete(vet);
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteWithNullObject() {
        Vet vet = Vet.builder().build();
        vetMapService.delete(vet);
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        vetMapService.delete(null);
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteByIdCorrectId() {
        Long id = 2L;
        Vet vet = new Vet();
        vet.setId(id);
        vetMapService.save(vet);

        vetMapService.deleteById(vet.getId());
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {
        vetMapService.deleteById(5L);
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {
        vetMapService.deleteById(null);
        assertEquals(1, vetMapService.findAll().size());
    }
}