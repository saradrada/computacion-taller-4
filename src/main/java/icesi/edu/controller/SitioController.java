package icesi.edu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import icesi.edu.delegate.SitioDelegate;
import icesi.edu.model.Tmio1Sitio;

@Controller
public class SitioController {

	private SitioDelegate delegate;

	@Autowired
	public SitioController(SitioDelegate delegate) {
		this.delegate = delegate;
	}

	@GetMapping("/sitios")
	public String consultarForm(Model model) {
		model.addAttribute("sitios", delegate.findAll());
		System.out.print("");
		return "sitios/index";
	}

	@GetMapping("/sitios/del/{id}")
	public String deleteUser(@PathVariable("id") long id) {

		try {
			Tmio1Sitio tmio1Sitio = delegate.findById(id);
			delegate.delete(tmio1Sitio);
		} catch (Exception e) {
		}
		return "redirect:/sitios";
	}

	@GetMapping("/sitios/add")
	public String busesAnadir(Model model) {
		model.addAttribute("tmio1Sitio", new Tmio1Sitio());
		return "sitios/add";
	}

	@PostMapping("/sitios/add")
	public String guardarBuses(@RequestParam(value = "action", required = true) String action,
			@Validated Tmio1Sitio tmio1Sitio, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "sitios/add";
			} else {
				try {
					
				
					delegate.save(tmio1Sitio);

				} catch (Exception e) {
					return "redirect:/error";
				}
			}
		}
		return "redirect:/sitios";
	}
	
	
	
	@GetMapping("/sitios/edit/{id}")
	public String showUpdateApps(@PathVariable("id") long id, Model model) {
		Tmio1Sitio tmio1Sitio = delegate.findById(id);
		if (tmio1Sitio == null) {
			throw new IllegalArgumentException("Invalid appointment Id:" + id);
		}
		model.addAttribute("tmio1Sitio", tmio1Sitio);
		return "sitios/update";
	}
	
	
	@PostMapping("/sitios/edit/{id}")
	public String updateApp(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Sitio tmio1Sitio, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				
				return "sitios/update";
			} else
				try {
					delegate.update(tmio1Sitio);
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/error";
				}
		}
		return "redirect:/sitios";
	}
	
	

}
