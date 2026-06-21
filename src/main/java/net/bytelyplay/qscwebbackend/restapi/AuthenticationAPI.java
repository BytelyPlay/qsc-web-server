package net.bytelyplay.qscwebbackend.restapi;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

@CommonsLog
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationAPI {
    private static final ObjectMapper MAP =
            new ObjectMapper();

    // TODO: Make constants
    @PostMapping("/refreshtoken")
    public ResponseEntity<JsonNode> authenticate(@RequestBody ObjectNode requestBody) {
        JsonNode exchangeType = requestBody.get("exchange_type");

        JsonNode credentialsJsonNode = requestBody.get("credentials");

        JsonNode usernameNode = credentialsJsonNode.get("username");
        JsonNode offerValueNode = credentialsJsonNode.get("offer_value");

        if (!usernameNode.isString() ||
                !offerValueNode.isString() || !exchangeType.isString()
                || !credentialsJsonNode.isObject())
            return ResponseEntity.badRequest()
                    .body(failureJson("bad_request_body"));

        return null;
    }
    private ObjectNode failureJson(String what) {
        ObjectNode failureNode = MAP.createObjectNode();
        failureNode.put("what", what);

        return failureNode;
    }
}
