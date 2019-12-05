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
import com.hbt.semillero.entidad.Persona;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * las peronas
 * 
 * @author Eric Varilla
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonaBean implements IGestonarPersonajeLocal {

	final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearComic(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		logger.debug("Aqui inicia el metodo CrearPersonaje");
		// Entidad nueva
		Persona personaje = convertirPersonajeDTOToPersonaje(personajeNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		em.persist(personaje);
		logger.debug("Aqui finaliza el metodo CrearPersonaje");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#modificarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) {
		logger.debug("Aqui inicia el metodo ModificarPersonaje");
		Persona personajeModificar ;
		if(personajeNuevo==null) {
			// Entidad a modificar
			personajeModificar = em.find(Persona.class, id);
		}else {
			personajeModificar = convertirPersonajeDTOToPersonaje(personajeNuevo);
		}
		personajeModificar.setNombre(nombre);
		em.merge(personajeModificar);
		logger.debug("Aqui finaliza el metodo ModificarPersonaje");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonaje(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonaje(Long idPersonaje) {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");
		Persona personajeEliminar = em.find(Persona.class, idPersonaje);
		if (personajeEliminar != null) {
			em.remove(personajeEliminar);
		}
		logger.debug("Aqui finaliza el metodo EliminarPersonaje");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonaje(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PersonajeDTO consultarPersonaje(String idPersonaje) {
		logger.debug("Aqui inicia el metodo ConsultarPersonaje");
		Persona personaje = null;
		personaje = new Persona();
		personaje = em.find(Persona.class, Long.parseLong(idPersonaje));
		PersonajeDTO personajeDTO = convertirPersonajeToPersonajeDTO(personaje);
		
		logger.debug("Aqui finaliza el metodo ConsultarPersonaje");
		return personajeDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonajes()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes() {
		logger.debug("Aqui inicia el metodo ListarPersonaje");
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		List<Persona> resultados = em.createQuery("select p from Personaje p").getResultList();
		for (Persona personaje:resultados) {
			resultadosPersonajeDTO.add(convertirPersonajeToPersonajeDTO(personaje));
		}
		
		logger.debug("Aqui finaliza el metodo ListarPersonaje");
		return resultadosPersonajeDTO;
		
	}

	/**
	 * 
	 * Metodo encargado de transformar un personaje a un personajeDTO
	 * 
	 * @param comic
	 * @return
	 */
	private PersonajeDTO convertirPersonajeToPersonajeDTO(Persona personaje) {
		logger.debug("Aqui inicia el metodo convertirPersonajeToPersonajeDTO");
		PersonajeDTO personajeDTO = new PersonajeDTO();
		if(personaje.getId()!=null) {
		 personajeDTO.setId(personaje.getId().toString());
		}
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEditorial(personaje.getEditorial());
		personajeDTO.setTematicaEnum(personaje.getTematicaEnum());
		personajeDTO.setColeccion(personaje.getColeccion());
		personajeDTO.setNumeroPaginas(personaje.getNumeroPaginas());
		personajeDTO.setPrecio(personaje.getPrecio());
		personajeDTO.setAutores(personaje.getAutores());
		personajeDTO.setColor(personaje.getColor());
		personajeDTO.setFechaVenta(personaje.getFechaVenta());
		personajeDTO.setEstadoEnum(personaje.getEstadoEnum());
		personajeDTO.setCantidad(personaje.getCantidad());
		
		logger.debug("Aqui finaliza el metodo convertirPersonajeToPersonajeDTO");
		return personajeDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un personajeDTO a un personaje
	 * 
	 * @param personaje
	 * @return
	 */
	private Persona convertirPersonajeDTOToPersonaje(PersonajeDTO personajeDTO) {
		logger.debug("Aqui inicia el metodo convertirPersonajeDTOToPersonaje");
		Persona personaje = new Persona();
		if(personajeDTO.getId()!=null) {
			personaje.setId(Long.parseLong(personajeDTO.getId()));
		}
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEditorial(personajeDTO.getEditorial());
		personaje.setTematicaEnum(personajeDTO.getTematicaEnum());
		personaje.setColeccion(personajeDTO.getColeccion());
		personaje.setNumeroPaginas(personajeDTO.getNumeroPaginas());
		personaje.setPrecio(personajeDTO.getPrecio());
		personaje.setAutores(personajeDTO.getAutores());
		personaje.setColor(personajeDTO.getColor());
		personaje.setFechaVenta(personajeDTO.getFechaVenta());
		personaje.setEstadoEnum(personajeDTO.getEstadoEnum());
		personaje.setCantidad(personajeDTO.getCantidad());
		logger.debug("Aqui finaliza el metodo convertirPersonajeDTOToPersonaje");
		return personaje;
	}

}
