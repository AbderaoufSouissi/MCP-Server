package com.ars.mcp_server.service;

import com.ars.mcp_server.entity.Product;
import com.ars.mcp_server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;



    @Tool(description = "Retrieve all products from the database and format their details into a readable string.")
    public String getAllProducts() {
        List<Product> products = productRepository.findAll();
        StringBuilder result = new StringBuilder();
        result.append(String.format("Found %d products:\n\n", products.size()));
        for (Product product : products) {
            result.append(String.format("%s (ID: %d)\n", product.getName(), product.getId()));
            result.append(String.format(" Category: %s\n", product.getCategory()));
            result.append(String.format(" Price: $%.2f\n", product.getPrice()));
            result.append(String.format(" Stock: %d units\n\n", product.getStock()));
        }
        return result.toString();
    }


    @Tool(description = "Searches for products by category name" +
            "Returns all products that match the specified category (case-sensitive). "+
            "Common categories include: Electronics, Books, Clothing, Appliances.")
    public String searchByCategory (String category) {
        log.info("MCP Tool called: searchByCategory with category={}", category);
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            return String.format("No products found in category '%s'.", category);
        }
        StringBuilder result = new StringBuilder();
        result.append(String.format("Found %d products in category '%s':\n\n", products.size(), category));
        for (Product product : products) {
            result.append(String.format("%s (ID: %d) $%.2f -Stock: %d\n",
                    product.getName(), product.getId(), product.getPrice(), product.getStock()));
        }
        return result.toString();
    }

    @Tool(description = "Finds products with price strictly less than the given value and returns a formatted summary.")
    public String findProductsUnderPrice(Double price) {
        log.info("MCP Tool called: findProductsUnderPrice with price={}", price);
        if (price == null) {
            return "Please provide a price to search for products under.";
        }
        if (price <= 0) {
            return String.format("No products found under $%.2f (price must be > 0).", price);
        }
        List<Product> products = productRepository.findByPriceLessThan(price);
        if (products.isEmpty()) {
            return String.format("No products found with price under $%.2f.", price);
        }
        StringBuilder result = new StringBuilder();
        result.append(String.format("Found %d products with price under $%.2f:\n\n", products.size(), price));
        for (Product product : products) {
            result.append(String.format("%s (ID: %d)\n", product.getName(), product.getId()));
            result.append(String.format(" Category: %s\n", product.getCategory()));
            result.append(String.format(" Price: $%.2f\n", product.getPrice()));
            result.append(String.format(" Stock: %d units\n\n", product.getStock()));
        }
        return result.toString();
    }

    @Tool(description = "Adds a new product to the database. " +
            "Requires: name (product name), category (e.g., Electronics, Books, Clothing, Appliances), " +
            "price (must be positive), and stock (number of units available, must be non-negative).")
    public String addProduct(String name, String category, Double price, Integer stock) {
        log.info("MCP Tool called: addProduct with name={}, category={}, price={}, stock={}",
                name, category, price, stock);

        // Validation
        if (name == null || name.trim().isEmpty()) {
            return "Error: Product name is required.";
        }
        if (category == null || category.trim().isEmpty()) {
            return "Error: Product category is required.";
        }
        if (price == null || price <= 0) {
            return "Error: Product price must be a positive number.";
        }
        if (stock == null || stock < 0) {
            return "Error: Product stock must be a non-negative number.";
        }

        try {
            Product product = Product.builder()
                    .name(name.trim())
                    .category(category.trim())
                    .price(price)
                    .stock(stock)
                    .build();

            Product savedProduct = productRepository.save(product);

            return String.format("Product successfully added!\n\n" +
                    "%s (ID: %d)\n" +
                    " Category: %s\n" +
                    " Price: $%.2f\n" +
                    " Stock: %d units",
                    savedProduct.getName(),
                    savedProduct.getId(),
                    savedProduct.getCategory(),
                    savedProduct.getPrice(),
                    savedProduct.getStock());
        } catch (Exception e) {
            log.error("Error adding product", e);
            return String.format("Error adding product: %s", e.getMessage());
        }
    }

    @Tool(description = "Updates an existing product in the database. " +
            "Requires: id (product ID to update), name (new product name), category (new category), " +
            "price (new price, must be positive), and stock (new stock quantity, must be non-negative). " +
            "All fields are required. Returns an error if the product is not found.")
    public String updateProduct(Long id, String name, String category, Double price, Integer stock) {
        log.info("MCP Tool called: updateProduct with id={}, name={}, category={}, price={}, stock={}",
                id, name, category, price, stock);

        // Validation
        if (id == null || id <= 0) {
            return "Error: Product ID is required and must be positive.";
        }
        if (name == null || name.trim().isEmpty()) {
            return "Error: Product name is required.";
        }
        if (category == null || category.trim().isEmpty()) {
            return "Error: Product category is required.";
        }
        if (price == null || price <= 0) {
            return "Error: Product price must be a positive number.";
        }
        if (stock == null || stock < 0) {
            return "Error: Product stock must be a non-negative number.";
        }

        try {
            // Check if product exists
            var existingProduct = productRepository.findById(id);
            if (existingProduct.isEmpty()) {
                return String.format("Error: Product with ID %d not found.", id);
            }

            // Update the product
            Product product = existingProduct.get();
            product.setName(name.trim());
            product.setCategory(category.trim());
            product.setPrice(price);
            product.setStock(stock);

            Product updatedProduct = productRepository.save(product);

            return String.format("Product successfully updated!\n\n" +
                    "%s (ID: %d)\n" +
                    " Category: %s\n" +
                    " Price: $%.2f\n" +
                    " Stock: %d units",
                    updatedProduct.getName(),
                    updatedProduct.getId(),
                    updatedProduct.getCategory(),
                    updatedProduct.getPrice(),
                    updatedProduct.getStock());
        } catch (Exception e) {
            log.error("Error updating product", e);
            return String.format("Error updating product: %s", e.getMessage());
        }
    }

    @Tool(description = "Deletes a product from the database by its ID. " +
            "Requires: id (product ID to delete). " +
            "Returns an error if the product is not found.")
    public String deleteProduct(Long id) {
        log.info("MCP Tool called: deleteProduct with id={}", id);

        // Validation
        if (id == null || id <= 0) {
            return "Error: Product ID is required and must be positive.";
        }

        try {
            // Check if product exists
            var existingProduct = productRepository.findById(id);
            if (existingProduct.isEmpty()) {
                return String.format("Error: Product with ID %d not found.", id);
            }

            Product product = existingProduct.get();
            String productDetails = String.format("%s (ID: %d) - Category: %s, Price: $%.2f, Stock: %d units",
                    product.getName(),
                    product.getId(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getStock());

            // Delete the product
            productRepository.deleteById(id);

            return String.format("Product successfully deleted!\n\n%s", productDetails);
        } catch (Exception e) {
            log.error("Error deleting product", e);
            return String.format("Error deleting product: %s", e.getMessage());
        }
    }
}
