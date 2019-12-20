package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b>DescripciÃ³n:<b> Clase que determina la entidad que permite representar la
 * tabla "DB_SEMILLERO"."PERSONAS"
 * 
 * @author Eric Varilla  ING
 * @version
 */
@Entity
@Table(name = "PERSONAS")
public class Personas implements Serializable{

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id Ãºnico que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String tDocumento;
	private Long documento;
	private LocalDate fechaNacimiento;
	
	/**
	 * Constructor de la clase
	 * */
	
	public Personas() {
		
	}
	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAS_PERSO_ID_GENERATOR", sequenceName = "SEQ_PERSONAS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAS_PERSO_ID_GENERATOR")
	@Column(name = "PERSO_ID")
	public Long getId() {
		return id;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo id
	 * 
	 * @param id El nuevo id a modificar.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * Metodo encargado de retornar el valor del atributo TIPO DE DOCUMENTO
	 * 
	 * @return El TDOCUMENTO asociado a la clase
	 */
	@Column(name = "PERSO_TDOCUMENTO")
	public String gettDocumento() {
		return tDocumento;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo TDOCMUENTO
	 * 
	 * @param TDOCUEMTNO El nuevo TDOCUMENTO a modificar.
	 */
	public void settDocumento(String tDocumento) {
		this.tDocumento = tDocumento;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo DOCUMENTO
	 * 
	 * @return El DOCUMENTO asociado a la clase
	 */
	@Column(name = "PERSO_DOCUMENTO")
	public Long getDocumento() {
		return documento;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo DOCUMENTO
	 * 
	 * @param DOCUMENTO El nuevo DOCUMENTO a modificar.
	 */
	public void setDocumento(Long documento) {
		this.documento = documento;
	}
	/**
	 * Metodo encargado de retornar el valor del atributo LA FECHA DE NACIMENTO
	 * 
	 * @return FECHA DE NACIMENTO asociado a la clase
	 */
	@Column(name = "PERSO_FECHANACIMIENTO")
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo FECHA DE NACIMENTO
	 * 
	 * @param FECHA DE NACIMENTO El nuevo FECHA DE NACIMENTO a modificar.
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	
	
	
}
