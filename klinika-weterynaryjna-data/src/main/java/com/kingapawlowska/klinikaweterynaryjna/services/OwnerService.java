package com.kingapawlowska.klinikaweterynaryjna.services;

import com.kingapawlowska.klinikaweterynaryjna.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
