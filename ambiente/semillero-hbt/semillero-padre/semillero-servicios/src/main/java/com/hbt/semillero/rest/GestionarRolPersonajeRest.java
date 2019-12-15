package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.ComicExceptions;
import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.ResultadoDTO;
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
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/crear
	 * 
	 * @param persona
	 * @return
	 */
	@POST
	@Path("/crear")
	public void crearRolPersonaje(RolPersonajeDTO rolNuevo) {
		try {
			gestionarRolPersonajeEJB.crearRolPersonaje(rolNuevo);
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
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
	public RolPersonajeDTO consultarRolPersonaje(@QueryParam("idRol") Long idRol) {
		RolPersonajeDTO rolpersonajeDTO = null;
		try {
			if (idRol != null) {

				rolpersonajeDTO = (RolPersonajeDTO) gestionarRolPersonajeEJB.consultarRolPersonaje(idRol.toString());
			}
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
		}

		return rolpersonajeDTO;
	};

	/**
	 * 
	 * Metodo encargado de traer la informacion de un rol determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/consultarRolPersonaje
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarRolPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolPersonajeDTO> consultarRolPersonaje() {
		try {
			return gestionarRolPersonajeEJB.consultarRolPersonaje();
		} catch (RolPersonajeExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
		}
		return null;
	};

	/**
	 * 
	 * Metodo encargado de modificar el nombre de un rol
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonaje/modificar?idRol=1&nombre=nuevonombre
	 * @param idComic identificador del comic a buscar
	 * @param nombre nombre nuevo del comic
	 */
	@PUT
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	public void modificarRolPersonaje(@QueryParam("idRol") Long idRol, @QueryParam("nombre") String nombre) {
		
		
		try {
			gestionarRolPersonajeEJB.modificarRolPersonaje(idRol, nombre,null);
		} catch (RolPersonajeExceptions e) {
		  logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
		}
			
		
		
	}

	/**
	 * 
	 * Metodo encargado de eliminar un rol dado el id
	 * 
	 * @param idRol identificador del rol
	 */
	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public void eliminarRolPersonaje(@QueryParam("idRol") Long idRol) {
		
		try {
		
			gestionarRolPersonajeEJB.eliminarRolPersonaje(idRol);
	
		} catch (RolPersonajeExceptions e) {
			// TODO: handle exception
			logger.error("Se ha capturado la excepcion y la informacion es: Codigo: "+e.getCodigo()+" mensaje: "+e.getMensaje());
		}
	}
}
