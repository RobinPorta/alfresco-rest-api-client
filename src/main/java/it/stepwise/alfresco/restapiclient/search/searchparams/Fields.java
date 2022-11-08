package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "fields")
public enum Fields {
    @JsonProperty("id")
    ID("id"),
    @JsonProperty("name")
    NAME("name"),
    @JsonProperty("search")
    SEARCH("search");

    private final String value;

    public String getValue() {
        return value;
    }

    private Fields(String value) {
        this.value = value;
    }
}
