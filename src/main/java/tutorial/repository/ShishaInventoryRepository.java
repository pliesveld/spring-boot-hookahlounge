package tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tutorial.domain.ShishaInventory;

@Repository
public interface ShishaInventoryRepository extends JpaRepository<ShishaInventory, Long> {
	
	ShishaInventory findByFlavor(String flavor);
	
}
