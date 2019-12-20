package com.hbt.semillero.dto;

import java.time.LocalDate;

import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personas;

/**
 * <b>Descripción:<b> Clase que determina el dto a usar para modificar,
 * consultar y posteriormente eliminar una compra
 * 
 * @author Eric Vairlla
 */
public class CompraDTO {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id Ãºnico que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long comic;
	private Long persona;
	private LocalDate fechaVenta;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getComic() {
		return comic;
	}
	public void setComic(Long comic) {
		this.comic = comic;
	}
	public Long getPersona() {
		return persona;
	}
	public void setPersona(Long persona) {
		this.persona = persona;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
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
