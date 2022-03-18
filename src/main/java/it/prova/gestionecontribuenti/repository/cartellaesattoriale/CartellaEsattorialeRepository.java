package it.prova.gestionecontribuenti.repository.cartellaesattoriale;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends PagingAndSortingRepository<CartellaEsattoriale, Long>, JpaSpecificationExecutor<CartellaEsattoriale>{
	@Query("from CartellaEsattoriale ce join fetch ce.contribuente c where ce.id = ?1")
	CartellaEsattoriale findSingleCartellaEager(Long id);
}