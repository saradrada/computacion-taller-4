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
import icesi.edu.delegate.BusDelegate2;
import icesi.edu.model.BusType;
import icesi.edu.model.Tmio1Bus;
import icesi.edu.services.BusService2;

@Controller
public class BusController2 {

	private BusDelegate2 delegate;


	@Autowired
	public BusController2(BusService2 service, BusDelegate2 delegate) {
		this.delegate = delegate;
	}

	@GetMapping("/bus/consult")
	public String consultForm(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		return "bus/consult";
	}

	@PostMapping("/bus/consult")
	public String showConsultForm(@ModelAttribute Tmio1Bus tmio1Bus, Model model) {

		Tmio1Bus bus;
		try {
			bus = delegate.findById(tmio1Bus.getId());
		} catch (Exception e) {
			
			e.printStackTrace();
			return "redirect:/error";
		}
		model.addAttribute("tmio1Bus", bus);
		return "bus/edit";
	}

	@RequestMapping(value = "/bus", method = RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("buses", delegate.findAll());
		return "bus/index";
	}

	@GetMapping("/bus/create")
	public String create(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		try {
			model.addAttribute("types", delegate.getTypes());
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error";
		}
		
		return "bus/create";
	}

	@PostMapping("/bus/create")
	public String postCreate(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Bus tmio1Bus,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				try {
					model.addAttribute("types", delegate.getTypes());
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/error";
				}
				return "bus/create";
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
		return "redirect:/bus";
	}

}
