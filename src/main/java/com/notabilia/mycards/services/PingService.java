package com.notabilia.mycards.services;

import com.notabilia.mycards.models.Ping;
import com.notabilia.mycards.utils.VersionSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingService.class);

    private final VersionSupplier versionSupplier;

    @Autowired
    PingService(VersionSupplier versionSupplier) {
        this.versionSupplier = versionSupplier;
    }

    public Ping getPing() {
        LOGGER.info("PingService getPing()");

        return new Ping(
                Instant.now(),
                this.versionSupplier.get()
        );
    }
}
