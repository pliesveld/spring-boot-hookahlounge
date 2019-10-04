package tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tutorial.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
