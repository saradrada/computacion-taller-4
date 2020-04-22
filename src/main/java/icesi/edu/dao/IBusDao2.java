package icesi.edu.dao;

import icesi.edu.model.Tmio1Bus;

public interface IBusDao2 {

	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	public Tmio1Bus findById(Integer id);
	public Iterable<Tmio1Bus> findAll();
}
