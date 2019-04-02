package com.notabilia.mycards.services;

import com.notabilia.mycards.models.Ping;
import com.notabilia.mycards.utils.VersionSupplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class PingServiceTest {

    @Mock
    private VersionSupplier mockVersionSupplier;

    private PingService service;

    @BeforeEach
    void setUp() {
        this.service = new PingService(this.mockVersionSupplier);
    }

    @Test
    void getPingCallsVersionSupplier() {
        // Given

        // When
        this.service.getPing();

        // Then
        verify(this.mockVersionSupplier, times(1)).get();
    }

    @Test
    void getPingReturnsPingWithCorrectVersion() {
        // Given
        String version = "local";

        // When
        when(this.mockVersionSupplier.get()).thenReturn(version);
        Ping ping = this.service.getPing();

        // Then
        assertThat(ping.getVersion(), is(equalTo(version)));
    }

    @Test
    void getPingReturnsPingWithCorrectDate() {
        // Given
        when(this.mockVersionSupplier.get()).thenReturn("local");

        // When
        Instant then = Instant.now();
        Ping ping = this.service.getPing();

        // Then
        Instant now = Instant.now();
        assertThat(Duration.between(ping.getDate(), now), is(lessThanOrEqualTo(Duration.between(then, now))));
    }
}