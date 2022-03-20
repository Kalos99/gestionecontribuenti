package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionecontribuenti.dto.ContribuenteConCartelleDTO;
import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.exceptions.CartelleAssociateException;
import it.prova.gestionecontribuenti.exceptions.ElementNotFoundException;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.service.ContribuenteService;

@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {
	
	@Autowired
	private ContribuenteService contribuenteService;
	
	@GetMapping
	public ModelAndView listAllContribuenti() {
		ModelAndView mv = new ModelAndView();
		List<Contribuente> contribuenti = contribuenteService.listAllElements();
		// trasformiamo in DTO
		mv.addObject("contribuenti_list_attribute", ContribuenteConCartelleDTO.createContribuenteConCartelleDTOListFromModelList(contribuenti));
		mv.setViewName("contribuente/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createContribuente(Model model) {
		model.addAttribute("insert_contribuente_attr", new ContribuenteDTO());
		return "contribuente/insert";
	}
	
	@PostMapping("/save")
	public String saveContribuente(@Valid @ModelAttribute("insert_contribuente_attr") ContribuenteDTO contribuenteDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "contribuente/insert";
		}
		contribuenteService.inserisciNuovo(contribuenteDTO.buildContribuenteModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
	@GetMapping(value = "/searchContribuentiAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchContribuente(@RequestParam String term) {

		List<Contribuente> listaContribuenteByTerm = contribuenteService.cercaByCognomeENomeILike(term);
		return buildJsonResponse(listaContribuenteByTerm);
	}

	private String buildJsonResponse(List<Contribuente> listaContribuenti) {
		JsonArray ja = new JsonArray();

		for (Contribuente contribuenteItem : listaContribuenti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", contribuenteItem.getId());
			jo.addProperty("label", contribuenteItem.getNome() + " " + contribuenteItem.getCognome());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}
	
	@GetMapping("/search")
	public String searchContribuente() {
		return "contribuente/search";
	}
	
	@PostMapping("/list")
	public String listContribuenti(ContribuenteDTO contribuenteExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "0") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Contribuente> contribuenti = contribuenteService.findByExample(contribuenteExample.buildContribuenteModel(), pageNo,
				pageSize, sortBy).getContent();

		model.addAttribute("contribuenti_list_attribute", ContribuenteConCartelleDTO.createContribuenteConCartelleDTOListFromModelList(contribuenti));
		return "contribuente/list";
	}
	
	@GetMapping("/show/{idContribuente}")
	public String show(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("show_contribuente_attr", ContribuenteConCartelleDTO.buildContribuenteConCartelleDTOFromModel(contribuenteService.caricaSingoloElementoConCartelle(idContribuente)));
		return "contribuente/show";
	}
	
	@GetMapping("/delete/{idContribuente}")
	public String delete(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("delete_contribuente_attr", ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(idContribuente)));
		return "contribuente/delete";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam(required = true) Long idContribuente, RedirectAttributes redirectAttrs) {
	    try {
	        contribuenteService.rimuovi(idContribuente);
	      } catch (ElementNotFoundException e) {
	        redirectAttrs.addFlashAttribute("errorMessage", "Contribuente non trovato.");
	        return "redirect:/contribuente";
	      } catch (CartelleAssociateException e) {
		        redirectAttrs.addFlashAttribute("errorMessage", "Impossibile rimuovere: sono presenti delle cartelle esattoriali associate a questo contribuente");
		        return "redirect:/contribuente";
		      }
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
	@GetMapping("/edit/{idContribuente}")
	public String editContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("edit_contribuente_attr",
				ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(idContribuente)));
		return "contribuente/edit";
	}

	@PostMapping("/update")
	public String updateContribuente(@Valid @ModelAttribute("edit_contribuente_attr") ContribuenteDTO contribuenteDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "contribuente/edit";
		}
		contribuenteService.aggiorna(contribuenteDTO.buildContribuenteModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
}