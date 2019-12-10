package icesi.edu.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import icesi.edu.model.Tmio1Ruta;

@Component
public class RutasDelegate {

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest = new RestTemplate();

	public Tmio1Ruta findById(Integer id) throws Exception {

		if (id == null) {
			throw new Exception("Id is null");
		}
		Tmio1Ruta ruta = rest.getForObject(URI + "api/ruta/" + id, Tmio1Ruta.class);
		return ruta;
	}

	public Iterable<Tmio1Ruta> findAll() {

		Tmio1Ruta[] buses = rest.getForObject(URI + "api/ruta", Tmio1Ruta[].class);
		List<Tmio1Ruta> at;
		try {
			at = Arrays.asList(buses);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void save(Tmio1Ruta tmio1Ruta) throws Exception {

		if (tmio1Ruta == null) {
			throw new Exception("Ruta is null");
		}
		Tmio1Ruta ruta = rest.postForEntity(URI + "/api/ruta", tmio1Ruta, Tmio1Ruta.class).getBody();

		if (ruta == null) {
			throw new Exception("Ruta nueva is null");
		}
	}
}
