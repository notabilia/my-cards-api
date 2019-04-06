package com.notabilia.mycards;

import com.notabilia.mycards.controllers.entities.CardController;
import com.notabilia.mycards.models.entities.Card;
import com.notabilia.mycards.services.entities.CardService;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateCardsSteps {

    private Card card;
    private Boolean exists;

    private CardController controller;

    private ResponseEntity<Card> apiResponse;

    private CardService mockService;

    @Before
    public void setUp() {
        this.mockService = mock(CardService.class);
    }

    @When("a user creates a card with no attributes")
    public void aUserCreatesACardWithNoAttributes() {
        this.card = new Card(0, null, null, null, null);
    }

    @Then("a card with no attributes should be returned")
    public void aCardWithNoAttributesShouldBeReturned() {
        assertThat(this.card, is(notNullValue()));
    }

    @When("a user creates a card with a description attribute of {string}")
    public void aUserCreatesACardWithADescriptionAttributeOf(String description) {
        this.card = new Card(0, description, null, null, null);
    }

    @Then("the cards description should be equal to {string}")
    public void theCardsDescriptionShouldBeEqualTo(String description) {
        assertThat(this.card.getDescription(), is(equalTo(description)));
    }

    @When("a user creates a card with a purchase date attribute of {string}")
    public void aUserCreatesACardWithAPurchaseDateAttributeOf(String date) {
        this.card = new Card(0, null, LocalDate.parse(date), null, null);
    }

    @Then("the cards purchase day should be equal to {int}")
    public void theCardsPurchaseDayShouldBeEqualTo(int day) {
        assertThat(this.card.getPurchaseDate().getDayOfMonth(), is(equalTo(day)));
    }

    @And("the cards purchase month should be equal to {int}")
    public void theCardsPurchaseMonthShouldBeEqualTo(int month) {
        assertThat(this.card.getPurchaseDate().getMonthValue(), is(equalTo(month)));
    }

    @And("the cards purchase year should be equal to {int}")
    public void theCardsPurchaseYearShouldBeEqualTo(int year) {
        assertThat(this.card.getPurchaseDate().getYear(), is(equalTo(year)));
    }

    @When("a user creates a card with a merchant attribute of {string}")
    public void aUserCreatesACardWithAMerchantAttributeOf(String merchant) {
        this.card = new Card(0, null, null, merchant, null);
    }

    @Then("the cards merchant should be equal to {string}")
    public void theCardsMerchantShouldBeEqualTo(String merchant) {
        assertThat(this.card.getMerchant(), is(equalTo(merchant)));
    }

    @When("a user creates a card with a price attribute of {double}")
    public void aUserCreatesACardWithAPriceAttributeOf(double price) {
        this.card = new Card(0, null, null, null, price);
    }

    @Then("the cards price should be equal to {double}")
    public void theCardsPriceShouldBeEqualTo(double price) {
        assertThat(this.card.getPrice(), is(equalTo(price)));
    }

    @Given("the cards existence is {string}")
    public void theCardSExistenceIsExists(String exists) {
        this.exists = Boolean.parseBoolean(exists);
    }

    @When("a user creates the card")
    public void aUserCreatesTheCard() {
        this.controller = new CardController(this.mockService);

        if(exists) {
            when(this.mockService.createOne(Mockito.any(Card.class))).thenThrow(new EntityExistsException());
        } else {
            when(this.mockService.createOne(Mockito.any(Card.class))).thenReturn(new Card());
        }

        this.apiResponse = this.controller.createOne(new Card());
    }

    @Then("the API should return a status code of {int}")
    public void theAPIShouldReturnAStatusCodeOfCode(int code) {
        assertThat(this.apiResponse.getStatusCodeValue(), is(equalTo(code)));
    }
}