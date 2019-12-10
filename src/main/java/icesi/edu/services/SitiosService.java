package icesi.edu.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.dao.ISitioDao;
import icesi.edu.model.Tmio1Sitio;

@Service
public class SitiosService {

	@Autowired
	private ISitioDao sitioDao;

	public Iterable<Tmio1Sitio> findAll() {
		return sitioDao.findAll();
	}
	@Transactional
	public Tmio1Sitio save(Tmio1Sitio tmio1Sitio) {
		
		sitioDao.save(tmio1Sitio);
		return tmio1Sitio;
	}
	
	public Tmio1Sitio findById(long id) {
		return sitioDao.findById(id);
	}
	
	@Transactional
	public void delete(long id) {
		Tmio1Sitio sitio = findById(id);
		sitioDao.delete(sitio);
	}
	
	@Transactional
	public Tmio1Sitio update(Tmio1Sitio tmio1Sitio) {
		Tmio1Sitio s = findById(tmio1Sitio.getId());
		s.setDescripcion(tmio1Sitio.getDescripcion());
		s.setNombre(tmio1Sitio.getNombre());
		
		sitioDao.save(s);
		return tmio1Sitio;
	}
}
