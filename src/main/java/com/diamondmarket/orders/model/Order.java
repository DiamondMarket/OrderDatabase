
package com.diamondmarket.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Order {

    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("diamond")
    private Diamond diamond;
    @JsonProperty("payment")
    private Payment payment;

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("diamond")
    public Diamond getDiamond() {
        return diamond;
    }

    @JsonProperty("diamond")
    public void setDiamond(Diamond diamond) {
        this.diamond = diamond;
    }

    @JsonProperty("payment")
    public Payment getPayment() {
        return payment;
    }

    @JsonProperty("payment")
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

	@Override
	public String toString() {
		return "Orders [quantity=" + quantity + ", diamond=" + diamond + ", payment=" + payment + "]";
	}

    
}
