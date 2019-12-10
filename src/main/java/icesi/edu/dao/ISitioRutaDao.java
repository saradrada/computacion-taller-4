package icesi.edu.dao;

import java.util.List;

import icesi.edu.model.Tmio1SitiosRuta;
import icesi.edu.model.Tmio1SitiosRutaPK;

public interface ISitioRutaDao {

	public void save(Tmio1SitiosRuta entity);

	public void update(Tmio1SitiosRuta entity);

	public void delete(Tmio1SitiosRuta entity);

	public List<Tmio1SitiosRuta> findAll();

	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id);
}
