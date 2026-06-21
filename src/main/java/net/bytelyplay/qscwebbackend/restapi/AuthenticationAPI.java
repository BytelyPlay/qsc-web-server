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
@RequestMapping("/api/v1")
public class AuthenticationAPI {
    private static final ObjectMapper MAP =
            new ObjectMapper();

    // TODO: Make constants
    @PostMapping("/authenticate")
    public ResponseEntity<JsonNode> authenticate(@RequestBody ObjectNode requestBody) {
        JsonNode credentialsJsonNode = requestBody.get("credentials");

        JsonNode usernameNode = credentialsJsonNode.get("username");
        JsonNode passwordNode = credentialsJsonNode.get("password");

        if (!credentialsJsonNode.isObject())
            return ResponseEntity
                    .badRequest()
                    .body(failureJson("bad_request_body"));
        if (!usernameNode.isString() || !passwordNode.isString())
            return ResponseEntity.badRequest()
                    .body(failureJson("bad_request_body"));


    }
    private ObjectNode failureJson(String what) {
        ObjectNode failureNode = MAP.createObjectNode();

        failureNode.put("what", what);
    }
}
