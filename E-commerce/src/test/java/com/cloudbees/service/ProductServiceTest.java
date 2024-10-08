package com.cloudbees.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cloudbees.exception.BadRequestException;
import com.cloudbees.exception.NotFoundException;
import com.cloudbees.exception.ProductSavingException;
import com.cloudbees.model.Product;
import com.cloudbees.model.ProductModificationRequest;
import com.cloudbees.model.ProductResponse;
import com.cloudbees.repository.ProductRepository;
import com.cloudbees.util.Constants;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	ProductService productService;

	@Mock
	ProductRepository productRepository;

	@Test
	void testCreateProduct() {

		productService = new ProductService(productRepository);
		Product product = new Product();
		product.setProductId(12L);
		product.setName("The Adventure");
		product.setDescription("Fiction");
		product.setPrice(125.0);
		product.setQuantityAvailable(50);

		when(productRepository.save(any())).thenReturn(product);
		ResponseEntity<Product> savedProduct = productService.createProduct(product);

		assertNotNull(savedProduct);
		assertEquals("The Adventure", savedProduct.getBody().getName());
		assertEquals("Fiction", savedProduct.getBody().getDescription());
		assertEquals(50, savedProduct.getBody().getQuantityAvailable());

		product.setName("Discovery Of India");
		product.setDescription("Non-Fiction");
		product.setPrice(125.0);
		product.setQuantityAvailable(-5);
		assertThrows(BadRequestException.class, () -> productService.createProduct(product));

		product.setQuantityAvailable(null);
		assertThrows(BadRequestException.class, () -> productService.createProduct(product));

		product.setName("");
		product.setQuantityAvailable(50);

		assertThrows(BadRequestException.class, () -> productService.createProduct(product));

		product.setName("Discovery Of India");
		product.setPrice(null);
		assertThrows(BadRequestException.class, () -> productService.createProduct(product));

		assertThrows(BadRequestException.class, () -> productService.createProduct(null));

	}

	@Test
	void testUpdateProduct() {

		productService = new ProductService(productRepository);
		Product product = new Product();
		product.setProductId(12L);
		product.setName("Wings of Fire");
		product.setDescription("Non-Fiction");
		product.setPrice(170.0);
		product.setQuantityAvailable(40);

		Product updatedProduct = new Product();
		updatedProduct.setQuantityAvailable(80);
		when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
		when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
		ResponseEntity<Product> responseEntity = productService.updateProduct(product.getProductId(), updatedProduct);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(80, responseEntity.getBody().getQuantityAvailable());

		Product updatedProductName = new Product();
		updatedProductName.setName("My Journey");
		when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
		when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
		responseEntity = productService.updateProduct(product.getProductId(), updatedProductName);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals("My Journey", responseEntity.getBody().getName());

		Product updatedProductDesc = new Product();
		updatedProductDesc.setDescription("Fiction");
		when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
		when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
		responseEntity = productService.updateProduct(product.getProductId(), updatedProductDesc);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals("Fiction", responseEntity.getBody().getDescription());

		Product updatedProductPrice = new Product();
		updatedProductPrice.setPrice(95.0);
		when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
		when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
		responseEntity = productService.updateProduct(product.getProductId(), updatedProductPrice);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(95.0, responseEntity.getBody().getPrice());

		when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class,
				() -> productService.updateProduct(product.getProductId(), updatedProduct));

		assertThrows(BadRequestException.class, () -> productService.updateProduct(null, updatedProduct));

	}

	@Test
	void testGetAllProducts() {

		productService = new ProductService(productRepository);
		Product product = new Product();
		product.setProductId(20L);
		product.setName("The Malgudi Days");
		product.setDescription("Fiction");
		product.setPrice(110.0);
		product.setQuantityAvailable(50);

		Product product2 = new Product();
		product2.setProductId(21L);
		product2.setName("You Can Win");
		product2.setDescription("Non-Fiction");
		product2.setPrice(100.0);
		product2.setQuantityAvailable(35);

		List<Product> productList = Arrays.asList(product, product2);

		when(productRepository.findAll()).thenReturn(productList);

		ResponseEntity<List<Product>> responseEntity = productService.getAllProducts();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(2, responseEntity.getBody().size());

	}

	@Test
	void testGetProductById() {
		productService = new ProductService(productRepository);
		Product product = new Product();
		product.setProductId(20L);
		product.setName("The Malgudi Days");
		product.setDescription("Fiction");
		product.setPrice(120.0);
		product.setQuantityAvailable(40);

		when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
		ResponseEntity<Optional<Product>> responseEntity = productService.getProductById(product.getProductId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
		responseEntity = productService.getProductById(product.getProductId());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		assertThrows(BadRequestException.class, () -> productService.getProductById(null));
	}

	@Test
	void testDeleteProduct() {
		productService = new ProductService(productRepository);
		Product product = new Product();
		product.setProductId(10L);
		product.setName("Wings of Fire");
		product.setDescription("Non-Fiction");
		product.setPrice(170.0);
		product.setQuantityAvailable(40);

		when(productRepository.existsById(product.getProductId())).thenReturn(true);
		ResponseEntity<String> responseEntity = productService.deleteProduct(product.getProductId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Successfully Deleted Product with ID : " + product.getProductId(), responseEntity.getBody());

		Long productId = 1L;

		when(productRepository.existsById(productId)).thenReturn(false);

		responseEntity = productService.deleteProduct(productId);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		assertThrows(BadRequestException.class, () -> productService.deleteProduct(null));
	}

	@Test
	void testapplyModification() {
		productService = new ProductService(productRepository);
		Product product = new Product();
		product.setProductId(12L);
		product.setName("The Adventure");
		product.setDescription("Fiction");
		product.setPrice(100.0);
		product.setQuantityAvailable(50);

		Long productId = 1L;
		ProductModificationRequest request = new ProductModificationRequest();
		request.setModificationType(Constants.DISCOUNT);
		request.setModificationValue(10.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		ResponseEntity<ProductResponse> responseEntity = productService.applyModification(productId, request);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(90.0, responseEntity.getBody().getModifiedPrice());

		request.setModificationType(Constants.TAX);
		request.setModificationValue(20.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		responseEntity = productService.applyModification(productId, request);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(120.0, responseEntity.getBody().getModifiedPrice());
		
		request.setModificationType("account");
		request.setModificationValue(20.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		assertThrows(BadRequestException.class, () -> productService.applyModification(productId, request));
		
		when(productRepository.findById(productId)).thenReturn(Optional.empty());
		responseEntity = productService.applyModification(productId, request);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		
		request.setModificationType(null);
		request.setModificationValue(12.0);
		assertThrows(BadRequestException.class, () -> productService.applyModification(productId, request));
		
		request.setModificationType("discount");
		request.setModificationValue(null);
		assertThrows(BadRequestException.class, () -> productService.applyModification(productId, request));


	}

}
