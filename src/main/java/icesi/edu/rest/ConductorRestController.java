package icesi.edu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import icesi.edu.model.Tmio1Bus;
import icesi.edu.model.Tmio1Conductore;
import icesi.edu.services.ConductorService;

@RestController
public class ConductorRestController {

	@Autowired
	private ConductorService service;
	
	@RequestMapping("/api/conductor/{cedula}")
	public Tmio1Conductore findById(@PathVariable("cedula") String cedula) {
		return service.findById(cedula);
	}

	@RequestMapping("/api/conductor")
	public Iterable<Tmio1Conductore> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(value = "/api/conductor", method = RequestMethod.POST)
	public Tmio1Conductore save(@RequestBody Tmio1Conductore tmio1Conductore) {

		try {
			return service.save(tmio1Conductore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
