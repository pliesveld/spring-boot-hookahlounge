package tutorial.test;

import tutorial.test.data.MockDataLoader;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hamcrest.core.IsEqual;
import org.hibernate.PersistentObjectException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.support.TransactionTemplate;

import tutorial.HookahloungeApplication;
import tutorial.domain.Hooka;
import tutorial.test.config.TestConfig;

@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration(classes = {HookahloungeApplication.class}),
//	@ContextConfiguration(classes = {TestConfig.class}),
	@ContextConfiguration(classes = {MockDataLoader.class, EntityTest.class}),
})
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
						listeners = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class} 
)
@DataJpaTest
public class EntityTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Test
	public void findFirst() {
		assertThat(entityManager.find(Hooka.class, 1L), is(not(nullValue())));
		assertThat(entityManager.find(Hooka.class, 1L).getHoses(), IsEqual.equalTo(2));
		assertThat(entityManager.find(Hooka.class, 1L).isDirty(), is(true));
	}
	
	@Test
	@Transactional
	public void entityManager_merge() {
		Hooka detached = new Hooka();
		detached.setId(1L);
		detached.setHoses(5);
		detached.setDirty(true);
		entityManager.merge(detached);
		
		entityManager.flush();
		entityManager.clear();
	}
	
	
	@Test
	@Transactional
	public void entityManager_updateByReference() {
		Hooka hibernateReference = entityManager.getReference(Hooka.class, 1L);
		hibernateReference.setHoses(20);
		entityManager.flush();
		entityManager.clear();
	}
	
	@Test
	public void transactionalTest() throws Exception {
		
		try {
			transactionTemplate.execute((s) -> {
				
				Hooka hibernateReference = entityManager.getReference(Hooka.class, 1L);
				hibernateReference.setHoses(50);
				s.setRollbackOnly();
				assertFalse(s.isNewTransaction());
				throw new RuntimeException("rollback expected");
			});
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
//		assertThat(entityManager.find(Hooka.class, 1L).getHoses(), not(IsEqual.equalTo(50)));
	}
	
	
	@Test(expected = PersistentObjectException.class)
	public void persistentObjectException() {
		Hooka detached = new Hooka();
		detached.setId(1L);
		detached.setHoses(5);
		detached.setDirty(true);
		entityManager.persist(detached);
	}
	
	
	
	
}
