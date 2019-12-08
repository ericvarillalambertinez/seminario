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

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * las peronas
 * 
 * @author Eric Varilla
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestonarPersonajeLocal {

	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearComic(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		logger.debug("Aqui inicia el metodo CrearPersonaje");
		// Entidad nueva
		Personaje personaje = convertirPersonaDTOToPersonaje(personajeNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		entityManager.persist(personaje);
		logger.debug("Aqui finaliza el metodo CrearPersonaje");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#modificarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) {
		logger.debug("Aqui inicia el metodo ModificarPersonaje");

		logger.debug("Aqui finaliza el metodo ModificarPersonaje");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonaje(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonaje(Long idPersonaje) {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		logger.debug("Aqui finaliza el metodo EliminarPersonaje");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonaje() {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		String query = "SELECT personaje FROM PERSONAJE personaje";
		// List<PersonajeDTO> listarPersonajes = new ArrayList<PersonajeDTO>();
		List<Personaje> resultados = entityManager.createQuery(query).getResultList();
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();

		for (Personaje personaje : resultados) {
			resultadosPersonajeDTO.add(convertirPersonajeToPersonajecDTO(personaje));
		}
		logger.debug("Aqui finaliza el metodo EliminarPersonaje");

		return resultadosPersonajeDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes(Long idComic) {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		String query = "SELECT personaje FROM PERSONAJE personaje WHERE personaje.comic.id = :idComic ";
		// List<PersonajeDTO> listarPersonajes = new ArrayList<PersonajeDTO>();
		List<Personaje> resultados = entityManager.createQuery(query).setParameter("idComic", idComic).getResultList();
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();

		for (Personaje personaje : resultados) {
			resultadosPersonajeDTO.add(convertirPersonajeToPersonajecDTO(personaje));
		}
		logger.debug("Aqui finaliza el metodo EliminarPersonaje");

		return resultadosPersonajeDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Personaje convertirPersonaDTOToPersonaje(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setId(personajeDTO.getId());
		personaje.setComic(new Comic());
		personaje.getComic().setId((personajeDTO.getIdComic()));
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperPoder(personajeDTO.getSuperPoder());

		return personaje;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private PersonajeDTO convertirPersonajeToPersonajecDTO(Personaje personaje) {
		PersonajeDTO personajeDTO = new PersonajeDTO();
		personajeDTO.setIdComic(personaje.getComic().getId());
		personajeDTO.setEstado(personajeDTO.getEstado());
		personajeDTO.setSuperPoder(personajeDTO.getSuperPoder());
		personajeDTO.setNombre(personajeDTO.getNombre());
		personajeDTO.setId(personajeDTO.getId());
		return personajeDTO;
	}

}
