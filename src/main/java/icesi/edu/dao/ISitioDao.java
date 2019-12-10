package icesi.edu.dao;

import java.util.List;

import icesi.edu.model.Tmio1Sitio;

public interface ISitioDao {

	public void save(Tmio1Sitio entity);

	public Tmio1Sitio update(Tmio1Sitio entity);

	public void delete(Tmio1Sitio entity);

	public List<Tmio1Sitio> findAll();

	public Tmio1Sitio findById(Long id);

}
