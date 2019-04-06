package com.notabilia.mycards.configuration;

import com.notabilia.mycards.utils.ProtocolSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URL;
import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final ProtocolSupplier protocolSupplier;

    @Autowired
    public SwaggerConfiguration(final ProtocolSupplier protocolSupplier) {
        this.protocolSupplier = protocolSupplier;
    }

    @Bean
    public Docket latestDocumentationPlugin() {
        return new VersionedDocket(this.protocolSupplier.get(), "latest");
    }

    @Bean
    public Docket v10DocumentationPlugin() {
        return new VersionedDocket(this.protocolSupplier.get(), "1.0");
    }

    private static class VersionedDocket extends Docket {

        private VersionedDocket(String protocol, String version) {
            super(DocumentationType.SWAGGER_2);
            super.groupName(version)
                    .protocols(Collections.singleton(protocol))
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.notabilia.mycards"))
                    .paths(regex("/api/" + version + "/.*"))
                    .build()
                    .apiInfo(getApiInfo(version))
                    .pathProvider(new BasePathAwareRelativeProvider("/api/" + version))
                    .directModelSubstitute(URL.class, String.class)
                    .useDefaultResponseMessages(false);
        }

        private ApiInfo getApiInfo(String version) {
            return new ApiInfo(
                    "My Cards - REST API",
                    "REST API to access My Cards",
                    version,
                    "https://github.com/notabilia/my-cards-api",
                    new Contact("Notabilia support", "https://github.com/notabilia/my-cards-api/issues", "developers@notabilia.dev"),
                    "",
                    "",
                    Collections.emptySet()
            );
        }
    }

    private static class BasePathAwareRelativeProvider extends AbstractPathProvider {

        private final String bathPath;

        private BasePathAwareRelativeProvider(final String basePath) {
            this.bathPath = basePath;
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");

            return Paths.removeAdjacentForwardSlashes(uriComponentsBuilder.path(operationPath.replaceFirst(this.bathPath, "")).build().toString());
        }

        @Override
        protected String applicationPath() {
            return this.bathPath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }
    }
}
