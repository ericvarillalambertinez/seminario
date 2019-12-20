package com.hbt.semillero.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <b>Descripción:<b> Clase que determina el dto a usar para modificar,
 * consultar y posteriormente eliminar un personas
 * 
 * @author Eric Varilla
 */
public class PersonasDTO implements Serializable{
	
	
	/**
	 * Atributo que determina
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String tDocumento;
	private Long documento;
	private LocalDate fechaNacimiento;
	
	
	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
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
	 * Metodo encargado de retornar el valor del atributo tipo documento
	 * 
	 * @return El tipo documento asociado a la clase
	 */
	public String gettDocumento() {
		return tDocumento;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo tipo de documento
	 * 
	 * @param tipo de documento El nuevo tipo de documento a modificar.
	 */
	public void settDocumento(String tDocumento) {
		this.tDocumento = tDocumento;
	}
	/**
	 * Metodo encargado de retornar el valor del atributo documento
	 * 
	 * @return El  documento asociado a la clase
	 */
	public Long getDocumento() {
		return documento;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo  documento
	 * 
	 * @param documento El nuevo documento a modificar.
	 */
	public void setDocumento(Long documento) {
		this.documento = documento;
	}
	/**
	 * Metodo encargado de retornar el valor del atributo fecha nacimiento
	 * 
	 * @return la fecha nacimiento asociado a la clase
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo  fecha nacimiento
	 * 
	 * @param fecha nacimiento El nuevo fecha nacimiento a modificar.
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo
	 * RolPersonajeDTO. <b>Caso de Uso:</b>
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static RolPersonajeDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, RolPersonajeDTO.class);
	}

	/**
	 * Método encargado de convertir los datos recibidos en RolPersonajeDTO al JSON
	 * esperado
	 * 
	 * @param dto DTO
	 * 
	 * @return Json
	 */
	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
	}
}
