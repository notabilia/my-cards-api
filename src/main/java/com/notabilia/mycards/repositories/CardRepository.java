package com.notabilia.mycards.repositories;

import com.notabilia.mycards.models.entities.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {
}
