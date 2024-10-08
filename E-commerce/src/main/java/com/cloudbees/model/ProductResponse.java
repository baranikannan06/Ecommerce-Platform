package com.cloudbees.model;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ProductResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-25T09:53:45.759072900+05:30[Asia/Calcutta]")
public class ProductResponse {

	private Product product;

	private Double modifiedPrice;

	public ProductResponse product(Product product) {
		this.product = product;
		return this;
	}

	/**
	 * Get product
	 * 
	 * @return product
	 */
	@Valid
	@Schema(name = "product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("product")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductResponse modifiedPrice(Double modifiedPrice) {
		this.modifiedPrice = modifiedPrice;
		return this;
	}

	/**
	 * Get modifiedPrice
	 * 
	 * @return modifiedPrice
	 */

	@Schema(name = "modifiedPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("modifiedPrice")
	public Double getModifiedPrice() {
		return modifiedPrice;
	}

	public void setModifiedPrice(Double modifiedPrice) {
		this.modifiedPrice = modifiedPrice;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductResponse productResponse = (ProductResponse) o;
		return Objects.equals(this.product, productResponse.product)
				&& Objects.equals(this.modifiedPrice, productResponse.modifiedPrice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, modifiedPrice);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProductResponse {\n");
		sb.append("    product: ").append(toIndentedString(product)).append("\n");
		sb.append("    modifiedPrice: ").append(toIndentedString(modifiedPrice)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
