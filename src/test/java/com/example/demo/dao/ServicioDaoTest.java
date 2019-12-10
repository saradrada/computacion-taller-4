package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
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
import icesi.edu.dao.IBusDao;
import icesi.edu.dao.IConductorDao;
import icesi.edu.dao.IRutaDao;
import icesi.edu.dao.IServicioDao;
import icesi.edu.model.BusType;
import icesi.edu.model.Tmio1Bus;
import icesi.edu.model.Tmio1Conductore;
import icesi.edu.model.Tmio1Ruta;
import icesi.edu.model.Tmio1Servicio;
import icesi.edu.model.Tmio1ServicioPK;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
public class ServicioDaoTest {

	@Autowired
	private IBusDao busDao;

	@Autowired
	private IConductorDao conductorDao;

	@Autowired
	private IRutaDao rutaDao;

	@Autowired
	private IServicioDao servicioDao;

	private SimpleDateFormat format;

	private Tmio1Bus bus;

	private Tmio1Ruta ruta;

	private void setUpBus() {
		bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo(BusType.T);
		bus.setMarca("A");
		bus.setModelo(new BigDecimal(1));
		bus.setPlaca("A");
	}

	private void setUpRuta() {

		ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("Si");
		ruta.setDescripcion("ABC");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));
	}

	private void setUp() {
		setUpBus();
		setUpRuta();

		format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("098");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
		}

		busDao.save(bus);
		conductorDao.save(conductor);
		rutaDao.save(ruta);

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		try {
			servicio.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		srvPK.setIdBus(servicio.getTmio1Bus().getId());
		srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
		srvPK.setFechaInicio(servicio.getTmioFechaInicio());
		srvPK.setFechaFin(servicio.getTmioFechaFin());

		servicio.setId(srvPK);
		servicio.setId_hash(srvPK.getHashCode());
		servicioDao.save(servicio);
	}

	private void setUp2() {
		setUpBus();
		setUpRuta();

		format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("987");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
		}

		busDao.save(bus);
		conductorDao.save(conductor);
		rutaDao.save(ruta);

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		try {
			servicio.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		srvPK.setIdBus(servicio.getTmio1Bus().getId());
		srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
		srvPK.setFechaInicio(servicio.getTmioFechaInicio());
		srvPK.setFechaFin(servicio.getTmioFechaFin());

		servicio.setId(srvPK);
		servicio.setId_hash(srvPK.getHashCode());
		servicioDao.save(servicio);
	}

	private void setUp3() {
		setUpBus();
		setUpRuta();

		format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("876");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
		}

		busDao.save(bus);
		conductorDao.save(conductor);
		rutaDao.save(ruta);

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		try {
			servicio.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		srvPK.setIdBus(servicio.getTmio1Bus().getId());
		srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
		srvPK.setFechaInicio(servicio.getTmioFechaInicio());
		srvPK.setFechaFin(servicio.getTmioFechaFin());

		servicio.setId(srvPK);
		servicio.setId_hash(srvPK.getHashCode());
		servicioDao.save(servicio);
	}

	private void setUp4() {
		setUpBus();
		setUpRuta();

		format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("543");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
		}

		busDao.save(bus);
		conductorDao.save(conductor);
		rutaDao.save(ruta);

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		try {
			servicio.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		srvPK.setIdBus(servicio.getTmio1Bus().getId());
		srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
		srvPK.setFechaInicio(servicio.getTmioFechaInicio());
		srvPK.setFechaFin(servicio.getTmioFechaFin());

		servicio.setId(srvPK);
		servicio.setId_hash(srvPK.getHashCode());

		Tmio1Servicio servicio2 = new Tmio1Servicio();
		servicio2.setTmio1Bus(bus);
		servicio2.setTmio1Conductore(conductor);
		servicio2.setTmio1Ruta(ruta);
		try {
			servicio2.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio2.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK2 = new Tmio1ServicioPK();
		srvPK2.setCedulaConductor(servicio2.getTmio1Conductore().getCedula());
		srvPK2.setIdBus(servicio2.getTmio1Bus().getId());
		srvPK2.setIdRuta(servicio2.getTmio1Ruta().getId());
		srvPK2.setFechaInicio(servicio2.getTmioFechaInicio());
		srvPK2.setFechaFin(servicio2.getTmioFechaFin());

		servicio2.setId(srvPK2);
		servicio2.setId_hash(srvPK2.getHashCode());

		servicioDao.save(servicio2);
	}

	private void setUp5() {

		format = new SimpleDateFormat("yyyy-MM-dd");
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal("5"));
		bus.setTipo(BusType.A);
		bus.setMarca("Toyota");
		bus.setPlaca("KKK111");
		bus.setModelo(new BigDecimal("2018"));

		Tmio1Conductore conductor = new Tmio1Conductore();
		try {
			conductor.setFechaContratacion(format.parse("2010-11-23"));
			conductor.setFechaNacimiento(format.parse("1999-08-12"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		conductor.setCedula("1144108763");
		conductor.setNombre("Daniela");
		conductor.setApellidos("Llano");

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("Si");
		ruta.setDescripcion("calle 68 hasta calle 50");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setHoraInicio(new BigDecimal(2));
		ruta.setHoraFin(new BigDecimal(2000));

		busDao.save(bus);
		conductorDao.save(conductor);
		rutaDao.save(ruta);

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		try {
			servicio.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		srvPK.setIdBus(servicio.getTmio1Bus().getId());
		srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
		srvPK.setFechaInicio(servicio.getTmioFechaInicio());
		srvPK.setFechaFin(servicio.getTmioFechaFin());

		servicio.setId(srvPK);
		servicio.setId_hash(srvPK.getHashCode());
		servicioDao.save(servicio);
		// --------------------------------------------------------------------------------------
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal("8"));
		bus2.setTipo(BusType.T);
		bus2.setMarca("Dodge");
		bus2.setPlaca("DDD123");
		bus2.setModelo(new BigDecimal("2019"));

		Tmio1Conductore conductor2 = new Tmio1Conductore();
		conductor2 = new Tmio1Conductore();
		try {
			conductor2.setFechaContratacion(format.parse("2000-03-16"));
			conductor2.setFechaNacimiento(format.parse("1993-06-02"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		conductor2.setCedula("1100234435");
		conductor2.setNombre("Santiago");
		conductor2.setApellidos("Ortiz");

		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setNumero("4");
		ruta2.setActiva("Si");
		ruta2.setDescripcion("ABC");
		ruta2.setDiaInicio(new BigDecimal(2));
		ruta2.setDiaFin(new BigDecimal(4));
		ruta2.setHoraInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal(1000));

		busDao.save(bus2);
		conductorDao.save(conductor2);
		rutaDao.save(ruta2);

		Tmio1Servicio servicio2 = new Tmio1Servicio();
		servicio2.setTmio1Bus(bus2);
		servicio2.setTmio1Conductore(conductor2);
		servicio2.setTmio1Ruta(ruta2);
		try {
			servicio2.setTmioFechaInicio(format.parse("1990-11-12"));
			servicio2.setTmioFechaFin(format.parse("2019-12-20"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK2 = new Tmio1ServicioPK();
		srvPK2.setCedulaConductor(servicio2.getTmio1Conductore().getCedula());
		srvPK2.setIdBus(servicio2.getTmio1Bus().getId());
		srvPK2.setIdRuta(servicio2.getTmio1Ruta().getId());
		srvPK2.setFechaInicio(servicio2.getTmioFechaInicio());
		srvPK2.setFechaFin(servicio2.getTmioFechaFin());

		servicio2.setId(srvPK2);
		servicio2.setId_hash(srvPK2.getHashCode());
		servicioDao.save(servicio2);
		// -------------------------------------------------------------------------------------
		Tmio1Bus bus3 = new Tmio1Bus();
		bus3.setCapacidad(new BigDecimal("5"));
		bus3.setTipo(BusType.P);
		bus3.setMarca("AAA");
		bus3.setPlaca("A2");
		bus3.setModelo(new BigDecimal("20"));

		Tmio1Conductore conductor3 = new Tmio1Conductore();
		conductor3 = new Tmio1Conductore();
		try {
			conductor3.setFechaContratacion(format.parse("2016-02-18"));
			conductor3.setFechaNacimiento(format.parse("1992-01-24"));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		conductor3.setCedula("1122200111");
		conductor3.setNombre("Samuel");
		conductor3.setApellidos("Ortiz");

		Tmio1Ruta ruta3 = new Tmio1Ruta();
		ruta3.setNumero("3");
		ruta3.setActiva("Si");
		ruta3.setDescripcion("ABC");
		ruta3.setDiaInicio(new BigDecimal(1));
		ruta3.setDiaFin(new BigDecimal(7));
		ruta3.setHoraInicio(new BigDecimal(10));
		ruta3.setHoraFin(new BigDecimal(10000));

		busDao.save(bus3);
		conductorDao.save(conductor3);
		rutaDao.save(ruta3);

		Tmio1Servicio servicio3 = new Tmio1Servicio();
		servicio3.setTmio1Bus(bus3);
		servicio3.setTmio1Conductore(conductor3);
		servicio3.setTmio1Ruta(ruta3);
		try {
			servicio3.setTmioFechaInicio(format.parse("2001-10-08"));
			servicio3.setTmioFechaFin(format.parse("2019-06-10"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK3 = new Tmio1ServicioPK();
		srvPK3.setCedulaConductor(servicio3.getTmio1Conductore().getCedula());
		srvPK3.setIdBus(servicio3.getTmio1Bus().getId());
		srvPK3.setIdRuta(servicio3.getTmio1Ruta().getId());
		srvPK3.setFechaInicio(servicio3.getTmioFechaInicio());
		srvPK3.setFechaFin(servicio3.getTmioFechaFin());

		servicio3.setId(srvPK3);
		servicio3.setId_hash(srvPK3.getHashCode());
		servicioDao.save(servicio3);

		// -----------------------------------------------------------------------------------------------
		Tmio1Bus bus4 = new Tmio1Bus();
		bus4.setCapacidad(new BigDecimal("7"));
		bus4.setTipo(BusType.A);
		bus4.setMarca("A1");
		bus4.setPlaca("ABC");
		bus4.setModelo(new BigDecimal("12"));

		Tmio1Conductore conductor4 = new Tmio1Conductore();
		conductor4 = new Tmio1Conductore();
		try {
			conductor4.setFechaContratacion(format.parse("2011-11-15"));
			conductor4.setFechaNacimiento(format.parse("1991-01-11"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		conductor4.setCedula("1001234761");
		conductor4.setNombre("Daniel");
		conductor4.setApellidos("Ortiz");

		Tmio1Ruta ruta4 = new Tmio1Ruta();
		ruta4.setNumero("4");
		ruta4.setActiva("Si");
		ruta4.setDescripcion("ABC");
		ruta4.setDiaInicio(new BigDecimal(3));
		ruta4.setDiaFin(new BigDecimal(7));
		ruta4.setHoraInicio(new BigDecimal(3));
		ruta4.setHoraFin(new BigDecimal(3000));

		busDao.save(bus4);
		conductorDao.save(conductor4);
		rutaDao.save(ruta4);

		Tmio1Servicio servicio4 = new Tmio1Servicio();
		servicio4.setTmio1Bus(bus4);
		servicio4.setTmio1Conductore(conductor4);
		servicio4.setTmio1Ruta(ruta4);
		try {
			servicio4.setTmioFechaInicio(format.parse("1960-12-24"));
			servicio4.setTmioFechaFin(format.parse("2023-11-11"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK4 = new Tmio1ServicioPK();
		srvPK4.setCedulaConductor(servicio4.getTmio1Conductore().getCedula());
		srvPK4.setIdBus(servicio4.getTmio1Bus().getId());
		srvPK4.setIdRuta(servicio4.getTmio1Ruta().getId());
		srvPK4.setFechaInicio(servicio4.getTmioFechaInicio());
		srvPK4.setFechaFin(servicio4.getTmioFechaFin());

		servicio4.setId(srvPK4);
		servicio4.setId_hash(srvPK4.getHashCode());
		servicioDao.save(servicio4);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(busDao);
		assertNotNull(conductorDao);
		assertNotNull(rutaDao);
		assertNotNull(servicioDao);

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo(BusType.P);
		bus.setMarca("B");
		bus.setModelo(new BigDecimal(1));
		bus.setPlaca("B");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setNombre("Isabella");
		conductor.setApellidos("Ortiz Drada");
		conductor.setCedula("456");
		try {
			conductor.setFechaContratacion(format.parse("2009-12-31"));
			conductor.setFechaNacimiento(format.parse("1999-12-31"));
		} catch (ParseException e) {
		}

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("2");
		ruta.setActiva("Si");
		ruta.setDescripcion("ABC");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		busDao.save(bus);
		conductorDao.save(conductor);
		rutaDao.save(ruta);

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		try {
			servicio.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		srvPK.setIdBus(servicio.getTmio1Bus().getId());
		srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
		srvPK.setFechaInicio(servicio.getTmioFechaInicio());
		srvPK.setFechaFin(servicio.getTmioFechaFin());

		servicio.setId(srvPK);
		servicio.setId_hash(srvPK.getHashCode());
		servicioDao.save(servicio);

		assertThat(servicioDao.findAll().size() >= 1);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() throws ParseException {

		assertNotNull(busDao);
		assertNotNull(conductorDao);
		assertNotNull(rutaDao);
		assertNotNull(servicioDao);
		setUp();
		Tmio1Servicio servicioRespuesta = servicioDao.findAll().get(0);

		servicioRespuesta.setTmioFechaInicio(format.parse("2018-06-14"));
		servicioRespuesta.setTmioFechaFin(format.parse("2019-06-12"));

		servicioDao.update(servicioRespuesta);
		assertNotNull(servicioDao.findById(servicioRespuesta.getId()));

		assertEquals(format.parse("2018-06-14"), servicioDao.findById(servicioRespuesta.getId()).getTmioFechaInicio());
		assertEquals(format.parse("2019-06-12"), servicioDao.findById(servicioRespuesta.getId()).getTmioFechaFin());

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteServicio() {

		assertNotNull(busDao);
		assertNotNull(conductorDao);
		assertNotNull(rutaDao);
		assertNotNull(servicioDao);
		setUp2();

		Tmio1Servicio servicio = servicioDao.findAll().get(0);
		Tmio1ServicioPK servicioPK = servicio.getId();

		servicioDao.delete(servicioDao.findById(servicioPK));
		assertNull(servicioDao.findById(servicioPK));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {

		assertNotNull(busDao);
		assertNotNull(conductorDao);
		assertNotNull(rutaDao);
		assertNotNull(servicioDao);
		setUp3();

		Tmio1Servicio servicio = servicioDao.findAll().get(0);
		Tmio1ServicioPK servicioPK = servicio.getId();
		assertNotNull(servicioDao.findById(servicioPK));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindRutasConServicios() {
		
		assertNotNull(busDao);
		assertNotNull(conductorDao);
		assertNotNull(rutaDao);
		assertNotNull(servicioDao);
		setUp5();
		List<Tmio1Ruta> waysByDate;
		try {
			waysByDate = servicioDao.findRutasConServicios(format.parse("2021-12-31"));
			assertNotNull(waysByDate);
			assertEquals(waysByDate.size(), 2);
			waysByDate = servicioDao.findRutasConServicios(format.parse("2031-12-31"));
			assertNotNull(waysByDate);
			assertEquals(0, waysByDate.size());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindBusPorFecha() {
		assertNotNull(servicioDao);

		format = new SimpleDateFormat("yyyy-MM-dd");

		Tmio1Conductore conductor = new Tmio1Conductore();
		try {
			conductor.setFechaContratacion(format.parse("2010-11-23"));
			conductor.setFechaNacimiento(format.parse("1999-08-12"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		conductor.setCedula("189");
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz");

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("12");
		ruta.setActiva("Si");
		ruta.setDescripcion("ABC");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setHoraInicio(new BigDecimal(2));
		ruta.setHoraFin(new BigDecimal(2000));

		
		conductorDao.save(conductor);
		rutaDao.save(ruta);

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(busDao.findById(1));
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		try {
			servicio.setTmioFechaInicio(format.parse("1980-12-31"));
			servicio.setTmioFechaFin(format.parse("2021-12-31"));
		} catch (Exception e) {
		}

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		srvPK.setIdBus(servicio.getTmio1Bus().getId());
		srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
		srvPK.setFechaInicio(servicio.getTmioFechaInicio());
		srvPK.setFechaFin(servicio.getTmioFechaFin());

		servicio.setId(srvPK);
		servicio.setId_hash(srvPK.getHashCode());
		servicioDao.save(servicio);
		
		
		List<Tmio1Bus> busByDate;
		try {
			busByDate = servicioDao.findBusConServiciosMismoDia(format.parse("2021-12-31"));
			assertNotNull(busByDate);

			busByDate = servicioDao.findBusConServiciosMismoDia(format.parse("2040-06-10"));

			assertNotNull(busByDate);

			assertEquals( 0, busByDate.size());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindConductoresPorFecha() {
		assertNotNull(servicioDao);

		format = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> conductorsByDate;
		try {
			conductorsByDate = servicioDao.findConductoresPorFecha(format.parse("2021-12-31"));
			assertNotNull(conductorsByDate);
			Tmio1Conductore n= (Tmio1Conductore) conductorsByDate.get(0)[0];
			int count= ((Number) conductorsByDate.get(0)[1]).intValue();
			
			assertEquals( n.getCedula(), "1001234761");
			assertEquals( 1,  count);
			
			assertEquals( 4, conductorsByDate.size());

			conductorsByDate = servicioDao.findConductoresPorFecha(format.parse("2040-06-10"));

			assertNotNull(conductorsByDate);

			assertEquals( 0, conductorsByDate.size());
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
