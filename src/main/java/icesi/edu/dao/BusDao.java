package icesi.edu.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.model.Tmio1Bus;

@Repository
@Scope("singleton")
public class BusDao implements IBusDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Tmio1Bus> findByPlaca(String placa) {
		String jpql = "Select a from Tmio1Bus a WHERE a.placa = '" + placa + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		String jpql = "Select a from Tmio1Bus a WHERE a.marca = '" + marca + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal modelo) {
		String jpql = "Select a from Tmio1Bus a WHERE a.modelo = '" + modelo + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Bus entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Bus entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Bus entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<Tmio1Bus> findAll() {
		String jpql = "Select a from Tmio1Bus a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Tmio1Bus findById(Integer id) {
		return entityManager.find(Tmio1Bus.class, id);
	}

}
