package it.prova.gestionecontribuenti.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contribuente")
public class Contribuente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "datadinascita")
	private Date dataDiNascita;
	
	@Column(name = "codicefiscale")
	private String codiceFiscale;
	
	@Column(name = "indirizzo")
	private String indirizzo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contribuente")
	private Set<CartellaEsattoriale> cartelle = new HashSet<CartellaEsattoriale>(0);

	public Contribuente() {
	}

	public Contribuente(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo, Set<CartellaEsattoriale> cartelle) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelle = cartelle;
	}

	public Contribuente(String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo,
			Set<CartellaEsattoriale> cartelle) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelle = cartelle;
	}

	public Contribuente(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public Contribuente(String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo) {
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

	public Set<CartellaEsattoriale> getCartelle() {
		return cartelle;
	}

	public void setCartelle(Set<CartellaEsattoriale> cartelle) {
		this.cartelle = cartelle;
	}
}