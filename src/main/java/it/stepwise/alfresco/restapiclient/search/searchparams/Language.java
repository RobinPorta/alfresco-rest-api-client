package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "language")
public enum Language {
    @JsonProperty("afts")
    AFTS("afts"),
    @JsonProperty("lucene")
    LUCENE("lucene"),
    @JsonProperty("cmis")
    CMIS("cmis");

    private final String value;

    public String getValue() {
        return value;
    }

    private Language(String value) {
        this.value = value;
    }

}
