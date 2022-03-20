package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecontribuenti.dto.CartellaEsattorialeDTO;
import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.service.CartellaEsattorialeService;
import it.prova.gestionecontribuenti.service.ContribuenteService;

@Controller
@RequestMapping(value = "/cartella_esattoriale")
public class CartellaEsattorialeController {
	
	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@Autowired
	private ContribuenteService contribuenteService;
	
	@GetMapping
	public ModelAndView listAllCartelle() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.listAllElements();
		mv.addObject("cartella_list_attribute", CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartelle, false));
		mv.setViewName("cartella_esattoriale/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createCartellaEsattoriale(Model model) {
		model.addAttribute("insert_cartella_attr", new CartellaEsattorialeDTO());
		return "cartella_esattoriale/insert";
	}

	// inietto la request perché ci potrebbe tornare utile per ispezionare i
	// parametri
	@PostMapping("/save")
	public String saveCartella(@Valid @ModelAttribute("insert_cartella_attr") CartellaEsattorialeDTO cartellaDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		// se fosse un entity questa operazione sarebbe inutile perche provvederebbe
		// da solo fare il binding dell'intero oggetto. Essendo un dto dobbiamo pensarci
		// noi 'a mano'. Se validazione risulta ok devo caricare l'oggetto per 
		// visualizzarne nome e cognome nel campo testo
		if (cartellaDTO.getContribuente() == null || cartellaDTO.getContribuente() .getId() == null)
			result.rejectValue("contribuente", "contribuente.notnull");
		else
			cartellaDTO.setContribuente(ContribuenteDTO
					.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(cartellaDTO.getContribuente().getId())));

		if (result.hasErrors()) {
			return "cartella_esattoriale/insert";
		}
		cartellaEsattorialeService.inserisciNuovo(cartellaDTO.buildCartellaEsattorialeModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartella_esattoriale";
	}
	
	@GetMapping("/search")
	public String searchCartelle(Model model) {
		model.addAttribute("contribuenti_list_attribute", ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.listAllElements()));
		return "cartella_esattoriale/search";
	}
}