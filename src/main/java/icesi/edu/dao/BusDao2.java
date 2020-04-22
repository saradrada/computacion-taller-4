package icesi.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.model.Tmio1Bus;

@Repository
@Scope("singleton")
public class BusDao2 implements IBusDao2 {

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public void save(Tmio1Bus tmio1Bus) {
		entityManager.persist(tmio1Bus);
	}

	@Override
	public void update(Tmio1Bus tmio1Bus) {
		entityManager.merge(tmio1Bus);
	}

	@Override
	public void delete(Tmio1Bus tmio1Bus) {
		entityManager.remove(tmio1Bus);
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
