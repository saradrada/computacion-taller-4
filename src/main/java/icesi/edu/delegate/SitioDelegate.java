package icesi.edu.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import icesi.edu.model.Tmio1Sitio;

@Component
public class SitioDelegate {

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest = new RestTemplate();

	
	public Tmio1Sitio findById(long id) {
		Tmio1Sitio sitio = rest.getForObject(URI + "api/sitios/" + id, Tmio1Sitio.class);
		return sitio;
	}
	
	public Iterable<Tmio1Sitio> findAll() {
		Tmio1Sitio[] sitios = rest.getForObject(URI + "api/sitios", Tmio1Sitio[].class);
		List<Tmio1Sitio> at;
		try {
			at = Arrays.asList(sitios);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(Tmio1Sitio sitio) {
		rest.delete(URI + "api/sitios/" + sitio.getId());
		System.out.println("");
	}
	
	public void save(Tmio1Sitio tmio1Sitio) throws Exception {
		Tmio1Sitio sitio = rest.postForEntity(URI + "api/sitios", tmio1Sitio, Tmio1Sitio.class).getBody();
		if (sitio == null) {
			throw new Exception();
		}
	}
	
	public void update(Tmio1Sitio tmio1Sitio) throws Exception {
		rest.put(URI + "api/sitios", tmio1Sitio);
		
	}
}
