package com.cloudbees.model;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Product Catalog
 */

@Entity
@Schema(name = "Product", description = "Product Catalog")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-25T09:53:45.759072900+05:30[Asia/Calcutta]")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	private String name;

	private String description;

	private Double price;

	private Integer quantityAvailable;

	public Product productId(Long productId) {
		this.productId = productId;
		return this;
	}

	/**
	 * Unique Identifier for the product
	 * 
	 * @return productId
	 */

	@Schema(name = "productId", accessMode = Schema.AccessMode.READ_ONLY, description = "Unique Identifier for the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productId")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Product Name
	 * 
	 * @return name
	 */

	@Schema(name = "name", description = "Product Name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Description of the product
	 * 
	 * @return description
	 */

	@Schema(name = "description", description = "Description of the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product price(Double price) {
		this.price = price;
		return this;
	}

	/**
	 * Price of the product minimum: 0
	 * 
	 * @return price
	 */
	@DecimalMin("0")
	@Schema(name = "price", description = "Price of the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product quantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
		return this;
	}

	/**
	 * Available quantity of the product minimum: 0
	 * 
	 * @return quantityAvailable
	 */
	@Min(0)
	@Schema(name = "quantityAvailable", description = "Available quantity of the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("quantityAvailable")
	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Product product = (Product) o;
		return Objects.equals(this.productId, product.productId) && Objects.equals(this.name, product.name)
				&& Objects.equals(this.description, product.description) && Objects.equals(this.price, product.price)
				&& Objects.equals(this.quantityAvailable, product.quantityAvailable);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, name, description, price, quantityAvailable);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Product {\n");
		sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    quantityAvailable: ").append(toIndentedString(quantityAvailable)).append("\n");
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
