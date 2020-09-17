package com.kingapawlowska.klinikaweterynaryjna.services.map;

import com.kingapawlowska.klinikaweterynaryjna.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SpecialityMapServiceTest {

    private SpecialityMapService specialityMapService;
    private final String specialityDescription = "oculist";

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        specialityMapService.save(Speciality.builder().description(specialityDescription).build());
    }

    @Test
    void findAll() {
        Set<Speciality> specialitySet = specialityMapService.findAll();
        assertEquals(1, specialitySet.size());
    }

    @Test
    void findSpecialityByIdExistingId() {
        Speciality savedSpeciality = specialityMapService.save(Speciality
                .builder().description("dermatologist").build());
        specialityMapService.save(savedSpeciality);
        Speciality searchedSpeciality = specialityMapService.findById(savedSpeciality.getId());
        assertEquals(savedSpeciality.getId(), searchedSpeciality.getId());
    }

    @Test
    void findSpecialityByIdNotExistingId() {
        Speciality speciality2 = Speciality.builder().description("dermatologist").build();
        Speciality searchedSpeciality = specialityMapService.findById(speciality2.getId());
        assertNull(searchedSpeciality);

        Speciality searchedSpeciality2 = specialityMapService.findById(5L);
        assertNull(searchedSpeciality2);
    }

    @Test
    void findSpecialityByIdNullId() {
        Speciality speciality2 = specialityMapService.findById(null);
        assertNull(speciality2);
    }

    @Test
    void save() {
        String description2 = "dermatologist";
        Speciality speciality2 = Speciality.builder().description(description2).build();
        Speciality savedDescription = specialityMapService.save(speciality2);
        assertEquals(description2, savedDescription.getDescription());
    }

    @Test
    void deleteWithCorrectSpecialityObject() {
        int sizeOfSpecialityListBeforeSaving = specialityMapService.findAll().size();
        Speciality savedSpeciality = specialityMapService.save(Speciality
                .builder().description("dermatologist").build());
        Long savedSpecialityId = savedSpeciality.getId();
        specialityMapService.delete(specialityMapService.findById(savedSpecialityId));
        int sizeOfSpecialityListAfterSaving = specialityMapService.findAll().size();
        assertEquals(sizeOfSpecialityListBeforeSaving, sizeOfSpecialityListAfterSaving);
    }

    @Test
    void deleteWithWrongSpecialityObject() {
        Speciality savedSpeciality = specialityMapService.save(Speciality
                .builder().description("dermatologist").build());
        specialityMapService.delete(savedSpeciality);
        assertEquals(1, specialityMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        int sizeOfSpecialityListBeforeDeleting = specialityMapService.findAll().size();
        specialityMapService.delete(null);

        assertEquals(sizeOfSpecialityListBeforeDeleting, specialityMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {
        int sizeOfSpecialityListBeforeSaving = specialityMapService.findAll().size();
        Speciality savedSpeciality = specialityMapService.save(Speciality
                .builder().description("dermatologist").build());
        Long savedSpecialityId = savedSpeciality.getId();
        specialityMapService.deleteById(savedSpecialityId);
        int sizeOfSpecialityListAfterSaving = specialityMapService.findAll().size();
        assertEquals(sizeOfSpecialityListBeforeSaving, sizeOfSpecialityListAfterSaving);
    }

    @Test
    void deleteWithWrongId() {
        Speciality speciality2 = new Speciality(specialityDescription);
        specialityMapService.deleteById(speciality2.getId());
        assertEquals(1, specialityMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {
        specialityMapService.deleteById(null);
        assertEquals(1, specialityMapService.findAll().size());
    }


}