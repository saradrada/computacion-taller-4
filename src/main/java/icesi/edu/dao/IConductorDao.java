package icesi.edu.dao;

import java.util.Date;
import java.util.List;

import icesi.edu.model.Tmio1Conductore;

public interface IConductorDao {
	
	public List<Tmio1Conductore> findByNombre(String nombre);
	public List<Tmio1Conductore> findByApellidos(String apellidos);

	public void save(Tmio1Conductore entity);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	public List<Tmio1Conductore> findAll();
	
	public Tmio1Conductore findByCedula(String cedula);
}
