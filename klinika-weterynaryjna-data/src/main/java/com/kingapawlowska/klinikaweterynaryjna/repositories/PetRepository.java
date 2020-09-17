package com.kingapawlowska.klinikaweterynaryjna.repositories;

import com.kingapawlowska.klinikaweterynaryjna.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
