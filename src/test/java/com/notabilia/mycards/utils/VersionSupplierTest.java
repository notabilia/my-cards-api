package com.notabilia.mycards.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class VersionSupplierTest {

    private VersionSupplier supplier;

    @Test
    public void getShouldReturnDefaultVersionWhenNullImplementationVersion() {
        // Given
        this.supplier = new VersionSupplier(null);

        // When
        String version = this.supplier.get();

        // Then
        assertThat(version, is(equalTo("local")));
    }

    @Test
    public void getShouldReturnDefaultVersionWhenEmptyImplementationVersion() {
        // Given
        this.supplier = new VersionSupplier("");

        // When
        String version = this.supplier.get();

        // Then
        assertThat(version, is(equalTo("local")));
    }

    @Test
    public void getShouldReturnCorrectVersion() {
        // Given
        String implementationVersion = "1.0.0";
        this.supplier = new VersionSupplier(implementationVersion);

        // When
        String version = this.supplier.get();

        // Then
        assertThat(version, is(equalTo(implementationVersion)));
    }
}