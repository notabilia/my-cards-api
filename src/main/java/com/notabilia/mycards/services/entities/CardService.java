package com.notabilia.mycards.services.entities;

import com.notabilia.mycards.models.entities.Card;
import com.notabilia.mycards.repositories.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class CardService implements EntityService<Card, Integer>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

    private final CardRepository repository;

    @Autowired
    CardService(final CardRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Card> getAll() {
        LOGGER.trace("CardService getAll()");

        try {
            return this.repository.findAll();
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);

            throw e;
        }
    }

    @Override
    public Card getOne(Integer id) {
        LOGGER.trace("CardService getOne()");

        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Card createOne(Card entity) {
        LOGGER.trace("CardService createOne()");

        if(entity.getId() != null && this.repository.existsById(entity.getId())) {
            throw new EntityExistsException();
        }

        return this.repository.save(entity);
    }
}
