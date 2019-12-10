package icesi.edu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import icesi.edu.model.Tmio1Sitio;
import icesi.edu.model.Tmio1SitiosRuta;
import icesi.edu.model.Tmio1SitiosRutaPK;
import icesi.edu.services.SitiosRutaService;

@RestController
public class SitiosRutaRestController {

	@Autowired
	private SitiosRutaService service;

	@GetMapping("/api/sitiosruta/{id}")
	public Tmio1SitiosRuta findById(@PathVariable("id") Tmio1SitiosRutaPK id) {

		try {
			return service.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/api/sitiosruta")
	public Iterable<Tmio1SitiosRuta> findAll() {
		return service.findAll();
	}

	@PostMapping("/api/sitiosruta")
	public Tmio1SitiosRuta save(@RequestBody Tmio1SitiosRuta sitio) {
		try {
			return service.save(sitio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/api/sitiosruta/{ruta}/{sitio}")
	public Tmio1SitiosRuta delete(@PathVariable( "ruta") Integer ruta, @PathVariable( "sitio") Integer sitio){
		
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta);
		id.setIdSitio(sitio);
		
		Tmio1SitiosRuta sitio2 = service.findById(id);
		service.delete(sitio2);
		return sitio2;
	}
}
