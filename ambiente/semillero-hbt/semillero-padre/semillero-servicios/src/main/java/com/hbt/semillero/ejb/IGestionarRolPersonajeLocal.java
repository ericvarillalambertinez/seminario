package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.RolPersonajeDTO;

/**
 * Expone los métodos del EJB GestionarRolPersonaje Las interfaces determinan
 * una especie de contrato donde se define las firmas de los metodos, define que
 * se necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author Eric Varilla
 *
 */
@Local
public interface IGestionarRolPersonajeLocal {

	/**
	 * 
	 * Metodo encargado de crear un Rol y persistirlo
	 * 
	 * @author Eric Varilla
	 * 
	 * @param comicNuevo informacion nueva a crear
	 */
	public void crearRolPersonaje(RolPersonajeDTO rolNuevo) throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de consultar un Rol modificarlo y guardarlo
	 * 
	 * @author Eric Varilla
	 * 
	 * @param modificarRolPersonaje informacion nueva a modificar
	 */
	public RolPersonajeDTO modificarRolPersonaje(RolPersonajeDTO rolNuevo) throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de eliminar un Rol 
	 * 
	 * @author Eric Varilla
	 * 
	 * @param idRol informacion a eliminar
	 */
	public void eliminarRolPersonaje(Long idRol) throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un Rol
	 * 
	 * @param idRol identificador del Rol a ser consultado
	 * @return rol Resultado de la consulta
	 * @throws Exception si no se recibe idRol
	 */
	public RolPersonajeDTO consultarRolPersonaje(String idRol) throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de retornar una lista de Rol
	 * 
	 * @return
	 */
	public List<RolPersonajeDTO> consultarRolPersonaje() throws RolPersonajeExceptions;

}
