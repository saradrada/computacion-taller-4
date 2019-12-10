package icesi.edu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import icesi.edu.model.BusType;
import icesi.edu.model.Tmio1Bus;
import icesi.edu.services.BusService;

@RestController
public class BusRestController {

	@Autowired
	private BusService service;

	@GetMapping("/api/bus/{id}")
	public Tmio1Bus findById(@PathVariable("id") String id) {

		try {
			Integer busId = Integer.parseInt(id);
			return service.findById(busId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/api/bus")
	public Iterable<Tmio1Bus> findAll() {
		return service.findAll();
	}

	@RequestMapping("/api/bus/getTypes")
	public BusType[] getTypes() {
		return service.getTypes();
	}
	
	@PostMapping("/api/bus")
	public Tmio1Bus save(@RequestBody Tmio1Bus bus) {

		try {
			return service.save(bus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
