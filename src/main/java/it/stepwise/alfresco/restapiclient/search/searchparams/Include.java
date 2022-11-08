package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "include")
public enum Include {
    @JsonProperty("allowableOperations")
    ALLOWABLE_OPERATIONS("allowableOperations"),
    @JsonProperty("aspectNames")
    ASPECT_NAMES("aspectNames"),
    @JsonProperty("isLink")
    IS_LINK("isLink"),
    @JsonProperty("path")
    PATH("path"),
    @JsonProperty("properties")
    PROPERTIES("properties"),
    @JsonProperty("association")
    ASSOCIATION("association");

    private final String value;

    public String getValue() {
        return value;
    }

    private Include(String value) {
        this.value = value;
    }
}
