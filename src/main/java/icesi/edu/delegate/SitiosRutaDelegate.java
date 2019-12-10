package icesi.edu.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import icesi.edu.model.Tmio1SitiosRuta;
import icesi.edu.model.Tmio1SitiosRutaPK;

@Component
public class SitiosRutaDelegate {

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest = new RestTemplate();
	
	
	
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id) {
		Tmio1SitiosRuta sitio = rest.getForObject(URI + "api/sitios/" + id, Tmio1SitiosRuta.class);
		return sitio;
	}
	
	
	public Iterable<Tmio1SitiosRuta> findAll() {
		Tmio1SitiosRuta[] sitios = rest.getForObject(URI + "api/sitiosruta", Tmio1SitiosRuta[].class);
		List<Tmio1SitiosRuta> at;
		try {
			at = Arrays.asList(sitios);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void save(Tmio1SitiosRuta tmio1SitiosRuta) throws Exception {
		Tmio1SitiosRuta sitio = rest.postForEntity(URI + "api/sitiosruta", tmio1SitiosRuta, Tmio1SitiosRuta.class).getBody();
		if (sitio == null) {
			throw new Exception();
		}
	}
	
	public void delete(Tmio1SitiosRuta sitio) {
		rest.delete(URI + "api/sitiosruta/" + sitio.getTmio1Ruta1().getId()+ "/" + sitio.getTmio1Sitio1().getId());
	}


	public Tmio1SitiosRuta findById(Integer id_hash) {
		
		return null;
	}
	
	
}
