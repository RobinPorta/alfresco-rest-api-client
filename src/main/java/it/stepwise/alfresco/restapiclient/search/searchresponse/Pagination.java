package it.stepwise.alfresco.restapiclient.search.searchresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "pagination")
public class Pagination {
    @JsonProperty("count")
    private Long count;
    @JsonProperty("hasMoreItems")
    private Boolean hasMoreItems;
    @JsonProperty("totalItems")
    private Long totalItems;
    @JsonProperty("skipCount")
    private Long skipCount;
    @JsonProperty("maxItems")
    private Long maxItems;
    
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public Boolean getHasMoreItems() {
        return hasMoreItems;
    }
    public void setHasMoreItems(Boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
    }
    public Long getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }
    public Long getSkipCount() {
        return skipCount;
    }
    public void setSkipCount(Long skipCount) {
        this.skipCount = skipCount;
    }
    public Long getMaxItems() {
        return maxItems;
    }
    public void setMaxItems(Long maxItems) {
        this.maxItems = maxItems;
    }

}
