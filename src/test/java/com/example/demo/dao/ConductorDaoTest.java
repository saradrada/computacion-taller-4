package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import icesi.edu.dao.IConductorDao;
import icesi.edu.model.Tmio1Conductore;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
public class ConductorDaoTest {

	@Autowired
	private IConductorDao conductorDao;

	private Tmio1Conductore conductor;
	private SimpleDateFormat format;

	public void setUp1() {
		format = new SimpleDateFormat("yyyy-MM-dd");

		conductor = new Tmio1Conductore();
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("123");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
			fail();
		}

		conductorDao.save(conductor);
	}

	public void setUp2() {
		format = new SimpleDateFormat("yyyy-MM-dd");

		conductor = new Tmio1Conductore();
		conductor.setNombre("Isabella");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("456");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
			fail();
		}

		conductorDao.save(conductor);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNombre() {
		setUp1();
		assertNotNull(conductorDao);

		List<Tmio1Conductore> conductorLista = conductorDao.findByNombre("Sara");
		assertThat(conductorLista.size() >= 1);

		if (conductorLista.size() > 0) {
			Tmio1Conductore conductorResultado = conductorLista.get(0);
			assertNotNull(conductorResultado);
		} else {
			fail();
		}

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByApellidos() {
		setUp2();
		assertNotNull(conductorDao);

		List<Tmio1Conductore> conductorLista = conductorDao.findByApellidos("Ortiz Drada");
		assertThat(conductorLista.size() >= 1);

		if (conductorLista.size() > 0) {
			Tmio1Conductore conductorResultado = conductorLista.get(0);
			assertNotNull(conductorResultado);
		} else {
			fail();
		}

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		format = new SimpleDateFormat("yyyy-MM-dd");

		conductor = new Tmio1Conductore();
		conductor.setNombre("Juan Carlos");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("789");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
			fail();
		}

		assertNotNull(conductorDao);
		conductorDao.save(conductor);
		List<Tmio1Conductore> conductorLista = conductorDao.findAll();
		assertThat(conductorLista.size() >= 1);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor2 = new Tmio1Conductore();
		conductor2.setNombre("Daniel");
		conductor2.setApellidos("Ortiz");
		conductor2.setCedula("789");
		try {
			conductor2.setFechaContratacion(format.parse("2009-12-31"));
			conductor2.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
			fail();
		}

		assertNotNull(conductorDao);
		conductorDao.update(conductor2);
		assertNotNull(conductorDao.findByCedula("789"));
		assertThat(conductorDao.findByCedula("789").getNombre().equals("Daniel"));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {

		format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor2 = new Tmio1Conductore();
		conductor2.setNombre("Daniel");
		conductor2.setApellidos("Ortiz");
		conductor2.setCedula("012");
		try {
			conductor2.setFechaContratacion(format.parse("2009-12-31"));
			conductor2.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
			fail();
		}

		assertNotNull(conductorDao);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDate() {
		
	}

}
