package icesi.edu.delegate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import icesi.edu.model.Tmio1Servicio;
import icesi.edu.model.Tmio1Sitio;
import icesi.edu.rest.TransactionBody;

@Component
public class ServiciosDelegate {

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest = new RestTemplate();

	public Iterable<Tmio1Servicio> findAll() {

		Tmio1Servicio[] servicios = rest.getForObject(URI + "api/servicio", Tmio1Servicio[].class);
		List<Tmio1Servicio> at;
		try {
			at = Arrays.asList(servicios);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save(Tmio1Servicio tmio1Servicio) throws Exception {

		if (tmio1Servicio == null) {
			throw new Exception("Servicio is null");
		}
		Tmio1Servicio servicio = rest.postForEntity(URI + "api/servicio", tmio1Servicio, Tmio1Servicio.class)
				.getBody();
		if (servicio == null) {
			throw new Exception("Servicio nuevo is null");
		}
	}

	public void update(Tmio1Sitio tmio1Sitio) throws Exception {
		rest.put(URI + "api/servicio", tmio1Sitio);	
	}
	
	public void delete(Integer id) {
		rest.delete(URI + "api/servicio/"+ id );
		}

	public Iterable<Tmio1Servicio> filter(Date fechaInicio, Date fechaFin) {
		Tmio1Servicio[] servicios = rest.getForObject(URI + "api/servicio/" + fechaInicio + "/" + fechaFin,
				Tmio1Servicio[].class);
		List<Tmio1Servicio> at;
		try {
			at = Arrays.asList(servicios);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
