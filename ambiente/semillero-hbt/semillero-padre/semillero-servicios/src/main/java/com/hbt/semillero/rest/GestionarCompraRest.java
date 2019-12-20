package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.ejb.IGestionarCompraLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * una Compra
 * 
 * @author Eric Varilla
 * @version
 */
@Path("/GestionarCompra")
public class GestionarCompraRest {

	final static Logger logger = Logger.getLogger(GestionarCompraRest.class);

	/**
	 * Atriburo que permite gestionar una Compra
	 */
	@EJB
	private IGestionarCompraLocal gestionarCompraBean;

	/**
	 * 
	 * Metodo encargado de traer la informacion de las Compras
	 * http://localhost:8085/semillero-servicios/rest/GestionarCompra/consultarCompras
	 *
	 * @return
	 * @throws RolPersonajeExceptions 
	 */
	@GET
	@Path("/consultarCompras")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarCompras() throws RolPersonajeExceptions {

		try {

			return Response.status(Response.Status.OK).entity(gestionarCompraBean.consultarCompra()).build();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Ocurrio un error al listar los personajes.." + e).build();
		}

	};
	
	/**
	 * 
	 * Metodo encargado de crear un GestionarCompra y persistirlo
	 * http://localhost:8085/semillero-servicios/rest/GestionarCompra/crearCompra
	 * 
	 * @author Eric varilla
	 * @param GestionarCompra informacion nueva a crear
	 */
	@POST
	@Path("/crearCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearCompra(CompraDTO compraNuevo) {
		try {
			gestionarCompraBean.crearCompra(compraNuevo);
			return Response.status(Response.Status.CREATED).entity("Compra Creada").build();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Ocurrio un error al crear el personaje.." + e)
					.build();
		}
	};


	
	
}
