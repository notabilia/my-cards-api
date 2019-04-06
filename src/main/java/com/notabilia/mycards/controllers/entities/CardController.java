package com.notabilia.mycards.controllers.entities;

import com.notabilia.mycards.models.entities.Card;
import com.notabilia.mycards.services.entities.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/1.0")
@Api(tags = {
        "Card Controller"
})
public class CardController implements EntityController<Card, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    private final CardService service;

    @Autowired
    public CardController(final CardService service) {
        this.service = service;
    }

    @GetMapping(path = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all cards")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "[Successful] Request OK, all cards gathered"),
            @ApiResponse(code = 500, message = "[Failure] Internal ERROR")
    })
    public ResponseEntity<Iterable<Card>> getAll() {
        LOGGER.trace("CardController getAll()");

        try {
            return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/card/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a card")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "[Successful] Request OK, card gathered"),
            @ApiResponse(code = 404, message = "[Failure] Request BAD, card does not exist"),
            @ApiResponse(code = 500, message = "[Failure] Internal ERROR")
    })
    public ResponseEntity<Card> getOne(@RequestParam("id") Integer id) {
        LOGGER.trace("CardController getOne()");

        try {
            return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LOGGER.debug("Entity not found", e);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/card", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a cards")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "[Successful] Request OK, card created"),
            @ApiResponse(code = 400, message = "[Failure] Request BAD, card already exists"),
            @ApiResponse(code = 500, message = "[Failure] Internal ERROR")
    })
    public ResponseEntity<Card> createOne(@RequestBody Card entity) {
        LOGGER.trace("CardController createOne()");

        try {
            return new ResponseEntity<>(this.service.createOne(entity), HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            LOGGER.debug("Entity already exists", e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
