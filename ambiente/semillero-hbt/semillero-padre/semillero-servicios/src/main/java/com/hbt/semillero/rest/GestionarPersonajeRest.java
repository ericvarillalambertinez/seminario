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
import com.hbt.semillero.Exceptions.PersonajeExceptions;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestonarPersonajeLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un comic
 * 
 * @author ccastano
 * @version
 */
@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {

	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);
	@EJB
	private IGestonarPersonajeLocal gestionarPersonajeEJB;

	
	@GET
	@Path("/consultarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<PersonajeDTO> consultarPersonaje(){
		
		try {
			return gestionarPersonajeEJB.consultarPersonaje();
		} catch (PersonajeExceptions e) {
		  logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
		}
	return null;
};
	
	@GET
	@Path("/consultarPersonajePorParametro")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<PersonajeDTO> consultarPersonaje(@QueryParam("index") int index,@QueryParam("cadena") String cadena){
	
			return gestionarPersonajeEJB.consultarPersonaje(index,cadena);

	};
	
	@GET
	@Path("/consultarPersonajeId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO>  consultarPersonajes(@QueryParam("idComic") Long idComic){
		try {
			return gestionarPersonajeEJB.consultarPersonajes(idComic);
		} catch (PersonajeExceptions e) {
		  logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
		}
	return null;
	
		
	};
	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author ccastano
	 * 
	 * @param personajeNuevo informacion nueva a crear
	 */
	@POST
	@Path("/crearPersonaje")
		public void crearPersonaje(PersonajeDTO personajeNuevo) {
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeNuevo);
		} catch (PersonajeExceptions e) {
		  logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
		}

		
		
		
	};

	
	@PUT
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	public void modificarPersonaje(@QueryParam("idPersonaje") Long idPersonaje, @QueryParam("nombre") String nombre) {
		
		
		try {
			gestionarPersonajeEJB.modificarPersonaje(idPersonaje, nombre, null);
		} catch (PersonajeExceptions e) {
		  logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
		}
			
		
		
	}

	/**
	 * 
	 * Metodo encargado de eliminar un persoanje dado el id
	 * 
	 * @param idPersonaje identificador del persoanje
	 */
	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public void eliminarComic(@QueryParam("idPersonaje") Long idPersonaje) {
		
		try {
		
			gestionarPersonajeEJB.eliminarPersonaje(idPersonaje);
	
		} catch (PersonajeExceptions e) {
			// TODO: handle exception
			logger.error("Se ha capturado la excepcion y la informacion es: Codigo: "+e.getCodigo()+" mensaje: "+e.getMensaje());
		}
	}

	
}
