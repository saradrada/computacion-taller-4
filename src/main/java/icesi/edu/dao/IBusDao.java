package icesi.edu.dao;

import java.math.BigDecimal;
import java.util.List;

import icesi.edu.model.Tmio1Bus;

public interface IBusDao {
	
	
	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	
	public List<Tmio1Bus> findByPlaca(String placa);
	public List<Tmio1Bus> findByMarca(String marca);
	public List<Tmio1Bus> findByModelo(BigDecimal modelo);

	public List<Tmio1Bus> findAll();
	public Tmio1Bus findById(Integer id);
}
