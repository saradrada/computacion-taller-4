package icesi.edu.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.dao.IBusDao2;
import icesi.edu.model.BusType;
import icesi.edu.model.Tmio1Bus;

@Service
public class BusService2 {
	
	@Autowired
	private IBusDao2 busDao;

	@Transactional
	public Tmio1Bus save(Tmio1Bus bus) {
		busDao.save(bus);
		return bus;
	}

	public Iterable<Tmio1Bus> findAll() {
		return busDao.findAll();
	}

	public BusType[] getTypes() {
		return BusType.values();
	}

	public Tmio1Bus findById(Integer id) {
		return busDao.findById(id);
	}

	@Transactional
	public void delete(Tmio1Bus bus) {
		busDao.delete(bus);
	}
}
