
package com.diamondmarket.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Diamond {

    @JsonProperty("diamondId")
    private String diamondId;
    @JsonProperty("diamondType")
    private String diamondType;
    @JsonProperty("carat")
    private String carat;
    @JsonProperty("weight")
    private String weight;

    @JsonProperty("diamondId")
    public String getDiamondId() {
        return diamondId;
    }

    @JsonProperty("diamondId")
    public void setDiamondId(String diamondId) {
        this.diamondId = diamondId;
    }

    @JsonProperty("diamondType")
    public String getDiamondType() {
        return diamondType;
    }

    @JsonProperty("diamondType")
    public void setDiamondType(String diamondType) {
        this.diamondType = diamondType;
    }

    @JsonProperty("carat")
    public String getCarat() {
        return carat;
    }

    @JsonProperty("carat")
    public void setCarat(String carat) {
        this.carat = carat;
    }

    @JsonProperty("weight")
    public String getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(String weight) {
        this.weight = weight;
    }

	@Override
	public String toString() {
		return "Diamond [diamondId=" + diamondId + ", diamondType=" + diamondType + ", carat=" + carat + ", weight="
				+ weight + "]";
	}
    
    
}
