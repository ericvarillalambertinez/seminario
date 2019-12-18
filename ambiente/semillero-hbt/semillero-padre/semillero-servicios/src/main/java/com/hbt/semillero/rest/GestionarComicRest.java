/**
 * GestionarComicRest.java
 */
package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.ComicExceptions;
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarComicLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un comic
 * 
 * @author ccastano
 * @version
 */
@Path("/GestionarComic")
public class GestionarComicRest {
	final static Logger logger = Logger.getLogger(GestionarComicRest.class);
	/**
	 * Atriburo que permite gestionar un comic
	 */
	@EJB
	private IGestionarComicLocal gestionarComicEJB;

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/saludo
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		return "Prueba inicial servicios rest en el semillero java hbt";
	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de los comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics
	 *
	 * @return
	 */
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarComic() {
		List<ComicDTO> comicDTO = null;
		try {
			comicDTO = gestionarComicEJB.consultarComics();
			//ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Listado todos los Comic exitosamente");
			return Response.status(Response.Status.CREATED).entity(comicDTO).build();	
		} catch (ComicExceptions e) {
			  logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Ocurrio un error al Listar los comic.." + e)
					.build();
		}

	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de los precios de un comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComicsPrecio
	 *
	 * @return
	 */
	@GET
	@Path("/consultarComicsPrecio")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarComicsPrecio() {
		List<ComicDTO> comicDTO = null;
		try {
			comicDTO = gestionarComicEJB.consultarComicsPrecio();
			//ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Listado todos los Comic exitosamente");
			return Response.status(Response.Status.CREATED).entity(comicDTO).build();	
			
		} catch (ComicExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Ocurrio un error al Listar los precios de los comic.." + e)
					.build();
		}

	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComic?idComic=1
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarComic(@QueryParam("idComic") String idComic) {

		ComicDTO comicDTO = null;
		try {
			if (idComic != null) {
				comicDTO = gestionarComicEJB.consultarComic(idComic);
			}
			return Response.status(Response.Status.OK).entity(comicDTO).build();
		} catch (ComicExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Ocurrio un error al crear el comic.." + e)
					.build();
		}

	}

	/**
	 * Crea los comic en el sistema
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/crearComic
	 * 
	 * @param comic
	 * @return
	 */
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearComic(ComicDTO comicDTO) {
		try {
			gestionarComicEJB.crearComic(comicDTO);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic creado exitosamente...");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();
		} catch (ComicExceptions e) {
			  logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Ocurrio un error al crear el comic.." + e)
					.build();
		}

	}

	/**
	 * 
	 * Metodo encargado de modificar el nombre de un comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/modificarComic?idComic=1&nombre=nuevonombre
	 * 
	 * @param idComic identificador del comic a buscar
	 * @param nombre  nombre nuevo del comic
	 */
	@POST
	@Path("/modificarComic")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificarComic(@QueryParam("nombre") String nombre, @QueryParam("id") Long id) {

		try {
			gestionarComicEJB.modificarComic(nombre, id, null);
			return Response.status(Response.Status.OK).entity("Comic modificado.....").build();
		} catch (ComicExceptions e) {
			logger.error("Se capturo la excepcion y la informacion del codigo es: "+e.getCodigo()+" mensaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

	}

	/**
	 * 
	 * Metodo encargado de eliminar un comic dado el id
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/eliminarComic?idComic=1
	 * @param idComic identificador del comic
	 */
	@POST
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarComic(@QueryParam("idComic") Long idComic) {

		try {
			gestionarComicEJB.eliminarComic(idComic);

			return Response.status(Response.Status.OK).entity("Comic Eliminado").build();

		} catch (ComicExceptions e) {
			logger.error("Se ha capturado la excepcion y la informacion es: Codigo: " + e.getCodigo() + " mensaje: "
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST).entity("Ocurrio un error al Eliminar el comic.." + e)
					.build();
		}
	}

}
