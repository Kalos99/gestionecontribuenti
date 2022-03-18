package it.prova.gestionecontribuenti.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.model.Stato;

public class CartellaEsattorialeDTO {
	
	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotNull(message = "{importo.notnull}")
	@Min(1)
	private Integer importo;
	
	@NotNull(message = "{stato.notblank}")
	private Stato stato;

	@NotNull(message = "{contribuente.notnull}")
	private ContribuenteDTO contribuente;

	public CartellaEsattorialeDTO() {
	}

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, Stato stato, ContribuenteDTO contribuente) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public CartellaEsattorialeDTO(String descrizione, Integer importo, Stato stato, ContribuenteDTO contribuente) {
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, Stato stato) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public CartellaEsattorialeDTO(String descrizione, Integer importo, Stato stato) {
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getImporto() {
		return importo;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public ContribuenteDTO getContribuente() {
		return contribuente;
	}

	public void setContribuente(ContribuenteDTO contribuente) {
		this.contribuente = contribuente;
	}
	
	public CartellaEsattoriale buildCartellaEsattorialeModel() {
		return new CartellaEsattoriale(this.id, this.descrizione, this.importo, this.stato, this.contribuente.buildContribuenteModel());
	}

	public static CartellaEsattorialeDTO buildCartellaEsattorialeDTOFromModel(CartellaEsattoriale cartellaModel, boolean includeContribuenti) {
		CartellaEsattorialeDTO result = new CartellaEsattorialeDTO(cartellaModel.getId(), cartellaModel.getDescrizione(), cartellaModel.getImporto(),
				cartellaModel.getStato());

		if (includeContribuenti)
			result.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(cartellaModel.getContribuente()));

		return result;
	}

	public static List<CartellaEsattorialeDTO> createCartellaEsattorialeDTOListFromModelList(List<CartellaEsattoriale> modelListInput, boolean includeContribuenti) {
		return modelListInput.stream().map(cartellaEntity -> {
			return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEntity, includeContribuenti);
		}).collect(Collectors.toList());
	}
}