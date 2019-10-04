package tutorial.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tutorial.domain.BasicInventory;
import tutorial.domain.Hooka;
import tutorial.domain.OrderRequest;
import tutorial.domain.ShishaInventory;
import tutorial.domain.SmokeableHooka;
import tutorial.exceptions.ProductNotFound;
import tutorial.repository.BasicInventoryRepository;
import tutorial.repository.HookaRepository;
import tutorial.repository.ShishaInventoryRepository;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Transactional
public class HookaService {
	@Autowired
	private HookaRepository hookaRepository;
	
	@Autowired
	private BasicInventoryRepository basicInventoryRepository;
	
	@Autowired
	private ShishaInventoryRepository shishaInventoryRepository;
	
	
	public HookaService() {
	}
	
	public SmokeableHooka prepareHooka(@Valid OrderRequest orderRequest) {
		
		int req_hoses = orderRequest.getHoses();
		Predicate<Hooka> pred = (h -> h.getHoses() == req_hoses);
		Optional<Hooka> findFirst = hookaRepository.findAllByDirtyIsFalse().filter(pred).findFirst();
		
		Hooka hooka = findFirst.orElseThrow(ProductNotFound::noHooka);
		hooka.setDirty(true);
		
		String orderFlavor = orderRequest.getFlavor();
		ShishaInventory shishaInventory = shishaInventoryRepository.findByFlavor(orderFlavor);

		Objects.requireNonNull(shishaInventory,"no shisha found " + orderFlavor);
		
		shishaInventory.setQuantity(shishaInventory.getQuantity() - 10);
		
		BasicInventory coals = basicInventoryRepository.findById("coals").orElseThrow(ProductNotFound::noCoals);
		
		BasicInventory disposableHose = basicInventoryRepository.findById("hose").orElseThrow(ProductNotFound::noHoses);
		
		if(coals.getCount() < 6) {
			throw new RuntimeException("not enough coals");
		}
		
		coals.setCount(coals.getCount() - 6);
		
		if(disposableHose.getCount() < req_hoses) {
			throw new RuntimeException("not enough hoses" + req_hoses);
		}
		
		disposableHose.setCount(disposableHose.getCount() - req_hoses);
		
		SmokeableHooka smokeableHooka = new SmokeableHooka(hooka);

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			public void afterCommit() {	
				System.out.println("PUBLISH EVENT");
			};
		});
		
		return smokeableHooka;
	}

    public List<Hooka> fetchAllHookas() {
		Iterable<Hooka> iterable =  hookaRepository.findAll();
		List<Hooka> actualList = StreamSupport
			.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
		return actualList;
    }
}