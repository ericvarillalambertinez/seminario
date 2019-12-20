package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.CompraDTO;

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
public interface IGestionarCompraLocal {
	
	/**
	 * 
	 * Metodo encargado de crear una compra y persistirlo
	 * 
	 * @author eric Varilla
	 * 
	 * @param crearCompra informacion nueva a crear
	 */
	public void crearCompra(CompraDTO compraNueva)throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de consultar una compra modificarlo y guardarlo
	 * 
	 * @author eric Varilla
	 * 
	 * @param modificarCompra informacion nueva a modificar
	 */
	public CompraDTO modificarCompra(CompraDTO compraNueva) throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de eliminar una compra modificarlo y guardarlo
	 * 
	 * @author eric varilla
	 * 
	 * @param eliminarCompra informacion a eliminar
	 */
	public void eliminarCompra(Long idCompra)throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de retornar la informacion de una compra
	 * 
	 * @param idCompra identificador del Persona a ser consultado
	 * @return consultarCompra Resultado de la consulta
	
	 */
	public CompraDTO consultarCompra(Long idCompra)throws RolPersonajeExceptions;

	/**
	 * 
	 * Metodo encargado de retornar una lista de compra
	 * 
	 * @return
	 */
	public List<CompraDTO> consultarCompra()throws RolPersonajeExceptions;
	

}
