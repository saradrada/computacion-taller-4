package icesi.edu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import icesi.edu.model.Tmio1Ruta;
import icesi.edu.services.RutaService;

@RestController
public class RutasRestController {

	@Autowired
	private RutaService service;
	
	@RequestMapping("/api/ruta/{id}")
	public Tmio1Ruta findById(@PathVariable("id") String id) {

		try {
			Integer rutaId = Integer.parseInt(id);
			return service.findById(rutaId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping("/api/ruta")
	public Iterable<Tmio1Ruta> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(value = "/api/ruta", method = RequestMethod.POST)
	public Tmio1Ruta save(@RequestBody Tmio1Ruta tmio1Ruta) {

		try {
			return service.save(tmio1Ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
