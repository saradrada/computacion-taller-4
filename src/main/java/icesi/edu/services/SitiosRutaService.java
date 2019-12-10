package icesi.edu.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.dao.ISitioDao;
import icesi.edu.dao.ISitioRutaDao;
import icesi.edu.model.Tmio1SitiosRuta;
import icesi.edu.model.Tmio1SitiosRutaPK;

@Service
public class SitiosRutaService {

	@Autowired
	private ISitioRutaDao sitioRutaDao;
	
	@Autowired
	private ISitioDao sitioDao;
	
	public Iterable<Tmio1SitiosRuta> findAll() {
		return sitioRutaDao.findAll();
	}
	
	@Transactional
	public Tmio1SitiosRuta save(Tmio1SitiosRuta tmio1SitioRuta) {
		tmio1SitioRuta.setTmio1Sitio1(sitioDao.findById((long)tmio1SitioRuta.getId().getIdSitio()));
		
		Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
		pk.setIdRuta(tmio1SitioRuta.getTmio1Ruta1().getId());
		Integer i = ((int)tmio1SitioRuta.getTmio1Sitio1().getId());
		pk.setIdSitio(i);
		
		tmio1SitioRuta.setId_hash(pk.hashCode());
		
		sitioRutaDao.save(tmio1SitioRuta);
		return tmio1SitioRuta;
	}
	
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id) {
		return sitioRutaDao.findById(id);
	}
	
	@Transactional
	public void delete(Tmio1SitiosRuta tmio1SitioRuta) {
		sitioRutaDao.delete(tmio1SitioRuta);
	}
	
}
