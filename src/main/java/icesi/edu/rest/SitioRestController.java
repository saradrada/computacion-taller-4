package icesi.edu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import icesi.edu.model.Tmio1Sitio;
import icesi.edu.services.SitiosService;

@RestController
public class SitioRestController {

	@Autowired
	private SitiosService service;

	@GetMapping("/api/sitios/{id}")
	public Tmio1Sitio findById(@PathVariable("id") String id) {

		try {
			long busId = (long) Integer.parseInt(id);
			return service.findById(busId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/api/sitios")
	public Iterable<Tmio1Sitio> findAll() {
		return service.findAll();
	}

	@PostMapping("/api/sitios")
	public Tmio1Sitio save(@RequestBody Tmio1Sitio sitio) {
		try {
			return service.save(sitio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/api/sitios/{id}")
	public void delete(@PathVariable long id) {
		
		service.delete(id);
		
	}
	
	@PutMapping("/api/sitios")
	public Tmio1Sitio update(@RequestBody Tmio1Sitio sitio) {
		try {
			return service.update(sitio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
