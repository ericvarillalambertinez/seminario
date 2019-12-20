package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.RolPersonajeDTO;
import com.hbt.semillero.ejb.IGestionarRolPersonajeLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un RolPersonaje
 * 
 * @author Eric Varilla
 * @version
 */
@Path("/GestionarRolPersonaje")
public class GestionarRolPersonajeRest {

	final static Logger logger = Logger.getLogger(GestionarRolPersonajeRest.class);
	/**
	 * Atriburo que permite gestionar un RolPersonaje
	 */
	@EJB
	private IGestionarRolPersonajeLocal gestionarRolPersonajeEJB;

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/crearRolPersonaje
	 * 
	 * @param persona
	 * @return
	 */
	@POST
	@Path("/crearRolPersonaje")
	public Response crearRolPersonaje(RolPersonajeDTO rolNuevo) {

		try {
			gestionarRolPersonajeEJB.crearRolPersonaje(rolNuevo);
			return Response.status(Response.Status.CREATED).entity("Rol Creado exitosamente..").build();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

	};

	/**
	 * 
	 * Metodo encargado de traer la informacion de un rol determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/consultarRolPersonajeId?idRol=1
	 * 
	 * @param idRol
	 * @return
	 */
	@GET
	@Path("/consultarRolPersonajeId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarRolPersonaje(@QueryParam("idRol") String idRol) {
		RolPersonajeDTO rol = null;
		try {
			
				rol = gestionarRolPersonajeEJB.consultarRolPersonaje(idRol);
			
					return Response.status(Response.Status.OK).entity(rol).build();
				
			
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

	};

	/**
	 * 
	 * Metodo encargado de traer la informacion de todos los rol
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/consultarRolPersonaje
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarRolPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarRolPersonaje() {
		List<RolPersonajeDTO> rol = null;
		try {
			rol = gestionarRolPersonajeEJB.consultarRolPersonaje();
			return Response.status(Response.Status.OK).entity(rol).build();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

	};

	/**
	 * 
	 * Metodo encargado de modificar un rol
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/modificarRolPersonaje
	 * 
	 * @param rolpersonajeDTO, que contendra toda la informacion del rol a modificar
	 */
	@POST
	@Path("/modificarRolPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificarRolPersonaje(RolPersonajeDTO rolpersonajeDTO) {

		try {
			gestionarRolPersonajeEJB.modificarRolPersonaje(rolpersonajeDTO);
			return Response.status(Response.Status.OK).entity("Rol Modificado exitosamente..").build();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

	}

	/**
	 * 
	 * Metodo encargado de eliminar un rol dado el id
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/eliminarRolPersonaje
	 * 
	 * @param idRol identificador del rol
	 */
	@POST
	@Path("/eliminarRolPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarRolPersonaje(@QueryParam("idRol") Long idRol) {

		try {
			gestionarRolPersonajeEJB.eliminarRolPersonaje(idRol);
			return Response.status(Response.Status.OK).entity("Rol eliminado..").build();

		} catch (RolPersonajeExceptions e) {
			logger.error("Se ha capturado la excepcion y la informacion es: Codigo: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}
	}
}
