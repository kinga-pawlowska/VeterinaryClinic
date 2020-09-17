package com.kingapawlowska.klinikaweterynaryjna.repositories;

import com.kingapawlowska.klinikaweterynaryjna.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastname);

    List<Owner> findAllByLastNameLike(String lastName);
}
