package tutorial.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tutorial.domain.Order;
import tutorial.service.HookaService;

@RestController
public class HookaController {
	
	@Autowired
	private HookaService hookaService;

	@RequestMapping(path = "/all")
	public ResponseEntity<?> getAllHookas() {
		return ResponseEntity.ok(hookaService.fetchAllHookas());
	}

	@RequestMapping(path = "/hooka", method = RequestMethod.POST)
	public ResponseEntity<?> prepareHooka(@RequestBody Order order) {
		if(order.getHoses() == 0) {
			order.setHoses(2);
		}
		return ResponseEntity.ok(hookaService.prepareHooka(order));
	}
	
	
	
}


