package tutorial.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tutorial.domain.Head;

@Repository
public interface HeadRepository extends CrudRepository<Head, Long> {

}
