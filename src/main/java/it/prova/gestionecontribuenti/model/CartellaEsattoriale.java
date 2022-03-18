package it.prova.gestionecontribuenti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartellaesattoriale")
public class CartellaEsattoriale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "importo")
	private Integer importo;
	
	@Column(name = "stato")
	@Enumerated(EnumType.STRING)
	private Stato stato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contribuente_id", nullable = false)
	private Contribuente contribuente;

	public CartellaEsattoriale() {
	}

	public CartellaEsattoriale(Long id, String descrizione, Integer importo, Stato stato, Contribuente contribuente) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public CartellaEsattoriale(String descrizione, Integer importo, Stato stato, Contribuente contribuente) {
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public CartellaEsattoriale(Long id, String descrizione, Integer importo, Stato stato) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public CartellaEsattoriale(String descrizione, Integer importo, Stato stato) {
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

	public Contribuente getContribuente() {
		return contribuente;
	}

	public void setContribuente(Contribuente contribuente) {
		this.contribuente = contribuente;
	}
}