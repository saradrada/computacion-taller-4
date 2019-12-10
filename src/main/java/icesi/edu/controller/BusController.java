package icesi.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import icesi.edu.delegate.BusDelegate;
import icesi.edu.exceptions.BusNullException;
import icesi.edu.exceptions.BusTipoException;
import icesi.edu.exceptions.CapacidadNullException;
import icesi.edu.exceptions.GuardarBusException;
import icesi.edu.exceptions.TipoNullException;
import icesi.edu.model.Tmio1Bus;
import icesi.edu.services.BusService;

@Controller
public class BusController {

	private BusDelegate delegate;


	@Autowired
	public BusController(BusService service, BusDelegate delegate) {
		this.delegate = delegate;
	}
	
	
	@GetMapping("/buses/consult")
	public String consultarForm(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		return "buses/consult";
	}

	@PostMapping("/buses/consult")
	public String showNewConsultarForm(@ModelAttribute Tmio1Bus tmio1Bus, Model model) {

		Tmio1Bus b;
		try {
			b = delegate.findById(tmio1Bus.getId());
		} catch (Exception e) {
			
			e.printStackTrace();
			return "redirect:/error";
		}
		model.addAttribute("tmio1Bus", b);
		return "buses/edit";
	}

	@RequestMapping(value = "/buses", method = RequestMethod.GET)
	public String busesIndex(Model model) {
		model.addAttribute("buses", delegate.findAll());
		return "buses/index";
	}

	@GetMapping("/buses/add")
	public String busesAnadir(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		try {
			model.addAttribute("types", delegate.getTypes());
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error";
		}
		
		return "buses/add";
	}

	@PostMapping("/buses/add")
	public String guardarBuses(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Bus tmio1Bus,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				try {
					model.addAttribute("types", delegate.getTypes());
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/error";
				}
				return "buses/add";
			} else {
				try {
					delegate.save(tmio1Bus);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			try {
				model.addAttribute("types", delegate.getTypes());
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/error";
			}
		}
		return "redirect:/buses";
	}


}
