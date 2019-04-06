package com.notabilia.mycards;

import com.notabilia.mycards.controllers.PingController;
import com.notabilia.mycards.models.Ping;
import com.notabilia.mycards.services.PingService;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PingSteps {

    private Boolean apiIsUp;
    private ResponseEntity<Ping> apiResponse;

    private PingController controller;

    private PingService mockService;

    private Instant now;

    @Before
    public void setUp() {
        this.mockService = mock(PingService.class);
        this.controller = new PingController(this.mockService);

        this.now = Instant.now();
    }

    @Given("the API is {string}")
    public void theAPIIs(String status) {
        if(status.equals("up")) {
            this.apiIsUp = Boolean.TRUE;
        } else {
            this.apiIsUp = Boolean.FALSE;
        }
    }

    @When("a user checks the status of the API")
    public void aUserChecksTheStatusOfTheAPI() {
        if(this.apiIsUp) {
            when(this.mockService.getPing()).thenReturn(new Ping(this.now, "local"));
        } else {
            when(this.mockService.getPing()).thenReturn(null);
        }

        this.apiResponse = this.controller.getPing();
    }

    @Then("the ping API endpoint should return a status code of {int}")
    public void theAPIShouldReturnAStatusCodeOfCode(int code) {
        assertThat(this.apiResponse.getStatusCodeValue(), is(equalTo(code)));
    }

    @And("the API should return the time the call was made")
    public void theAPIShouldReturnTheTimeTheCallWasMade() {
        assertThat(this.apiResponse.getBody(), is(notNullValue()));
        assertThat(this.apiResponse.getBody().getDate(), is(equalTo(this.now)));
    }

    @And("the API should return the current running version")
    public void theAPIShouldReturnTheCurrentRunningVersion() {
        assertThat(this.apiResponse.getBody(), is(notNullValue()));
        assertThat(this.apiResponse.getBody().getVersion(), is(equalTo("local")));
    }
}
