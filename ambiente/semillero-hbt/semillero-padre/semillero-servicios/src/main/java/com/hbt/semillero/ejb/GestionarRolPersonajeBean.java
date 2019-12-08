package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolPersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.RolPersonaje;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los RolPersonaje
 * 
 * @author Eric Varilla
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarRolPersonajeBean implements IGestionarRolPersonajeLocal {

	final static Logger logger = Logger.getLogger(GestionarRolPersonajeBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager EntityManager;

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#crearRolPersonaje(com.hbt.semillero.dto.RolPersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void crearRolPersonaje(RolPersonajeDTO rolNuevo) {
		logger.debug("Aqui inicia el metodo crearRolPersonaje");

		// Entidad nueva
		RolPersonaje rolpersonaje = convertirRolPersonajeDTOToRolPersonaje(rolNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		EntityManager.persist(rolpersonaje);
		logger.debug("Aqui finaliza el metodo crearRolPersonaje");
	}

	@Override
	public void modificarRolPersonaje(Long id, String nombre, RolPersonajeDTO rolNuevo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarRolPersonaje(Long idRol) {
		// TODO Auto-generated method stub

	}

	@Override
	public  RolPersonajeDTO consultarRolPersonaje(String idRol) {
		logger.debug("Se ejecuta el metodo consultar RolPersonajes");
		
		RolPersonaje rolpersonaje = null;
		rolpersonaje = new RolPersonaje();
		rolpersonaje = EntityManager.find(RolPersonaje.class, Long.parseLong(idRol));
		RolPersonajeDTO rolpersonajeDTO = convertirRolPersonajeToRolPersonajeDTO(rolpersonaje);
		logger.debug("Aqui finaliza el metodo EliminarPersonaje");
		return rolpersonajeDTO;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolPersonajeDTO> consultarRolPersonaje() {
		logger.debug("Se ejecuta el metodo consultar RolPersonajes");
	
		List<RolPersonajeDTO> resultadosrolpersonajeDTO = new ArrayList<RolPersonajeDTO>();
		List<RolPersonaje> resultados = EntityManager.createQuery("select r from RolPersonaje r").getResultList();
		for (RolPersonaje rolpersonaje:resultados) {
			resultadosrolpersonajeDTO.add(convertirRolPersonajeToRolPersonajeDTO(rolpersonaje));
		}
		logger.debug("Finaliza el metodo consultar RolPersonajes");
		return resultadosrolpersonajeDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private RolPersonajeDTO convertirRolPersonajeToRolPersonajeDTO(RolPersonaje rolpersonaje) {
		RolPersonajeDTO rolpersonajeDTO = new RolPersonajeDTO();
		rolpersonajeDTO.setId(rolpersonaje.getId());
		rolpersonajeDTO.setNombre(rolpersonaje.getNombre());
		rolpersonajeDTO.setEstado(rolpersonaje.getEstado());

		return rolpersonajeDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private RolPersonaje convertirRolPersonajeDTOToRolPersonaje(RolPersonajeDTO rolpersonajeDTO) {
		RolPersonaje rolpersonaje = new RolPersonaje();
		rolpersonaje.setId(rolpersonajeDTO.getId());
		rolpersonaje.setNombre(rolpersonajeDTO.getNombre());
		rolpersonaje.setEstado(rolpersonajeDTO.getEstado());

		return rolpersonaje;
	}

}
