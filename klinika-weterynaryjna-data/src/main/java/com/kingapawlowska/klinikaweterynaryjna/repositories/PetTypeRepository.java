package com.kingapawlowska.klinikaweterynaryjna.repositories;

import com.kingapawlowska.klinikaweterynaryjna.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
