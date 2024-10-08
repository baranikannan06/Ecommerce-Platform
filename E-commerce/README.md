ECommerce Platform API

A comprehensive Springboot CRUD API for managing products in an eCommerce platform.



## Documentation

### OpenAPI - Stoplight

The POJO (Plain Old Java Object) and Controller classes for the API have been automatically generated based on the OpenAPI specification using Stoplight. 

The OpenAPI specification file, which serves as the source for the code generation, is available at https://github.com/Sindhap/ecommerce-platform/blob/feature_ecommerce/src/main/resources/productAPI.yaml

## Prerequisites

Ensure you have the following prerequisites before getting started:

- Java 8 or higher
- Maven
- Eclipse IDE 


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

- **`DB_USERNAME`**: The username for connecting to the H2 database.
- **`DB_PASSWORD`**: The password for connecting to the H2 database.

## Run Locally

To get started with the project, follow these steps:

1. Clone the repository: `git clone https://github.com/Sindhap/ecommerce-platform.git`

2. Navigate to the project directory: `cd <project_directory>`

3. Build the project: `mvn clean install`

4. To run the application, use the following Maven command:
 `mvn spring-boot:run`
 
## Running Tests

To run tests, run the following command

mvn test

Coverage Report is available at https://github.com/Sindhap/ecommerce-platform/tree/feature_ecommerce/src/main/resources/coverage-report

## Code Quality and Scanning

### Code Scanning Status

The codebase has been scanned using CodeQL, and no code scanning alerts were found.

Also, Used [SonarLint](https://www.sonarlint.org/) in Eclipse IDE for code smell detection.

## API Reference

#### Get all products

Retrieve a list of all products.

```http
  GET /api/products
```

#### Get Product by ProductID

Retrieve the information of the product with the matching product ID.

```http
  GET /api/products/{product-id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `product-id`      | `Long` | **Required**. Id of product to fetch |

#### Add a New Product

Create a new product.

```http
  POST /api/product
```

| Fields | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | Product name |
| `description`      | `string` | Description of the product |
| `price`      | `Double` | Product price |
| `quantityAvailable`      | `Integer` | Available quantity of the product |


#### Update Product by ProductID

Update the information of an existing product.

```http
  PATCH /api/products/{product-id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `product-id`      | `Long` | **Required**. Id of product to fetch |

#### Delete Product

Delete an existing product.

```http
  DELETE /api/products/{product-id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `product-id`      | `Long` | **Required**. Id of product to delete |

#### Apply Discount or tax

Apply discount to a product to promote sales or tax for regulatory compliance

```http
  POST /api/products/{product-id}/applyModification
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `product-id`      | `Long` | **Required**. Id of product to update |
| `modificationType`      | `string` |  Either Discount or tax |
| `modificationValue`      | `Double` |  value |


