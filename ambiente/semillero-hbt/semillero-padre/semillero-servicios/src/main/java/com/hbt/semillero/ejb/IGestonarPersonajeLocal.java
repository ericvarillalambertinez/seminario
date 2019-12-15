package com.hbt.semillero.ejb;

import javax.ejb.Local;

import java.util.List;

import com.hbt.semillero.Exceptions.ComicExceptions;
import com.hbt.semillero.Exceptions.PersonajeExceptions;
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;


/**
 * Expone los métodos del EJB GestionarComic Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author ccastano
 *
 */
@Local
public interface IGestonarPersonajeLocal {
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeNuevo) throws PersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de consultar un personaje modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeModificar informacion nueva a modificar
	 */
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) throws PersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de eliminar un personaje modificarlo y guardarlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeEliminar informacion a eliminar
	 */
	public void eliminarPersonaje(Long idPersonaje) throws PersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un personaje
	 * 
	 * @param idPersonaje identificador del personaje a ser consultado
	 * @return personaje resultado de la consulta
	 * @throws Exception si no se recibe idPersonaje
	 */
	public  List<PersonajeDTO> consultarPersonaje() throws PersonajeExceptions ;
	public  List<PersonajeDTO> consultarPersonaje(int index, String cadena);
	
	public List<PersonajeDTO>  consultarPersonajes(Long idComic) throws PersonajeExceptions;

	
}
