package icesi.edu.dao;

import java.math.BigDecimal;
import java.util.List;

import icesi.edu.model.Tmio1Ruta;

public interface IRutaDao {

	public List<Tmio1Ruta> findByDate(BigDecimal diaInicio, BigDecimal diaFin);
	
	public List<Tmio1Ruta> findByHour(BigDecimal horaInicio, BigDecimal horaFin);
	
	
	public void save(Tmio1Ruta entity);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	public List<Tmio1Ruta> findAll();
	
	public Tmio1Ruta findById(Integer id);
}
