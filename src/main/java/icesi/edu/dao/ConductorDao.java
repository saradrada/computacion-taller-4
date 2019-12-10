package icesi.edu.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.model.Tmio1Bus;
import icesi.edu.model.Tmio1Conductore;
import icesi.edu.model.Tmio1Servicio;

@Repository
@Scope("singleton")
public class ConductorDao implements IConductorDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Tmio1Conductore> findByNombre(String nombre) {
		String jpql = "Select a from Tmio1Conductore a WHERE a.nombre = '" + nombre + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByApellidos(String apellidos) {
		String jpql = "Select a from Tmio1Conductore a WHERE a.apellidos = '" +  apellidos + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Conductore entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(Tmio1Conductore entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(Tmio1Conductore entity) {
		entityManager.remove(entity);

	}

	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql = "Select a from Tmio1Conductore a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Tmio1Conductore findByCedula(String cedula) {
		return entityManager.find(Tmio1Conductore.class, cedula);
	}
	
//	@Override
//	public List<Tmio1Conductore> findByDate(Date fechaVigente) {
//		
//		String jpql = "Select Tmio1Conductore.cedula, Tmio1Conductore.apellido, Tmio1Conductore.fechaContratacion,"
//				+ "Tmio1Conductore.fechaNacimiento, Tmio1Conductore.nombre, COUNT(Tmio1Conductore.cedula)" + 
//				"from Tmio1Conductore tc INNERJOIN Tmio1Servicio ts ON tc.cedula = ts.id.cedula_conductor"
//				+ "WHERE ts.tmioFechaFin <= '" + fechaVigente + "' ORDER BY ts.tmioFechaFin DESC";
//		
//		
//		return entityManager.createQuery(jpql).getResultList();
//	}



}
