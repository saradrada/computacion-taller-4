package icesi.edu.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.dao.IBusDao;
import icesi.edu.exceptions.BusNullException;
import icesi.edu.exceptions.BusTipoException;
import icesi.edu.exceptions.CapacidadNullException;
import icesi.edu.exceptions.GuardarBusException;
import icesi.edu.exceptions.TipoNullException;
import icesi.edu.model.BusType;
import icesi.edu.model.Tmio1Bus;

@Service
public class BusService {

//	@Autowired
//	private BusesRepository repository;
	
	@Autowired
	private IBusDao busDao;

	@Transactional
	public Tmio1Bus save(Tmio1Bus bus)
			throws GuardarBusException, BusNullException, BusTipoException, TipoNullException, CapacidadNullException {

		if (bus == null) {
			throw new BusNullException();
		} else if (bus.getTipo() == null || bus.getTipo().equals("")) {
			throw new TipoNullException();
		} else if (bus.getCapacidad() == null) {
			throw new CapacidadNullException();
		} else if ((bus.getCapacidad().compareTo(new BigDecimal(0)) <= 0)) {
			throw new GuardarBusException();
		} else if (!(bus.getTipo().toString().equals("T") || bus.getTipo().toString().equals("P") || bus.getTipo().toString().equals("A"))) {
			throw new BusTipoException();
		} else {
			busDao.save(bus);
//			repository.save(bus);
			return bus;
		}
	}

//	public void setRepository(BusesRepository repository) {
//		this.repository = repository;
//	}
	
	public Iterable<Tmio1Bus> findAll(){

		return busDao.findAll();
//		return repository.findAll();
	}

	public BusType[] getTypes() {
		return BusType.values();
	}
	
	public Tmio1Bus findById(Integer id) {

		
		return busDao.findById(id);
//		return repository.findById(id);
	}

}
