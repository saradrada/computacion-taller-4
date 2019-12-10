package icesi.edu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import icesi.edu.delegate.RutasDelegate;
import icesi.edu.delegate.SitioDelegate;
import icesi.edu.delegate.SitiosRutaDelegate;
import icesi.edu.model.Tmio1Servicio;
import icesi.edu.model.Tmio1Sitio;
import icesi.edu.model.Tmio1SitiosRuta;
import icesi.edu.model.Tmio1SitiosRutaPK;

@Controller
public class SitiosRutaController {

	private SitiosRutaDelegate delegate;

	private RutasDelegate rutasDelegate;
	private SitioDelegate sitioDelegate;

	@Autowired
	public SitiosRutaController(SitiosRutaDelegate delegate, RutasDelegate rutasDelegate, SitioDelegate sitioDelegate) {
		this.delegate = delegate;
		this.rutasDelegate = rutasDelegate;
		this.sitioDelegate = sitioDelegate;
	}

	@GetMapping("/sitiosruta")
	public String consultarForm(Model model) {
		model.addAttribute("sitios", delegate.findAll());
		return "sitiosruta/index";
	}

	@GetMapping("/sitiosruta/add")
	public String busesAdd(Model model) {

		model.addAttribute("rutas", rutasDelegate.findAll());
		model.addAttribute("sitios", sitioDelegate.findAll());

		model.addAttribute("tmio1SitiosRuta", new Tmio1SitiosRuta());
		return "sitiosruta/add";
	}

	@PostMapping("/sitiosruta/add")
	public String saveApps(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1SitiosRuta tmio1SitiosRuta, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors() && false) {

				model.addAttribute("rutas", rutasDelegate.findAll());
				model.addAttribute("sitios", sitioDelegate.findAll());

				return "sitiosruta/add";
			} else
				try {
					Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();

					pk.setIdSitio(tmio1SitiosRuta.getId2());
					pk.setIdRuta(tmio1SitiosRuta.getTmio1Ruta1().getId());
					tmio1SitiosRuta.setId(pk);

					delegate.save(tmio1SitiosRuta);

				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/error";
				}

			model.addAttribute("rutas", rutasDelegate.findAll());
			model.addAttribute("sitios", sitioDelegate.findAll());

		}
		return "redirect:/sitiosruta";
	}

	@GetMapping("/sitiosruta/edit/{id_hash}")
	public String showUpdateApps(@PathVariable("id_hash") Integer id_hash, Model model) {

		Iterable<Tmio1SitiosRuta> servicios = delegate.findAll();
		Tmio1SitiosRuta tmio1SitiosRuta = null;

		for (Tmio1SitiosRuta ser : servicios) {
			if (ser.getId_hash().compareTo(id_hash) == 0) {
				tmio1SitiosRuta = ser;
				break;
			}
		}
		model.addAttribute("tmio1SitiosRuta", tmio1SitiosRuta);
		return "sitiosruta/update";
	}

	@PostMapping("/sitiosruta/edit/{id_hash}")
	public String updateApp(@PathVariable("id_hash") Integer id_hash,
			@RequestParam(value = "action", required = true) String action, @Valid Tmio1SitiosRuta tmio1SitiosRuta,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {

				return "sitiosruta/update";
			} else
				try {
				
					delegate.save(tmio1SitiosRuta);
				} catch (Exception e) {
					return "redirect:/error";
				}
		}
		return "redirect:/sitiosruta";
	}

	@GetMapping("/sitiosruta/del/{id_hash}")
	public String deleteUser(@PathVariable("id_hash") Integer id_hash, Model model) {

		Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();

		Iterable<Tmio1SitiosRuta> servicios = delegate.findAll();
		Tmio1SitiosRuta tmio1SitiosRuta = null;

		for (Tmio1SitiosRuta ser : servicios) {
			if (ser.getId_hash().compareTo(id_hash) == 0) {
				tmio1SitiosRuta = ser;
				break;
			}
		}

		try {
			delegate.delete(tmio1SitiosRuta);
		} catch (Exception e) {
		}
		return "redirect:/sitiosruta";
	}

}
