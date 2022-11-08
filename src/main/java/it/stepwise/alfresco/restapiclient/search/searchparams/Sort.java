package it.stepwise.alfresco.restapiclient.search.searchparams;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "sort")
public class Sort {
    
    private Type type;
    private String field;
    private Boolean ascending;
    
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public String getField() {
        return field;
    }
    
    public void setField(String field) {
        this.field = field;
    }
    
    public Boolean getAscending() {
        return ascending;
    }
    
    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }
    
    public Sort(Type type, String field, Boolean ascending) {
        this.type = type;
        this.field = field;
        this.ascending = ascending;
    }
    
    public Sort(Type type, String field) {
        this.type = type;
        this.field = field;
        this.ascending = true;
    }
    
    public Sort(String field) {
        this.type = Type.FIELD;
        this.field = field;
        this.ascending = true;
    }
    
    public enum Type {
        FIELD("FIELD"),
        DOCUMENT("DOCUMENT"),
        SCORE("SCORE");
    
        private final String value;
    
        public String getValue() {
            return value;
        }
    
        private Type(String value) {
            this.value = value;
        }
    }
}
