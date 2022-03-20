package it.prova.gestionecontribuenti.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.model.Stato;

public class ContribuenteConCartelleDTO {
	
private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataDiNascita;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, max = 16, message = "Il codice fiscale deve essere lungo esattamente {max} caratteri")
	private String codiceFiscale;
	
	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;
	
	private Set<CartellaEsattoriale> cartelle = new HashSet<CartellaEsattoriale>();

	public ContribuenteConCartelleDTO() {
	}

	public ContribuenteConCartelleDTO(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo, Set<CartellaEsattoriale> cartelle) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelle = cartelle;
	}

	public ContribuenteConCartelleDTO(String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo, Set<CartellaEsattoriale> cartelle) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelle = cartelle;
	}

	public Set<CartellaEsattoriale> getCartelle() {
		return cartelle;
	}

	public void setCartelle(Set<CartellaEsattoriale> cartelle) {
		this.cartelle = cartelle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataDiNascita, this.codiceFiscale, this.indirizzo);
	}

	public static ContribuenteConCartelleDTO buildContribuenteConCartelleDTOFromModel(Contribuente contribuenteModel) {
		return new ContribuenteConCartelleDTO(contribuenteModel.getId(), contribuenteModel.getNome(), contribuenteModel.getCognome(), contribuenteModel.getDataDiNascita(), contribuenteModel.getCodiceFiscale(), contribuenteModel.getIndirizzo(), contribuenteModel.getCartelle());
	}

	public static List<ContribuenteConCartelleDTO> createContribuenteConCartelleDTOListFromModelList(List<Contribuente> modelListInput) {
		return modelListInput.stream().map(contribuenteEntity -> {
			return ContribuenteConCartelleDTO.buildContribuenteConCartelleDTOFromModel(contribuenteEntity);
		}).collect(Collectors.toList());
	}
	
	public boolean isInContenzioso() {
		for(CartellaEsattoriale cartellaItem : this.getCartelle()) {
			if(cartellaItem.getStato().equals(Stato.IN_CONTENZIOSO))
				return true;
		}
		return false;
	}
	
	public Integer calcolaImportoTotaleCartelle() {
		Integer totale = 0;
		for(CartellaEsattoriale cartellaItem : this.getCartelle()) {
			totale += cartellaItem.getImporto();
		}
		return totale;
	}
	
	public Integer calcolaImportoPagato() {
		Integer totale = 0;
		for(CartellaEsattoriale cartellaItem : this.getCartelle()) {
			if(cartellaItem.getStato().equals(Stato.CONCLUSA))
				totale += cartellaItem.getImporto();
		}
		return totale;
	}
	
	public Integer calcolaImportoInContenzioso() {
		Integer totale = 0;
		for(CartellaEsattoriale cartellaItem : this.getCartelle()) {
			if(cartellaItem.getStato().equals(Stato.IN_CONTENZIOSO))
				totale += cartellaItem.getImporto();
		}
		return totale;
	}
}