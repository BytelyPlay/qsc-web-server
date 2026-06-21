package net.bytelyplay.qscwebbackend.constants;

import org.springframework.http.ResponseEntity;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

public class RequestResponses {
    /**
     * Something is wrong with the structure or types of the request Json.
     */
    public static final ResponseEntity<JsonNode> BAD_REQUEST_BODY =
            ResponseEntity.badRequest()
            .body(failureJson("bad_request_body"));
    /**
     * Offer type doesn't exist.
     */
    public static final ResponseEntity<JsonNode> BAD_OFFER_TYPE =
            ResponseEntity.badRequest()
                    .body(failureJson("bad_offer_type"));
    /**
     * Offer was wrong (e.g. wrong password)
     */
    public static final ResponseEntity<JsonNode> OFFER_NOT_VALID =
            ResponseEntity.badRequest()
                    .body(failureJson("offer_not_valid"));

    private static final ObjectMapper MAP =
            new ObjectMapper();
    private static ObjectNode failureJson(String what) {
        ObjectNode failureNode = MAP.createObjectNode();
        failureNode.put("what", what);

        return failureNode;
    }

}
