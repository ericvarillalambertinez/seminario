package com.hbt.semillero.entidad;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b>DescripciÃ³n:<b> Clase que determina la entidad que permite representar la
 * tabla "DB_SEMILLERO"."COMPRA"
 * 
 * @author Eric Varilla ING
 * @version
 */
@Entity
@Table(name = "COMPRA")
public class Compra {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id Ãºnico que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "COMPRA_CA_ID_GENERATOR", sequenceName = "SEC_COMPRA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_CA_ID_GENERATOR")
	@Column(name = "CA_ID")
	private Long id;
	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinColumn(name="COMIC")
	@JoinTable(name = "COMIC", joinColumns = @JoinColumn(name = "CA_LCOMIC", nullable = false), inverseJoinColumns = @JoinColumn(name = "CA_PERSO", nullable = false))
	@ManyToMany(fetch = FetchType.LAZY)
	private Comic comic;
	@JoinTable(name = "PERSONAS", joinColumns = @JoinColumn(name = "CA_PERSO", nullable = false), inverseJoinColumns = @JoinColumn(name = "CA_LCOMIC", nullable = false))
	@ManyToMany(fetch = FetchType.LAZY)
	private Personas persona;
	@Column(name = "CA_FECHAVENTA")
	private LocalDate fechaVenta;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Comic getComic() {
		return comic;
	}
	public void setComic(Comic comic) {
		this.comic = comic;
	}
	public Personas getPersona() {
		return persona;
	}
	public void setPersona(Personas persona) {
		this.persona = persona;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

}
