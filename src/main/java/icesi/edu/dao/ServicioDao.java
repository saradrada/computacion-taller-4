package icesi.edu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.model.Tmio1Bus;
import icesi.edu.model.Tmio1Ruta;
import icesi.edu.model.Tmio1Servicio;
import icesi.edu.model.Tmio1ServicioPK;

@Repository
@Scope("singleton")
public class ServicioDao implements IServicioDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Servicio entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Servicio entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Servicio entity) {
		entityManager.remove(entity);

	}

	@Override
	public List<Tmio1Servicio> findAll() {
		String jpql = "Select a from Tmio1Servicio a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Tmio1Servicio findById(Tmio1ServicioPK id) {
		return entityManager.find(Tmio1Servicio.class, id);
	}

	/**
	 * El(los) conductores(es) con sus datos y cantidad de servicios vigentes (para
	 * una fecha dada), ordenados por fecha.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findConductoresPorFecha(Date date) {

		String q = "select s.tmio1Conductore, count(s) from Tmio1Servicio s where :date between s.id.fechaInicio and s.id.fechaFin group by s.tmio1Ruta ORDER BY s.id.fechaInicio ASC ";
		Query query = entityManager.createQuery(q);
		query.setParameter("date", date);

		List<Object[]> results = query.getResultList();

		return results;
	}

	/**
	 * Mostrar las rutas que tienen servicios pero la cantidad es menor a diez para
	 * una fecha dada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Ruta> findRutasConServicios(Date date) {

		String q = "select s.tmio1Ruta from Tmio1Servicio s where :date between s.id.fechaInicio and s.id.fechaFin group by s.tmio1Ruta having count(s) < 10";
		Query query = entityManager.createQuery(q);
		query.setParameter("date", date);
		return query.getResultList();
	}

	/**
	 * Listado de los buses que tienen más de un servicio en el mismo día
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1Bus> findBusConServiciosMismoDia(Date date) {
		List<Tmio1Bus> retorno = new ArrayList<Tmio1Bus>();

		String q = "select s.tmio1Bus , COUNT(s.tmio1Bus) from Tmio1Servicio s where :date between s.id.fechaInicio and s.id.fechaFin group by s.tmio1Bus ";
		Query query = entityManager.createQuery(q);

		query.setParameter("date", date);
		List<Object[]> results = query.getResultList();

		for (Object[] result : results) {
			Tmio1Bus n = (Tmio1Bus) result[0];
			System.out.println(n.getPlaca());
			int count = ((Number) result[1]).intValue();
			System.out.println(count);
			if (count > 1)
				retorno.add(n);
		}

		return retorno;

	}

}
