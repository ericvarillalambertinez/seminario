package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.PersonasDTO;

/**
 * Expone los métodos del EJB GestionarPersonas Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author eric varilla
 *
 */
@Local
public interface IGestionarPersonasLocal {

	
	/**
	 * 
	 * Metodo encargado de crear una persona y persistirlo
	 * 
	 * @author eric Varilla
	 * 
	 * @param personaNueva informacion nueva a crear
	 */
	public void crearPersona(PersonasDTO personaNueva)throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de consultar una persona modificarlo y guardarlo
	 * 
	 * @author eric Varilla
	 * 
	 * @param modificarPersona informacion nueva a modificar
	 */
	public PersonasDTO modificarPersona(PersonasDTO personaNueva) throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de eliminar un persona modificarlo y guardarlo
	 * 
	 * @author eric varilla
	 * 
	 * @param eliminarPersona informacion a eliminar
	 */
	public void eliminarPersona(Long idPersona)throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de retornar la informacion de una persona
	 * 
	 * @param idPersona identificador del Persona a ser consultado
	 * @return Persona Resultado de la consulta
	
	 */
	public PersonasDTO consultarPersona(Long idPersona)throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de retornar una lista de personas
	 * 
	 * @return
	 */
	public List<PersonasDTO> consultarPersonas()throws RolPersonajeExceptions;
	
	
	
}
