package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icesi.edu.DemoApplication;
import icesi.edu.dao.IRutaDao;
import icesi.edu.model.Tmio1Ruta;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
public class RutaDaoTest {

	@Autowired
	private IRutaDao rutaDao;

	private Tmio1Ruta ruta;

	private void setUp() {
		ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("Si");
		ruta.setDescripcion("ABC");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));
		rutaDao.save(ruta);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveRuta() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("2");
		ruta.setActiva("Si");
		ruta.setDescripcion("ABC");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		assertNotNull(rutaDao);
		try {
			rutaDao.save(ruta);
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Se deben correr todas las pruebas para que esta funcione. Porque da por hecho
	 * que se agregan las demás rutas de las otras pruebas.
	 */
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		setUp();
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setDiaFin(new BigDecimal(4));

		assertNotNull(rutaDao);
		rutaDao.update(ruta);
		List<Tmio1Ruta> rutaLista = rutaDao.findAll();
		assertThat(rutaLista.size() >= 1);

		if (rutaLista.size() > 0) {
			Tmio1Ruta rutaResultado = rutaLista.get(3);
			assertNotNull(rutaResultado);
			assertEquals(rutaResultado.getActiva(), "Si");
			assertEquals(rutaResultado.getDiaInicio(), new BigDecimal("2"));
		}

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		setUp();
		assertNotNull(rutaDao);
		rutaDao.delete(ruta);
		List<Tmio1Ruta> rutaLista = rutaDao.findAll();
		assertThat(rutaLista.size() == 0);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDate() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("2");
		ruta.setActiva("Si");
		ruta.setDescripcion("XYZ");
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		rutaDao.save(ruta);
		List<Tmio1Ruta>	lista = rutaDao.findByDate(new BigDecimal("2"), new BigDecimal("3"));
		
		assertThat(lista.size() == 1);
		assertEquals(lista.get(0).getDescripcion(), "XYZ");
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByHour() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("2");
		ruta.setActiva("Si");
		ruta.setDescripcion("XYZ");
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(100));
		ruta.setHoraFin(new BigDecimal(1000));

		rutaDao.save(ruta);
		List<Tmio1Ruta>	lista = rutaDao.findByHour(new BigDecimal("200"), new BigDecimal("1000"));
		
		System.out.println(lista.size());
		assertThat(lista.size() == 1);
		assertEquals(lista.get(0).getDescripcion(), "ABC");
	}

}
