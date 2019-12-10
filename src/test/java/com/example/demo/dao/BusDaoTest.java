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
import icesi.edu.dao.IBusDao;
import icesi.edu.model.BusType;
import icesi.edu.model.Tmio1Bus;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
public class BusDaoTest {

	@Autowired
	private IBusDao busDao;

	private Tmio1Bus bus;

	public void setUp() {
		bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo(BusType.T);
		bus.setMarca("A");
		bus.setModelo(new BigDecimal(1));
		bus.setPlaca("A");
		busDao.save(bus);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveBus() {
		assertNotNull(busDao);
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo(BusType.T);
		bus.setMarca("A");
		bus.setModelo(new BigDecimal(1));
		bus.setPlaca("A");
		try {
			busDao.save(bus);

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByPlaca() {
		setUp();
		assertNotNull(busDao);
		List<Tmio1Bus> busLista = busDao.findByPlaca("A");
		assertThat(busLista.size() >= 1);

		if (busLista.size() > 0) {
			Tmio1Bus busResultado = busLista.get(0);
			assertNotNull(busResultado);
		} else {
			fail();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByMarca() {
		setUp();
		assertNotNull(busDao);
		List<Tmio1Bus> busLista = busDao.findByMarca("A");
		assertThat(busLista.size() >= 1);

		if (busLista.size() > 0) {
			Tmio1Bus busResultado = busLista.get(0);
			assertNotNull(busResultado);
		} else {
			fail();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByModelo() {
		setUp();
		assertNotNull(busDao);
		List<Tmio1Bus> busLista = busDao.findByModelo(new BigDecimal("1"));
		assertThat(busLista.size() >= 1);

		if (busLista.size() > 0) {
			Tmio1Bus busResultado = busLista.get(0);
			assertNotNull(busResultado);
		} else {
			fail();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		setUp();
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(2));
		bus2.setTipo(BusType.P);
		bus2.setMarca("B");
		bus2.setModelo(new BigDecimal(2));
		bus2.setPlaca("B");

		assertNotNull(busDao);
		busDao.update(bus2);
		List<Tmio1Bus> busLista = busDao.findByPlaca("B");
		assertThat(busLista.size() >= 1);

		if (busLista.size() > 0) {
			Tmio1Bus busResultado = busLista.get(0);
			assertNotNull(busResultado);
			assertEquals(busResultado.getCapacidad(), new BigDecimal("2"));
			assertEquals(busResultado.getTipo(), BusType.P);
			assertEquals(busResultado.getMarca(), "B");
			assertEquals(busResultado.getModelo(), new BigDecimal("2"));
		} else {
			fail();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		setUp();
		assertNotNull(busDao);
		busDao.delete(bus);
		assertThat(busDao.findAll().size() == 0);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		setUp();
		assertNotNull(busDao);
		Tmio1Bus busResultado = busDao.findById(1);
		assertNotNull(busResultado);
	}
}
