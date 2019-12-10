package icesi.edu.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.model.Tmio1Bus;
import icesi.edu.model.Tmio1Ruta;

@Repository
@Scope("singleton")
public class RutaDao implements IRutaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Tmio1Ruta> findByDate(BigDecimal diaInicio, BigDecimal diaFin) {
		String jpql = "Select a from Tmio1Ruta a WHERE a.diaInicio >= '" + diaInicio + "' AND a.diaFin <= '" + diaFin + "'";
		return entityManager.createQuery(jpql).getResultList();

	}

	@Override
	public List<Tmio1Ruta> findByHour(BigDecimal horaInicio, BigDecimal horaFin) {
		String jpql = "Select a from Tmio1Ruta a WHERE a.horaInicio <= '" +  horaInicio + "' AND a.horaFin >= '" + horaFin + "'";
		return entityManager.createQuery(jpql).getResultList();

	}

	@Override
	public void save(Tmio1Ruta entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(Tmio1Ruta entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(Tmio1Ruta entity) {
		entityManager.remove(entity);

	}

	@Override
	public List<Tmio1Ruta> findAll() {
		String jpql = "Select a from Tmio1Ruta a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Tmio1Ruta findById(Integer id) {
		return entityManager.find(Tmio1Ruta.class, id);
	}
	
	

}
