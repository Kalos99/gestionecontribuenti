package it.prova.gestionecontribuenti.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecontribuenti.exceptions.CartelleAssociateException;
import it.prova.gestionecontribuenti.exceptions.ElementNotFoundException;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.repository.contribuente.ContribuenteRepository;

public class ContribuenteServiceImpl implements ContribuenteService{
	
	@Autowired
	private ContribuenteRepository repository;

	@Transactional(readOnly = true)
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Contribuente caricaSingoloElementoConCartelle(Long id) {
		return repository.findById(id).get();
	}

	@Transactional
	public void aggiorna(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);		
	}

	@Transactional
	public void inserisciNuovo(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);
	}

	@Transactional
	public void rimuovi(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Id non trovato");
		}
		Contribuente contribuenteDaEliminare = repository.findById(id).get();
		if (contribuenteDaEliminare == null)
			throw new ElementNotFoundException("Contribuente con id: " + id + " non trovato.");
		if (contribuenteDaEliminare.getCartelle().size() != 0)
			throw new CartelleAssociateException("Impossibile rimuovere: sono presenti delle cartelle esattoriali associate a questo contribuente");
		repository.deleteById(id);	
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Contribuente> findByExample(Contribuente example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Contribuente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (example.getDataDiNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiNascita"), example.getDataDiNascita()));
			
			if (StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")), "%" + example.getCodiceFiscale().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getIndirizzo()))
				predicates.add(cb.like(cb.upper(root.get("indirizzo")), "%" + example.getIndirizzo().toUpperCase() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}
}