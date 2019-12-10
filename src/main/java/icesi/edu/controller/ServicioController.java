package icesi.edu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import icesi.edu.delegate.BusDelegate;
import icesi.edu.delegate.ConductorDelegate;
import icesi.edu.delegate.RutasDelegate;
import icesi.edu.delegate.ServiciosDelegate;
import icesi.edu.exceptions.BusNullException;
import icesi.edu.exceptions.ConductorNullException;
import icesi.edu.exceptions.FechasNoConsistentesException;
import icesi.edu.exceptions.RutaNullException;
import icesi.edu.exceptions.ServicioNullException;
import icesi.edu.model.Tmio1Servicio;
import icesi.edu.services.BusService;
import icesi.edu.services.ConductorService;
import icesi.edu.services.RutaService;
import icesi.edu.services.ServicioService;

@Controller
public class ServicioController {

	
	private ServiciosDelegate delegate;
	private RutasDelegate rutasDelegate;
	private ConductorDelegate conductorDelegate;
	private BusDelegate busDelegate;

	@Autowired
	public ServicioController(ServicioService service, ServiciosDelegate delegate, RutasDelegate rutasDelegate,
			ConductorDelegate conductorDelegate, BusDelegate busDelegate) {
		this.service = service;
		this.delegate = delegate;
		this.rutasDelegate = rutasDelegate;
		this.conductorDelegate = conductorDelegate;
		this.busDelegate = busDelegate;

	}

	@RequestMapping(value = "/servicios", method = RequestMethod.GET)
	public String servicios(Model model) {
		model.addAttribute("servicios", delegate.findAll());
		return "servicios/index";
	}

	@GetMapping("/servicios/add-servicio")
	public String busesAdd(Model model) {
		
		model.addAttribute("buses", busDelegate.findAll());
		model.addAttribute("conductores", conductorDelegate.findAll());
		model.addAttribute("rutas", rutasDelegate.findAll());
		
		
		model.addAttribute("tmio1Servicio", new Tmio1Servicio());
		return "servicios/add";
	}

	@PostMapping("/servicios/add-servicio")
	public String saveApps(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Servicio tmio1Servicio, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {

				
				model.addAttribute("buses", busDelegate.findAll());
				model.addAttribute("conductores", conductorDelegate.findAll());
				model.addAttribute("rutas", rutasDelegate.findAll());

				return "servicios/add";
			} else
				try {
					delegate.save(tmio1Servicio);
				} catch (BusNullException | ConductorNullException | RutaNullException | FechasNoConsistentesException
						| ServicioNullException e) {
					return "redirect:/error";
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			model.addAttribute("buses", busDelegate.findAll());
			model.addAttribute("conductores", conductorDelegate.findAll());
			model.addAttribute("rutas", rutasDelegate.findAll());

		}
		return "redirect:/servicios";
	}

	@GetMapping("/servicios/update-servicio/{id_hash}")
	public String showUpdateApps(@PathVariable("id_hash") Integer id_hash, Model model) {

		Iterable<Tmio1Servicio> servicios = delegate.findAll();
		Tmio1Servicio servicio = null;

		for (Tmio1Servicio ser : servicios) {
			if (ser.getId_hash().compareTo(id_hash) == 0) {
				delegate.delete(id_hash);
				servicio = ser;
				break;
			}
		}
		
		model.addAttribute("buses", busDelegate.findAll());
		model.addAttribute("conductores", conductorDelegate.findAll());
		model.addAttribute("rutas", rutasDelegate.findAll());

		model.addAttribute("tmio1Servicio", servicio);
		return "servicios/update";
	}

	@PostMapping("/servicios/update-servicio/{id_hash}")
	public String updateApp(@PathVariable("id_hash") Integer id_hash,
			@RequestParam(value = "action", required = true) String action, @Valid Tmio1Servicio tmio1Servicio,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "/servicios/update";
			}
		}
		try {
			delegate.save(tmio1Servicio);
		} catch (BusNullException | ConductorNullException | RutaNullException | FechasNoConsistentesException
				| ServicioNullException e) {
			return "redirect:/error";
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		model.addAttribute("buses", busDelegate.findAll());
		model.addAttribute("conductores", conductorDelegate.findAll());
		model.addAttribute("rutas", rutasDelegate.findAll());

		return "redirect:/servicios";
	}

	@GetMapping("/servicios/filter-servicio")
	public String consultForm(Model model) {

		model.addAttribute("tmio1Servicio", new Tmio1Servicio());
		return "servicios/filter";
	}

	private ServicioService service;
	
	@PostMapping("/servicios/filter-servicio")
	public String showConsultForm2(@ModelAttribute Tmio1Servicio tmio1Servicio, Model model) {

		Iterable<Tmio1Servicio> r = service.filter(tmio1Servicio.getTmioFechaInicio(), tmio1Servicio.getTmioFechaFin());
//		Iterable<Tmio1Servicio> r = delegate.filter(tmio1Servicio.getTmioFechaInicio(), tmio1Servicio.getTmioFechaFin());
		model.addAttribute("servicios", r);

		return "servicios/index";
	}

}
