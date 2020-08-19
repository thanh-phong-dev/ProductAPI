/**
* System name : Product API
*
* Author  Phong
* Version 1.0
* Created date Aug 19, 2020
*/
package product.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.api.model.Product;
import product.api.repository.ProductRepository;

/**
 * The Class ProductService.
 */
@Service
@Transactional
public class ProductService {

	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Save.
	 *
	 * @param product the product
	 */
	public void save(Product product) {
		productRepository.save(product);
	}

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Product> listAllProduct() {
		return productRepository.findAll();
	}

	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the product
	 */
	public Product getProductById(Integer id) {
		return productRepository.findById(id).get();
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	public List<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

}
