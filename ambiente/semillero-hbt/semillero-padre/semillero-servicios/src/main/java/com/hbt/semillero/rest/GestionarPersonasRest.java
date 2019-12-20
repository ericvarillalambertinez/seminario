package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.PersonasDTO;
import com.hbt.semillero.ejb.IGestionarPersonasLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * una Persona
 * 
 * @author Eric Varilla
 * @version
 */
@Path("/GestionarPersona")
public class GestionarPersonasRest {

	
	final static Logger logger = Logger.getLogger(GestionarRolPersonajeRest.class);
	/**
	 * Atriburo que permite gestionar una Persona
	 */
	@EJB
	private IGestionarPersonasLocal gestionarPersonaBean;
	
	
	/**
	 * Crea las personas en el sistema.
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/crearPersona
	 * 
	 * @param persona
	 * @return
	 */
	@POST
	@Path("/crearPersona")
	public Response crearPersona(PersonasDTO personaNueva) {

		try {
			gestionarPersonaBean.crearPersona(personaNueva);
			return Response.status(Response.Status.CREATED).entity("Persona Creada exitosamente..").build();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

	};
	
	/**
	 * 
	 * Metodo encargado de traer la informacion de todos las perosnas
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarRolPersonaje
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarRolPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarRolPersonaje() {
		List<PersonasDTO> persona = null;
		try {
			persona = gestionarPersonaBean.consultarPersonas();
			return Response.status(Response.Status.OK).entity(persona).build();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

	};
	
}
