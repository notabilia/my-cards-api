package com.notabilia.mycards.controllers;

import com.notabilia.mycards.models.Ping;
import com.notabilia.mycards.services.PingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class PingControllerTest {

    @Mock
    private PingService mockPingService;

    private PingController controller;

    @BeforeEach
    void setUp() {
        this.controller = new PingController(this.mockPingService);
    }

    @Test
    void getPingCallsService() {
        // Given

        // When
        this.controller.getPing();

        // Then
        verify(this.mockPingService, times(1)).getPing();
    }

    @Test
    void getPingReturnsPingInBody() {
        // Given
        Ping ping = new Ping(Instant.now(), "local");
        when(this.mockPingService.getPing()).thenReturn(ping);

        // When
        ResponseEntity<Ping> response = this.controller.getPing();

        // Then
        assertThat(response.getBody(), is(equalTo(ping)));
    }

    @Test
    void getPingReturns200StatusCode() {
        // Given
        Ping ping = new Ping(Instant.now(), "local");
        when(this.mockPingService.getPing()).thenReturn(ping);

        // When
        ResponseEntity<Ping> response = this.controller.getPing();

        // Then
        assertThat(response.getStatusCodeValue(), is(equalTo(200)));
    }
}