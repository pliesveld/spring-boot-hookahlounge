package tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tutorial.domain.BasicInventory;

@Repository
public interface BasicInventoryRepository extends CrudRepository<BasicInventory, String> {

}
