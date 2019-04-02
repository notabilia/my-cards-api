package com.notabilia.mycards.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class ProtocolSupplier implements Supplier<String> {

    private final static Logger LOGGER  = LoggerFactory.getLogger(ProtocolSupplier.class);

    private static final String DEFAULT_PROTOCOL = "https";

    private final SecurityStrategy securityStrategy;

    public ProtocolSupplier(SecurityStrategy securityStrategy) {
        this.securityStrategy = securityStrategy;
    }

    @Override
    public String get() {
        if (this.securityStrategy == null) {
            return DEFAULT_PROTOCOL;
        }

        LOGGER.info(this.securityStrategy.toString());

        StringBuilder protocol = new StringBuilder();

        switch (this.securityStrategy) {
            case SECURE:
                protocol.append(DEFAULT_PROTOCOL);
                break;
            case INSECURE:
                protocol.append("http");
                break;
        }

        return protocol.toString();
    }

    public enum SecurityStrategy {
        SECURE,
        INSECURE
    }
}
