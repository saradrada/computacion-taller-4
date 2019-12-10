package icesi.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.model.Tmio1Sitio;

@Repository
@Scope("singleton")
public class SitioDao implements ISitioDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Tmio1Sitio> findAll() {
		String jpql = "Select a from Tmio1Sitio a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Sitio entity) {
		entityManager.persist(entity);
	}

	@Override
	public Tmio1Sitio update(Tmio1Sitio entity) {
		return entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Sitio entity) {
		entityManager.remove(entity);
	}
	
	@Override
	public Tmio1Sitio findById(Long id) {
		return entityManager.find(Tmio1Sitio.class, id);
	}

}
