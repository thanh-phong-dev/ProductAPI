package product.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import product.api.model.Product;
import product.api.service.ProductService;

/**
 * The Class ProductController.
 */
@RestController
@RequestMapping("/api")
public class ProductController {

	/** The product service. */
	@Autowired
	private ProductService productService;

	/**
	 * List product.
	 *
	 * @return the response entity
	 */
	@GetMapping("/product")
	public ResponseEntity<List<Product>> listProduct() {
		List<Product> products = productService.listAllProduct();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	/**
	 * Gets the product by id.
	 *
	 * @param id the id
	 * @return the product by id
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		try {
			Product product = productService.getProductById(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds the product.
	 *
	 * @param product the product
	 */
	@PostMapping("/save/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		productService.save(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	/**
	 * Update product.
	 *
	 * @param product the product
	 * @param id
	 * @return the response entity
	 */
	@SuppressWarnings("unused")
	@PutMapping("/update/product/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		try {
			Product existProduct = productService.getProductById(id);
			productService.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok("Product is not found");
		}
	}

	/**
	 * Delete product.
	 *
	 * @param id the id
	 */
	@SuppressWarnings("unused")
	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		try {
			Product existProduct = productService.getProductById(id);
			productService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Search name.
	 *
	 * @param name the name
	 * @return the list
	 */
	@GetMapping("search/product")
	public ResponseEntity<List<Product>> searchName(@RequestParam String name) {
		List<Product> products = productService.findByName(name);
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
