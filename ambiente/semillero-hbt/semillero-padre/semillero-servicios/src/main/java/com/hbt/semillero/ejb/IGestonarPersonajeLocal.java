package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.Exceptions.PersonajeExceptions;
import com.hbt.semillero.dto.PersonajeDTO;


/**
 * Expone los métodos del EJB GestionarPersonajeEJB Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author Eric Varilla
 *
 */
@Local
public interface IGestonarPersonajeLocal {
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author Eric Varilla
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeNuevo) throws PersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de consultar un personaje modificarlo y guardarlo
	 * 
	 * @author Eric Varilla
	 * 
	 * @param personajeModificar informacion nueva a modificar
	 */
	public PersonajeDTO modificarPersonaje(PersonajeDTO personajeDTO) throws PersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de eliminar un personaje modificarlo y guardarlo
	 * 
	 * @author Eric Varilla
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
	
	/**
	 * Metodo para listar los personajes por id del comic por indice del personaje
	 * @param index
	 * @param cadena
	 * */
	public  List<PersonajeDTO> consultarPersonaje(int index, String cadena);
	
	/**
	 * Metodo para listar los personajes por id del comic que entra como foraneo
	 * @param idComic
	 * */
	public List<PersonajeDTO>  consultarPersonajes(Long idComic) throws PersonajeExceptions;

	
}
