package it.prova.gestionecontribuenti.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionecontribuenti.model.Contribuente;

public class ContribuenteDTO {
	
private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataDiNascita;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String codiceFiscale;
	
	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	public ContribuenteDTO() {
	}

	public ContribuenteDTO(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteDTO(String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
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

	public static ContribuenteDTO buildContribuenteDTOFromModel(Contribuente contribuenteModel) {
		return new ContribuenteDTO(contribuenteModel.getId(), contribuenteModel.getNome(), contribuenteModel.getCognome(), contribuenteModel.getDataDiNascita(), contribuenteModel.getCodiceFiscale(), contribuenteModel.getIndirizzo());
	}

	public static List<ContribuenteDTO> createContribuenteDTOListFromModelList(List<Contribuente> modelListInput) {
		return modelListInput.stream().map(contribuenteEntity -> {
			return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteEntity);
		}).collect(Collectors.toList());
	}
}