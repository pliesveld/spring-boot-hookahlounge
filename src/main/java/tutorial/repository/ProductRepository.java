package tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tutorial.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
