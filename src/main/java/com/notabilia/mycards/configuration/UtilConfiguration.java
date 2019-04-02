package com.notabilia.mycards.configuration;

import com.notabilia.mycards.utils.ProtocolSupplier;
import com.notabilia.mycards.utils.VersionSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class UtilConfiguration {

    @Bean
    public VersionSupplier versionSupplierBean() {
        String implementationVersion = Optional.ofNullable(this.getClass().getPackage().getImplementationVersion()).orElse(null);

        return new VersionSupplier(implementationVersion);
    }

    @Bean
    public ProtocolSupplier protocolSupplierBean() {
        return new ProtocolSupplier(ProtocolSupplier.SecurityStrategy.INSECURE);
    }
}
