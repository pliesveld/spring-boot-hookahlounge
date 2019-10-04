package tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tutorial.domain.OrderRequest;
import tutorial.service.HookaService;

@RestController
@RequestMapping(path = "/hooka")
public class HookaController {
	
	@Autowired
	private HookaService hookaService;

	@RequestMapping
	public ResponseEntity<?> getAllHookas() {
		return ResponseEntity.ok(hookaService.fetchAllHookas());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> prepareHooka(@RequestBody OrderRequest orderRequest) {
		if(orderRequest.getHoses() == 0) {
			orderRequest.setHoses(2);
		}
		return ResponseEntity.ok(hookaService.prepareHooka(orderRequest));
	}
	
	
	
}


