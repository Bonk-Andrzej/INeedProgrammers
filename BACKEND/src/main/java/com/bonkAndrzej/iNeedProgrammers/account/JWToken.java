package com.bonkAndrzej.iNeedProgrammers.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to return as body in JWT Authentication.
 */

@Getter @Setter
@AllArgsConstructor
public class JWToken {

    @JsonProperty("id_token")
    private String idToken;
}
