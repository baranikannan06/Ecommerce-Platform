package com.cloudbees.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import com.cloudbees.model.Product;
import com.cloudbees.model.ProductModificationRequest;
import com.cloudbees.model.ProductResponse;

/**
 * A delegate to be called by the {@link ProductAPIController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-25T10:40:44.336476700+05:30[Asia/Calcutta]")
public interface ProductAPIDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /api/product : Add a new Product
     * creates a new Product
     *
     * @param product  (optional)
     * @return Created (status code 201)
     *         or Missing Required Information (status code 400)
     *         or Unauthorized (status code 401)
     *         or Not Found (status code 404)
     *         or Method Not Allowed (status code 405)
     *         or Internal Server Error (status code 500)
     * @see ProductAPI#createProduct
     */
    default ResponseEntity<Product> createProduct(Product product) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"quantityAvailable\" : 0, \"productId\" : 0, \"price\" : 0.6027456183070403, \"name\" : \"name\", \"description\" : \"description\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /api/products/{product-id} : Delete a Product
     * To delete an existing product.  
     *
     * @param productId Product ID (required)
     * @return Product Deleted Successfully (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Internal Server Error (status code 500)
     * @see ProductAPI#deleteProduct
     */
    default ResponseEntity<String> deleteProduct(Long productId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /api/products : Get all products
     * Retrive all products
     *
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Not Found (status code 404)
     *         or Method Not Allowed (status code 405)
     *         or Internal Server Error (status code 500)
     * @see ProductAPI#getAllProducts
     */
    default ResponseEntity<List<Product>> getAllProducts() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"quantityAvailable\" : 0, \"productId\" : 0, \"price\" : 0.6027456183070403, \"name\" : \"name\", \"description\" : \"description\" }, { \"quantityAvailable\" : 0, \"productId\" : 0, \"price\" : 0.6027456183070403, \"name\" : \"name\", \"description\" : \"description\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /api/products/{product-id} : Get Product Info by Product ID
     * Retrieve the information of the Product with the matching Product ID.
     *
     * @param productId Product ID (required)
     * @return Success (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Product Not Found (status code 404)
     *         or Method Not Allowed (status code 405)
     *         or Internal Server Error (status code 500)
     * @see ProductAPI#getProductById
     */
    default ResponseEntity<Optional<Product>> getProductById(Long productId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"quantityAvailable\" : 0, \"productId\" : 0, \"price\" : 0.6027456183070403, \"name\" : \"name\", \"description\" : \"description\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /api/products/{product-id}/applyModification : Apply Discount or deduct tax
     * Appy Discount or deduct tax
     *
     * @param productId Product ID (required)
     * @param postApiProductProductIdApplyModificationRequest  (optional)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Method Not Allowed (status code 405)
     *         or Internal Server Error (status code 500)
     * @see ProductAPI#postApiProductProductIdApplyModification
     */
    default ResponseEntity<ProductResponse> applyModification(Long productId,
    		ProductModificationRequest postApiProductProductIdApplyModificationRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"product\" : { \"quantityAvailable\" : 0, \"productId\" : 0, \"price\" : 0.6027456183070403, \"name\" : \"name\", \"description\" : \"description\" }, \"modifiedPrice\" : 0.8008281904610115 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    String exampleString = "<null> <Product> <productId>123456789</productId> <name>aeiou</name> <description>aeiou</description> <price>3.149</price> <quantityAvailable>123</quantityAvailable> </Product> <modifiedPrice>3.149</modifiedPrice> </null>";
                    ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /api/products/{product-id} : Update Product Information
     * Update the information of an existing product.
     *
     * @param productId Product ID (required)
     * @param product  (optional)
     * @return Product information Updated (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Method Not Allowed (status code 405)
     *         or Internal Server Error (status code 500)
     * @see ProductAPI#updateProduct
     */
    default ResponseEntity<Product> updateProduct(Long productId,
        Product product) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"quantityAvailable\" : 0, \"productId\" : 0, \"price\" : 0.6027456183070403, \"name\" : \"name\", \"description\" : \"description\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
