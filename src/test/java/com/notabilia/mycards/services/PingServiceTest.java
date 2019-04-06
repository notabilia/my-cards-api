package com.notabilia.mycards.services;

import com.notabilia.mycards.models.Ping;
import com.notabilia.mycards.utils.VersionSupplier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PingServiceTest {

    @Mock
    private VersionSupplier mockVersionSupplier;

    private PingService service;

    @Before
    public void setUp() {
        this.service = new PingService(this.mockVersionSupplier);
    }

    @Test
    public void getPingCallsVersionSupplier() {
        // Given

        // When
        this.service.getPing();

        // Then
        verify(this.mockVersionSupplier, times(1)).get();
    }

    @Test
    public void getPingReturnsPingWithCorrectVersion() {
        // Given
        String version = "local";

        // When
        when(this.mockVersionSupplier.get()).thenReturn(version);
        Ping ping = this.service.getPing();

        // Then
        assertThat(ping.getVersion(), is(equalTo(version)));
    }

    @Test
    public void getPingReturnsPingWithCorrectDate() {
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