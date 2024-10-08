package com.cloudbees.service;

import static com.cloudbees.util.CommonFunctions.printAsJson;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloudbees.controller.ProductAPIDelegate;
import com.cloudbees.exception.BadRequestException;
import com.cloudbees.exception.InternalServerErrorException;
import com.cloudbees.exception.NotFoundException;
import com.cloudbees.exception.ProductDeletionException;
import com.cloudbees.exception.ProductSavingException;
import com.cloudbees.model.Product;
import com.cloudbees.model.ProductModificationRequest;
import com.cloudbees.model.ProductResponse;
import com.cloudbees.repository.ProductRepository;
import com.cloudbees.util.Constants;

import io.micrometer.common.util.StringUtils;

@Service
public class ProductService implements ProductAPIDelegate {

	ProductRepository productRepository;

	private final Logger log = LoggerFactory.getLogger(getClass());

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<Product> createProduct(Product product) {

		Product productDetails = null;
		try {

			validateProductRequest(product);
			productDetails = productRepository.save(product);
		} catch (BadRequestException e) {

			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {

			log.error("Exception Occured while adding new Product: {}", e.getLocalizedMessage());
			throw new ProductSavingException("Exception Occured while adding new Product: " + e.getLocalizedMessage());
		}

		log.info("Product added Successfully : {}", printAsJson(productDetails));
		return ResponseEntity.ok(productDetails);
	}

	@Override
	public ResponseEntity<Product> updateProduct(Long id, Product product) {

		Product productDetails = null;
		if (id == null) {

			throw new BadRequestException("ProductId cannot be null");
		}
		try {

			productDetails = productRepository.findById(id)
					.orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND + id));

			if (product.getName() != null) {
				productDetails.setName(product.getName());
			}
			if (product.getDescription() != null) {
				productDetails.setDescription(product.getDescription());
			}
			if (product.getPrice() != null) {
				productDetails.setPrice(product.getPrice());
			}
			if (product.getQuantityAvailable() != null) {
				productDetails.setQuantityAvailable(product.getQuantityAvailable());
			}
		}

		catch (BadRequestException e) {

			throw new BadRequestException(e.getMessage());
		} catch (NotFoundException e) {

			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {

			log.error("Exception Occured while updating Product: {}", e.getLocalizedMessage());
			throw new ProductSavingException("Exception Occured while updating Product: " + e.getLocalizedMessage());
		}

		log.info("Product updated Successfully : {}", printAsJson(productDetails));
		return ResponseEntity.ok(productRepository.save(productDetails));
	}

	@Override
	public ResponseEntity<Optional<Product>> getProductById(Long id) {
		try {

			if (id == null) {
				throw new BadRequestException("ProductID cannot be null");
			}
			Optional<Product> productDetails = productRepository.findById(id);
			if (productDetails.isPresent()) {

				log.info("ProductDetails retrived for Id {} : {}", id, printAsJson(productDetails));
				return ResponseEntity.ok(productDetails);
			} else {
				log.info(Constants.PRODUCT_NOT_EXIST, id);
				return ResponseEntity.notFound().build();
			}
		} catch (BadRequestException e) {

			throw new BadRequestException(e.getMessage());
		} catch (NotFoundException e) {

			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {

			log.error("Exception Occured while reading Product:: {}", e.getLocalizedMessage());
			throw new InternalServerErrorException("Exception Occured while reading Product: " + e.getMessage());
		}

	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> productList = null;
		try {

			productList = productRepository.findAll();
			log.info("Retrieved all Products : {}", printAsJson(productList));

		} catch (Exception e) {

			log.error("Exception Occured while fetching all Products: {}", e.getLocalizedMessage());
			throw new InternalServerErrorException("Exception Occured while fetching all Products: " + e.getMessage());
		}
		return ResponseEntity.ok(productList);
	}

	@Override
	public ResponseEntity<String> deleteProduct(Long id) {

		try {

			if (id == null) {
				throw new BadRequestException("ProductID cannot be null");
			}
			if (productRepository.existsById(id)) {

				productRepository.deleteById(id);
				log.info("Successfully Deleted Product with ID : {}", id);

			} else {

				log.info(Constants.PRODUCT_NOT_EXIST, id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.PRODUCT_NOT_FOUND + id);
			}
		}

		catch (BadRequestException e) {

			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {

			log.error("Exception Occured while deleting Product: {}", e.getLocalizedMessage());
			throw new ProductDeletionException("Exception Occured while deleting Product: " + e.getMessage());
		}
		return ResponseEntity.ok("Successfully Deleted Product with ID : " + id);
	}

	private void validateProductRequest(Product product) {

		if (null == product) {
			throw new BadRequestException("Request body cannot be null");
		}
		if (null == product.getQuantityAvailable() || product.getQuantityAvailable() < 0) {
			throw new BadRequestException("QuantityAvailable() cannot be null and the value should be minimum 0");
		}
		if (StringUtils.isBlank(product.getName())) {
			throw new BadRequestException("Product Name should not be null");
		}
		if (null == product.getPrice()) {
			throw new BadRequestException("Price value should not be null");
		}
	}

	@Override
	public ResponseEntity<ProductResponse> applyModification(Long id, ProductModificationRequest request) {
		ProductResponse response = new ProductResponse();
		try {
			if (StringUtils.isBlank(request.getModificationType()) || request.getModificationValue() == null) {
				throw new BadRequestException("Modification type and value cannot be null");
			}

			Optional<Product> productDetails = productRepository.findById(id);

			if (productDetails.isPresent()) {
				Product product = productDetails.get();
				response.setProduct(product);
				if (Constants.DISCOUNT.equalsIgnoreCase(request.getModificationType())) {

					response.setModifiedPrice(product.getPrice() * (1 - request.getModificationValue() / 100));
					log.info("Applied Discount successfully : {}", printAsJson(response));
				} else if (Constants.TAX.equalsIgnoreCase(request.getModificationType())) {

					response.setModifiedPrice(product.getPrice() * (1 + request.getModificationValue() / 100));
					log.info("Applied Tax successfully : {}", printAsJson(response));
				} else {
					throw new BadRequestException("Invalid modification type. Use 'discount' or 'tax'.");
				}

				productRepository.save(product);
				return ResponseEntity.ok(response);
			} else {
				log.info(Constants.PRODUCT_NOT_EXIST, id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			log.error("Exception Occurred while applying modification to Product: {}", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

}
