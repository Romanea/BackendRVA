package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the artikl database table.
 * 
 */
@Entity
@NamedQuery(name="Smer.findAll", query="SELECT a FROM Artikl a")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Smer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SMER_ID_GENERATOR", sequenceName="SMER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SMER_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String oznaka;

	//bi-directional many-to-one association to Grupa
	@OneToMany(mappedBy="smer")
	@JsonIgnore
	private List<Grupa> grupas;

	public Smer() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String proizvodjac) {
		this.oznaka = proizvodjac;
	}

	public List<Grupa> getGrupas() {
		return this.grupas;
	}

	public void setStavkaPorudzbines(List<Grupa> stavkaPorudzbines) {
		this.grupas = stavkaPorudzbines;
	}

	public Grupa addStavkaPorudzbine(Grupa stavkaPorudzbine) {
		getGrupas().add(stavkaPorudzbine);
		//stavkaPorudzbine.setSmerBean(this);

		return stavkaPorudzbine;
	}

	public Grupa removeGrupa(Grupa grupa) {
		getGrupas().remove(grupa);
		//grupas.setGrupalBean(null);

		return grupa;
	}

}