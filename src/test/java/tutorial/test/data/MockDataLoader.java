package tutorial.test.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.ContextRefreshedEvent;

import tutorial.domain.Hooka;

@Configuration
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class MockDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private boolean isLoaded = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        assertThat("Entity Manager Factory is autowired on application context load", entityManagerFactory, is(not(nullValue())));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        assertThat("Entity Manager Factory is not null after creating manager", entityManager, is(not(nullValue())));

        if(isLoaded) {
            entityManager.getTransaction().begin();
           
            Hooka domainPo = new Hooka();
            domainPo.setHoses(2);
            domainPo.setId(1L);
            domainPo.setDirty(true);
            
            entityManager.merge(domainPo);
            entityManager.flush();

            entityManager.getTransaction().commit();
        }
        isLoaded = true;
    }
}