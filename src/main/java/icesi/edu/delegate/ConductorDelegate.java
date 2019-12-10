package icesi.edu.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import icesi.edu.model.Tmio1Conductore;

@Component
public class ConductorDelegate {

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest = new RestTemplate();

	public Tmio1Conductore findById(String cedula) throws Exception {

		if(cedula == null) {
			throw new Exception("Cedula is null");
		}
		Tmio1Conductore conductor = rest.getForObject(URI + "api/conductor/" + cedula, Tmio1Conductore.class);
		return conductor;
	}

	public Iterable<Tmio1Conductore> findAll() {

		Tmio1Conductore[] conductores = rest.getForObject(URI + "api/conductor", Tmio1Conductore[].class);
		List<Tmio1Conductore> at;
		try {
			at = Arrays.asList(conductores);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save(Tmio1Conductore tmio1Conductore) throws Exception {

		Tmio1Conductore conductor = rest.postForEntity(URI + "api/conductor", tmio1Conductore, Tmio1Conductore.class).getBody();

		if (conductor == null ) {
			throw new Exception("Conductor is null");
		}

	}
}
