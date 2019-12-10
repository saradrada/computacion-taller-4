package icesi.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.model.Tmio1SitiosRuta;
import icesi.edu.model.Tmio1SitiosRutaPK;

@Repository
@Scope("singleton")
public class SitioRutaDao implements ISitioRutaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Tmio1SitiosRuta entity) {
		entityManager.persist(entity);

	}

	@Override
	@Transactional
	public void update(Tmio1SitiosRuta entity) {
		entityManager.merge(entity);

	}

	@Override
	@Transactional
	public void delete(Tmio1SitiosRuta entity) {
		entityManager.remove(entity);

	}

	@Override
	@Transactional
	public List<Tmio1SitiosRuta> findAll() {
		String jpql = "Select a from Tmio1SitiosRuta a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id) {
		return entityManager.find(Tmio1SitiosRuta.class, id);
	}

}
