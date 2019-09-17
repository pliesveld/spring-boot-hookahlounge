package tutorial.repository;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tutorial.domain.Hooka;

@Repository
public interface HookaRepository extends CrudRepository<Hooka, Long> {

	Stream<Hooka> findAllByDirtyIsFalse();
}
