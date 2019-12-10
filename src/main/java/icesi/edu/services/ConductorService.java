package icesi.edu.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.dao.IConductorDao;
import icesi.edu.exceptions.ConductorNullException;
import icesi.edu.exceptions.FechaNullException;
import icesi.edu.exceptions.FechasNoConsistentesException;
import icesi.edu.exceptions.FormatoIncorrectoException;
import icesi.edu.model.Tmio1Conductore;

@Service
public class ConductorService {

//	@Autowired
//	private ConductoresRepository repository;
//	
	@Autowired
	private IConductorDao conductorDao;

	@Transactional
	public Tmio1Conductore save(Tmio1Conductore conductor) throws ConductorNullException, FechasNoConsistentesException,
			FechaNullException, FormatoIncorrectoException {

		if (conductor == null) {
			throw new ConductorNullException();
		} else if (conductor.getFechaNacimiento() == null || conductor.getFechaContratacion() == null) {
			throw new FechaNullException();
		} else if (conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == -1
				|| conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == 0) {
			throw new FechasNoConsistentesException();
		} else {
			conductorDao.save(conductor);
			return conductor;
//			return repository.save(conductor);
		}
	}

//	public void setRepository(ConductoresRepository repository) {
//		this.repository = repository;
//	}
//
	public Iterable<Tmio1Conductore> findAll() {
		return conductorDao.findAll();
//		return repository.findAll();
	}

	public Tmio1Conductore findById(String cedula) {

		return conductorDao.findByCedula(cedula);
//		return repository.findById(cedula);
	}

}
