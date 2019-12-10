package icesi.edu.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import icesi.edu.model.BusType;
import icesi.edu.model.Tmio1Bus;

@Component
public class BusDelegate {

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest = new RestTemplate();

	public Tmio1Bus findById(Integer id) throws Exception {

		Tmio1Bus bus = rest.getForObject(URI + "api/bus/" + id, Tmio1Bus.class);
		if (bus == null) {
			throw new Exception("Bus is null");
		}
		return bus;

	}

	public Iterable<Tmio1Bus> findAll() {

		Tmio1Bus[] buses = rest.getForObject(URI + "api/bus", Tmio1Bus[].class);
		List<Tmio1Bus> at;
		try {
			at = Arrays.asList(buses);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BusType[] getTypes() throws Exception {

		BusType[] types = rest.getForObject(URI + "/api/bus/getTypes", BusType[].class);
		if (types == null) {
			throw new Exception("Bus types are null");
		}
		return types;

	}

	public void save(Tmio1Bus tmio1Bus) {

		Tmio1Bus bus = rest.postForEntity(URI + "api/bus", tmio1Bus, Tmio1Bus.class).getBody();
		if (bus == null) {
			throw new IllegalArgumentException("bus is null");
		}

	}
}
