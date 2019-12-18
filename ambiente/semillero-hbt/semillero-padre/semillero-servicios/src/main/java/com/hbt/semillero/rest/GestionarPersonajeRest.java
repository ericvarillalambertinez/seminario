package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.PersonajeExceptions;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.ejb.IGestonarPersonajeLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un Personaje
 * 
 * @author Eric Varilla
 * @version
 */
@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {

	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);

	/**
	 * Atriburo que permite gestionar un Personaje
	 */
	@EJB
	private IGestonarPersonajeLocal gestionarPersonajeEJB;

	/**
	 * 
	 * Metodo encargado de traer la informacion de los personajes
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonaje
	 *
	 * @return
	 */

	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersonaje() {

		try {

			return Response.status(Response.Status.OK).entity(gestionarPersonajeEJB.consultarPersonaje()).build();
		} catch (PersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Ocurrio un error al listar los personajes.." + e).build();
		}

	};

	/**
	 * 
	 * Metodo encargado de traer la informacion de los personajes por parametros
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajePorParametro
	 * 
	 * @param index
	 * @param cadena
	 *
	 * @return
	 */
	@GET
	@Path("/consultarPersonajePorParametro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersonaje(@QueryParam("index") int index, @QueryParam("cadena") String cadena) {

		try {
			return Response.status(Response.Status.OK).entity(gestionarPersonajeEJB.consultarPersonaje(index, cadena))
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Ocurrio un error al listar los personajes.." + e).build();
		}

	};

	/**
	 * 
	 * Metodo encargado de traer la informacion del personaje por id del comic
	 * asociado
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajeId
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarPersonajeId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersonajes(@QueryParam("idComic") Long idComic) {
		List<PersonajeDTO> personaje= null;
		try {
			personaje = gestionarPersonajeEJB.consultarPersonajes(idComic);
			if(personaje.size()!=0) {
				return Response.status(Response.Status.OK).entity(personaje)
						.build();
			}else {
				return Response.status(Response.Status.OK).entity("el identificador del comic no existe")
						.build();
			}

		} catch (PersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Ocurrio un error al listar los personajes.." + e).build();
		}

	};

	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/crearPersonaje
	 * 
	 * @author Eric varilla
	 * @param personajeNuevo informacion nueva a crear
	 */
	@POST
	@Path("/crearPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearPersonaje(PersonajeDTO personajeNuevo) {
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeNuevo);
			return Response.status(Response.Status.CREATED).entity(personajeNuevo).build();
		} catch (PersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Ocurrio un error al crear el personaje.." + e)
					.build();
		}
	};

	/**
	 * 
	 * Metodo encargado de modificar un personaje
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/modificarPersonaje
	 * 
	 * @author Eric varilla
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	@POST
	@Path("/modificarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificarPersonaje(PersonajeDTO personajeDTO) {

		try {
			gestionarPersonajeEJB.modificarPersonaje(personajeDTO);
			return Response.status(Response.Status.OK).entity(personajeDTO).build();
		} catch (PersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Ocurrio un error al modificar el personaje.." + e).build();
		}

	}

	/**
	 * 
	 * Metodo encargado de eliminar un persoanje dado el id
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/eliminarPersonaje
	 * 
	 * @param idPersonaje identificador del personaje
	 */
	@POST
	@Path("/eliminarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPersonaje(@QueryParam("idPersonaje") Long idPersonaje) {

		try {
			gestionarPersonajeEJB.eliminarPersonaje(idPersonaje);
			return Response.status(Response.Status.OK).entity("Personaje eliminado").build();

		} catch (PersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Ocurrio un error al eliminar el personaje.." + e).build();
		}
	}

}
