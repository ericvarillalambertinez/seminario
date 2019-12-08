package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

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
	 * Metodo encargado de crear un comic y persistirlo
	 * 
	 * @author Eric Varilla
	 * 
	 * @param comicNuevo informacion nueva a crear
	 */
	public void crearRolPersonaje(RolPersonajeDTO rolNuevo);

	/**
	 * 
	 * Metodo encargado de consultar un comic modificarlo y guardarlo
	 * 
	 * @author Eric Varilla
	 * 
	 * @param comicModificar informacion nueva a modificar
	 */
	public void modificarRolPersonaje(Long id, String nombre, RolPersonajeDTO rolNuevo);

	/**
	 * 
	 * Metodo encargado de eliminar un comic modificarlo y guardarlo
	 * 
	 * @author Eric Varilla
	 * 
	 * @param comicEliminar informacion a eliminar
	 */
	public void eliminarRolPersonaje(Long idRol);

	/**
	 * 
	 * Metodo encargado de retornar la informacion de un comic
	 * 
	 * @param idComic identificador del comic a ser consultado
	 * @return comic Resultado de la consulta
	 * @throws Exception si no se recibe idComic
	 */
	public RolPersonajeDTO consultarRolPersonaje(String idRol);

	/**
	 * 
	 * Metodo encargado de retornar una lista de comics
	 * 
	 * @return
	 */
	public List<RolPersonajeDTO> consultarRolPersonaje();

}
