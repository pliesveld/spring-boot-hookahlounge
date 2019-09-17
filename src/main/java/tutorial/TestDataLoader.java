package tutorial;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import tutorial.domain.BasicInventory;
import tutorial.domain.Hooka;
import tutorial.domain.ShishaInventory;
import tutorial.repository.BasicInventoryRepository;
import tutorial.repository.HookaRepository;
import tutorial.repository.ShishaInventoryRepository;

@Component
//@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class TestDataLoader implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private HookaRepository hookaRepository;
	
	@Autowired
	private BasicInventoryRepository basicInventoryRepository;
	
	@Autowired
	private ShishaInventoryRepository shishaInventoryRepository;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		BasicInventory basicInventory = new BasicInventory();
		
		basicInventory.setId("tips");
		basicInventory.setCount(4);
		basicInventoryRepository.save(basicInventory);

		basicInventory = new BasicInventory();
		basicInventory.setId("hose");
		basicInventory.setCount(15);
		basicInventoryRepository.save(basicInventory);
		
		basicInventory = new BasicInventory();
		basicInventory.setId("coals");
		basicInventory.setCount(40);
		basicInventoryRepository.save(basicInventory);
		
		for(int i = 0;i < 10;i++) {
			Hooka hooka = new Hooka();
			hookaRepository.save(hooka);
		}
		
		for( String flavor : Arrays.asList("apple", "mint", "orange", "strawberry")) {
			ShishaInventory shishaInventory = new ShishaInventory();
			shishaInventory.setFlavor(flavor);
			shishaInventory.setQuantity(300);
			shishaInventoryRepository.save(shishaInventory);
		}
	}


}
