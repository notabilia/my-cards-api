package com.notabilia.mycards.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class VersionSupplier implements Supplier<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionSupplier.class);

    private static final String DEFAULT_VERSION = "local";

    private final String implementationVersion;

    public VersionSupplier(final String implementationVersion) {
        this.implementationVersion = implementationVersion;
    }

    @Override
    public String get() {
        if (this.implementationVersion == null || this.implementationVersion.isEmpty()) {
            LOGGER.debug("Implementation version is NULL, returning DEFAULT version");

            return DEFAULT_VERSION;
        }

        return this.implementationVersion;
    }
}