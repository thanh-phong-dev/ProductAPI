/**
* System name : Product API
*
* Author  Phong
* Version 1.0
* Created date Aug 19, 2020
*/
package product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import product.api.model.Product;

/**
 * The Interface ProductRepository.
 *
 * @author Phong
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	/**
	 * List all.
	 *
	 * @return the list
	 */
	@Query("SELECT p FROM Product p WHERE p.name = :name")
	List<Product> findByName(@Param("name") String name);

}
