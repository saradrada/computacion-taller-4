package icesi.edu.services;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.dao.IRutaDao;
import icesi.edu.exceptions.FechaInvalidaException;
import icesi.edu.exceptions.FechaNullException;
import icesi.edu.exceptions.FechasNoConsistentesException;
import icesi.edu.exceptions.HoraInvalidaException;
import icesi.edu.exceptions.HoraNullException;
import icesi.edu.exceptions.HorasNoConsistentesException;
import icesi.edu.exceptions.RutaNullException;
import icesi.edu.model.Tmio1Bus;
import icesi.edu.model.Tmio1Conductore;
import icesi.edu.model.Tmio1Ruta;
import icesi.edu.repositories.RutasRepository;


@Service
public class RutaService {

//	@Autowired
//	private RutasRepository repository;

	@Autowired
	private IRutaDao rutaDao;
	
	@Transactional
	public Tmio1Ruta save(Tmio1Ruta ruta)
			throws RutaNullException, FechasNoConsistentesException, HorasNoConsistentesException, FechaNullException,
			HoraNullException, FechaInvalidaException, HoraInvalidaException {

		if (ruta == null) {
			throw new RutaNullException();
		} else if (ruta.getDiaInicio() == null || ruta.getDiaFin() == null) {
			throw new FechaNullException();
		} else if (ruta.getHoraInicio() == null || ruta.getHoraFin() == null) {
			throw new HoraNullException();
		} else if (ruta.getDiaInicio().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaInicio().compareTo(new BigDecimal(7)) == 1
				|| ruta.getDiaFin().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaFin().compareTo(new BigDecimal(7)) == 1) {
			throw new FechaInvalidaException();
		} else if (ruta.getHoraInicio().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraInicio().compareTo(new BigDecimal(86399)) == 1
				|| ruta.getHoraFin().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraFin().compareTo(new BigDecimal(86399)) == 1) {
			throw new HoraInvalidaException();
		} else if (ruta.getDiaFin().compareTo(ruta.getDiaInicio()) == -1) {
			throw new FechasNoConsistentesException();
		} else if (ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == -1
				|| ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == 0) {
			throw new HorasNoConsistentesException();
		} else {
			
			rutaDao.save(ruta);
			return ruta;
//			return repository.save(ruta);
		}
	}

//	public void setRepository(RutasRepository repository) {
//		this.repository = repository;
//	}
	
	public Iterable<Tmio1Ruta> findAll(){
		return rutaDao.findAll();
//		return repository.findAll();
	}
	
	public Tmio1Ruta findById(Integer id) {
		return rutaDao.findById(id);
//		return repository.findById(id);
	}
	

}
