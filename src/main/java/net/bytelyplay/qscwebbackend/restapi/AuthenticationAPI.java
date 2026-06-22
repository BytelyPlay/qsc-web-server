package net.bytelyplay.qscwebbackend.restapi;

import lombok.extern.apachecommons.CommonsLog;
import net.bytelyplay.qscwebbackend.constants.RequestResponses;
import net.bytelyplay.qscwebbackend.utils.AuthenticationHelpers;
import net.bytelyplay.qscwebbackend.utils.enums.RefreshTokenOfferType;
import net.bytelyplay.qscwebbackend.utils.offers.refreshtokens.RefreshTokenOffer;
import net.bytelyplay.qscwebbackend.utils.tokens.RefreshToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.Optional;

@CommonsLog
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationAPI {
    private static final ObjectMapper MAP =
            new ObjectMapper();

    // TODO: clean up

    /**
     * Try to get a refresh token.
     *
     * @param requestBody The request body, should look like: {
     *                    "offer_type": "offer-type (e.g. password)"
     *                    "credentials": {
     *                      "username": "the username",
     *                      "offer_value": "the value of the offer (e.g. password)"
     *                    }
     * }
     * @return Some form of failure or success which would look like: {
     *     "refresh_token": "token"
     * }
     */
    @PostMapping("/refreshtoken")
    public ResponseEntity<JsonNode> refreshToken(@RequestBody ObjectNode requestBody) {
        JsonNode offerTypeNode = requestBody.get("offer_type");
        JsonNode credentialsJsonNode = requestBody.get("credentials");

        JsonNode usernameNode = credentialsJsonNode.get("username");
        JsonNode offerValueNode = credentialsJsonNode.get("offer_value");

        if (!allNodesValid(offerTypeNode, credentialsJsonNode, usernameNode, offerValueNode))
            return RequestResponses.BAD_REQUEST_BODY;

        return getRefreshToken(
                offerTypeNode, usernameNode,
                offerValueNode
        );
    }

    private ResponseEntity<JsonNode> successfulAuthentication(RefreshToken token) {
        ObjectNode root = MAP.createObjectNode();

        root.put("refresh_token", token.getTokenAsString());

        return ResponseEntity.ok()
                .body(root);
    }

    private boolean allNodesValid(
            JsonNode offerTypeNode,
            JsonNode credentialsJsonNode,
            JsonNode usernameNode,
            JsonNode offerValueNode
    ) {
        return !usernameNode.isString() ||
                !offerValueNode.isString() || !offerTypeNode.isString()
                || !credentialsJsonNode.isObject();
    }
    private ResponseEntity<JsonNode> getRefreshToken(
            JsonNode offerTypeNode,
            JsonNode usernameNode,
            JsonNode offerValueNode
    ) {
        Optional<RefreshTokenOfferType> optOfferType =
                RefreshTokenOfferType.getOfferFromString(
                        offerTypeNode.asString()
                );
        if (optOfferType.isEmpty())
            return RequestResponses.BAD_OFFER_TYPE;

        RefreshTokenOfferType offerType = optOfferType.orElseThrow();

        Optional<RefreshToken> optRefreshToken =
                AuthenticationHelpers
                        .getInstance()
                        .getRefreshTokenFromOffer(
                                offerType,
                                offerValueNode.asString(),
                                usernameNode.asString()
                        );
        if (optRefreshToken.isPresent())
            return successfulAuthentication(optRefreshToken.orElseThrow());
        else
            return RequestResponses.OFFER_NOT_VALID;
    }
}