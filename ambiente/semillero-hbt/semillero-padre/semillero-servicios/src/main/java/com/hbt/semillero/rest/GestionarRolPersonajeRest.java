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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearRolPersonaje(RolPersonajeDTO rolNuevo) {
		gestionarRolPersonajeEJB.crearRolPersonaje(rolNuevo);
		ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "ROlPersonaje creado exitosamente");
		return resultadoDTO;
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
		if (idRol != null) {
			RolPersonajeDTO rolpersonajeDTO = (RolPersonajeDTO) gestionarRolPersonajeEJB.consultarRolPersonaje(idRol.toString());
			return rolpersonajeDTO;
		}
		return null;
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
		return gestionarRolPersonajeEJB.consultarRolPersonaje();
	};

}
