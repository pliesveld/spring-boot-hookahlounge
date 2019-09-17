package tutorial.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tutorial.domain.Hooka;
import tutorial.repository.HookaRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = HookaRepository.class)
@EntityScan(basePackageClasses = Hooka.class)
public class HookaConfig {

}
