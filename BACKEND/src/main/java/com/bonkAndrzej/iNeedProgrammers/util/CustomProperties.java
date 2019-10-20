package com.bonkAndrzej.iNeedProgrammers.util.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Configuration
@Validated
@Getter @Setter
@ConfigurationProperties("custom")
public class CustomProperties {


    @NotBlank
    private String initialAdminEmail;

    @NotBlank
    private String initialAdminPassword;

    @NotBlank
    private String base64Secret;

}
