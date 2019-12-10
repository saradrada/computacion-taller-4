package icesi.edu.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import icesi.edu.model.Tmio1Servicio;
import icesi.edu.model.Tmio1ServicioPK;
import icesi.edu.services.ServicioService;

@RestController
public class ServicioRestController {

	@Autowired
	private ServicioService service;

	@RequestMapping("/api/servicio")
	public Iterable<Tmio1Servicio> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/api/servicio", method = RequestMethod.POST)
	public Tmio1Servicio save(@RequestBody Tmio1Servicio servicio) {

		try {
			return service.save(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@DeleteMapping("/api/servicio/{id_hash}")
	public void delete(@PathVariable( "id_hash") Integer id_hash) {
		

		Iterable<Tmio1Servicio> servicios = findAll();
		Tmio1Servicio servicio = null;

		for (Tmio1Servicio ser : servicios) {
			if (ser.getId_hash().compareTo(id_hash) == 0) {
				servicio = ser;
				break;
			}
		}
		service.delete(servicio);
		
	}
	
	@RequestMapping("/api/servicio/{fechaInicio}/{fechaFin}")
	public Iterable<Tmio1Servicio> filter(@PathVariable("fechaInicio") Date fechaInicio, @PathVariable("fechaFin") Date fechaFin) {
		return service.filter(fechaInicio, fechaFin);
	}
	

}
