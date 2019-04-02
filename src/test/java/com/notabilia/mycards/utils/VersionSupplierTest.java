package com.notabilia.mycards.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class VersionSupplierTest {

    private VersionSupplier supplier;

    @Test
    void getShouldReturnDefaultVersionWhenNullImplementationVersion() {
        // Given
        this.supplier = new VersionSupplier(null);

        // When
        String version = this.supplier.get();

        // Then
        assertThat(version, is(equalTo("local")));
    }

    @Test
    void getShouldReturnDefaultVersionWhenEmptyImplementationVersion() {
        // Given
        this.supplier = new VersionSupplier("");

        // When
        String version = this.supplier.get();

        // Then
        assertThat(version, is(equalTo("local")));
    }

    @Test
    void getShouldReturnCorrectVersion() {
        // Given
        String implementationVersion = "1.0.0";
        this.supplier = new VersionSupplier(implementationVersion);

        // When
        String version = this.supplier.get();

        // Then
        assertThat(version, is(equalTo(implementationVersion)));
    }
}