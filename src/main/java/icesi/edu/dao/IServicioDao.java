package icesi.edu.dao;

import java.util.Date;
import java.util.List;

import icesi.edu.model.Tmio1Bus;
import icesi.edu.model.Tmio1Ruta;
import icesi.edu.model.Tmio1Servicio;
import icesi.edu.model.Tmio1ServicioPK;

public interface IServicioDao {

	public void save(Tmio1Servicio entity);
	public void update(Tmio1Servicio entity);
	public void delete(Tmio1Servicio entity);
	public List<Tmio1Servicio> findAll();
	
	public Tmio1Servicio findById(Tmio1ServicioPK id);
	
	public List<Tmio1Ruta> findRutasConServicios(Date date);
	public List<Tmio1Bus> findBusConServiciosMismoDia(Date date);
	public List<Object[]> findConductoresPorFecha(Date date);
	
	
	
}
