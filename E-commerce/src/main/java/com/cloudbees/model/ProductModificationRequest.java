package com.cloudbees.model;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ProductModificationRequest
 */

@JsonTypeName("post_api_product_product_id_applyModification_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-25T09:53:45.759072900+05:30[Asia/Calcutta]")
public class ProductModificationRequest {

	private String modificationType;

	private Double modificationValue;

	public ProductModificationRequest modificationType(String modificationType) {
		this.modificationType = modificationType;
		return this;
	}

	/**
	 * Get modificationType
	 * 
	 * @return modificationType
	 */

	@Schema(name = "modificationType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("modificationType")
	public String getModificationType() {
		return modificationType;
	}

	public void setModificationType(String modificationType) {
		this.modificationType = modificationType;
	}

	public ProductModificationRequest modificationValue(Double modificationValue) {
		this.modificationValue = modificationValue;
		return this;
	}

	/**
	 * Get modificationValue
	 * 
	 * @return modificationValue
	 */

	@Schema(name = "modificationValue", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("modificationValue")
	public Double getModificationValue() {
		return modificationValue;
	}

	public void setModificationValue(Double modificationValue) {
		this.modificationValue = modificationValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductModificationRequest ProductModificationRequest = (ProductModificationRequest) o;
		return Objects.equals(this.modificationType, ProductModificationRequest.modificationType)
				&& Objects.equals(this.modificationValue, ProductModificationRequest.modificationValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(modificationType, modificationValue);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProductModificationRequest {\n");
		sb.append("    modificationType: ").append(toIndentedString(modificationType)).append("\n");
		sb.append("    modificationValue: ").append(toIndentedString(modificationValue)).append("\n");
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
