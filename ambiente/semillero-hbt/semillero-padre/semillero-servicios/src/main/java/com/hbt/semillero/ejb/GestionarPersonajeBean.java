package com.hbt.semillero.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.PersonajeExceptions;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * las personajes
 * 
 * @author Eric Varilla
 * @version
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class GestionarPersonajeBean implements IGestonarPersonajeLocal {

	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 * Metodo para crear un personaje
	 * 
	 * @throws PersonajeExceptions
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public void crearPersonaje(PersonajeDTO personajeNuevo) throws PersonajeExceptions {

		try {
			logger.debug("Aqui inicia el metodo CrearPersonaje");
			logger.info("Informacion del personaje: " + personajeNuevo);
			// Entidad nueva
			Personaje personaje = convertirPersonajeDTOToEntidad(personajeNuevo);
			// Se almacena la informacion y se maneja la enidad personaje
			entityManager.persist(personaje);
			logger.debug("Aqui finaliza el metodo CrearPersonaje");
		} catch (Exception e) {
			logger.error("Ocurrio un error creando el Personaje" + personajeNuevo);
			throw new PersonajeExceptions("COD-0008", "Error al ejecutar el metodo crearPersonaje ", e);
		}

	}

	/**
	 * Metodo para modificar un personaje
	 * 
	 * @throws PersonajeExceptions
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#modificarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@Override
	public PersonajeDTO modificarPersonaje(PersonajeDTO personajeDTO) throws PersonajeExceptions {

		try {
			logger.debug("Aqui inicia el metodo ModificarPersonaje");
			if (personajeDTO.getId() == null) {
				throw new PersonajeExceptions("COD-0009", "El identificado es requerido");
			}
			// Entidad a modificar

			Query query = entityManager.createQuery("UPDATE Personaje p " 
			+"SET p.nombre = :nombre ," 
			+ "p.estado = :estado " 
			+ "WHERE p.id = :id");
			query.setParameter("nombre", personajeDTO.getNombre());
			query.setParameter("estado", personajeDTO.getEstado());
			query.setParameter("id", personajeDTO.getId());
			query.executeUpdate();
			logger.debug("Aqui finaliza el metodo ModificarPersonaje");
			return convertirPersonajeTopersonajeDTO(entityManager.find(Personaje.class, personajeDTO.getId()));

		} catch (Exception e) {
			logger.error("Ocurrio un error modificadno el personaje" + personajeDTO);
			throw new PersonajeExceptions("COD-0009", "Error al ejecutar el metodo modificarComic ", e);
		}

	}

	/**
	 * Metodo para eliminar un personaje
	 * 
	 * @throws PersonajeExceptions
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonaje(java.lang.Long)
	 */
	@Override
	public void eliminarPersonaje(Long idPersonaje) throws PersonajeExceptions {
		logger.debug("Aqui inicia el metodo EliminarPersonaje");

		try {

			Query query = entityManager.createQuery("DELETE FROM Personaje p WHERE p.id = :idPersonaje")
					.setParameter("idPersonaje", idPersonaje);
			query.executeUpdate();

		} catch (Exception e) {

			logger.error("Error al eliminar el personaje... " + e);
			throw new PersonajeExceptions("COD-0010", "Error al ejecutar el metodo eliminar personaje", e);
		}

		logger.debug("Aqui finaliza el metodo EliminarPersonaje");
	}

	/**
	 * Metodo para consultar los personaje
	 * 
	 * @throws PersonajeExceptions
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonaje()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonaje() throws PersonajeExceptions {
		logger.debug("Aqui inicia el metodo consultarPersonaje");

		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		try {
			String query = "SELECT personaje FROM Personaje personaje";
			// List<PersonajeDTO> listarPersonajes = new ArrayList<PersonajeDTO>();
			List<Personaje> resultados = entityManager.createQuery(query).getResultList();

			for (Personaje personaje : resultados) {
				resultadosPersonajeDTO.add(convertirPersonajeTopersonajeDTO(personaje));
			}

		} catch (Exception e) {
			logger.error("Error al consultar los el personaje... " + e);
			throw new PersonajeExceptions("COD-0011", "Error al ejecutar el metodo consultarPersonaje", e);
		}

		logger.debug("Aqui finaliza el metodo consultarPersonaje");

		return resultadosPersonajeDTO;

	}

	/**
	 * Metodo para consultar los personaje por indice del personaje y por nombre
	 * 
	 * @throws PersonajeExceptions
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonaje()
	 * @param index
	 * @param cadena
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<PersonajeDTO> consultarPersonaje(int index, String cadena) {
		logger.debug("Aqui inicia el metodo consultarPersonaje");
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		try {

			String query = "SELECT personaje FROM Personaje personaje";
			// List<PersonajeDTO> listarPersonajes = new ArrayList<PersonajeDTO>();
			List<Personaje> resultados = entityManager.createQuery(query).getResultList();

			for (Personaje personaje : resultados) {
				resultadosPersonajeDTO.add(convertirPersonajeTopersonajeDTO(personaje));
			}

			PersonajeDTO personajeDTO = resultadosPersonajeDTO.get(index);
			Long valorCadena = Long.parseLong(cadena);
			BigDecimal valorUno = new BigDecimal("22");
			BigDecimal valorDos = BigDecimal.ZERO;
			BigDecimal total = valorUno.divide(valorDos);

		} catch (IndexOutOfBoundsException e) {
			logger.error("Indixe fuera del limite: ");
		} catch (NumberFormatException e) {
			logger.error("Error al convertir el numero ");
		} catch (ArithmeticException e) {
			logger.error("No se puedo realizar la division con los valores BigDeciaml");
		}
		logger.debug("Aqui finaliza el metodo consultarPersonaje");

		return resultadosPersonajeDTO;
	}

	/**
	 * Metodo para consultar los personaje por id
	 * 
	 * @throws PersonajeExceptions
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonaje(java.Long)
	 * @params idComic
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes(Long idComic) throws PersonajeExceptions {
		logger.debug("Aqui inicia el metodo consultarPersonajes");

		try {
			String query = "SELECT personaje FROM Personaje personaje WHERE personaje.comic.id = :idComic ";
			// List<PersonajeDTO> listarPersonajes = new ArrayList<PersonajeDTO>();
			List<Personaje> resultados = entityManager.createQuery(query).setParameter("idComic", idComic)
					.getResultList();
			List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<>();

			for (Personaje personaje : resultados) {
				resultadosPersonajeDTO.add(convertirPersonajeTopersonajeDTO(personaje));
			}
			logger.debug("Aqui finaliza el metodo consultarPersonajes");

			return resultadosPersonajeDTO;
		} catch (NumberFormatException e) {
			logger.error("Error convirtiendo la cadena a numero: " + idComic);
			throw new PersonajeExceptions("COD-0012", "no se pudo convertir la cedena", e);
		} catch (Exception e) {
			logger.error("Error al ejecutar el metodo consultarComic " + idComic);
			throw new PersonajeExceptions("COD-0013", "no se pudo ejecutar el metodo consultarComic ", e);
		}

	}

	/**
	 * 
	 * Metodo encargado de transformar un PersonajeDTO a un Personaje
	 * 
	 * @param personajeDTO
	 * @return
	 */
	private Personaje convertirPersonajeDTOToEntidad(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();

		personaje.setId(personajeDTO.getId());
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setComic(new Comic());
		personaje.getComic().setId((personajeDTO.getIdComic()));
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperPoder(personajeDTO.getSuperPoder());

		return personaje;
	}

	/**
	 * 
	 * Metodo encargado de transformar un Personaje a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private PersonajeDTO convertirPersonajeTopersonajeDTO(Personaje personaje) {
		PersonajeDTO personajeDTO = new PersonajeDTO();
		if (personaje.getId() != null) {
			personajeDTO.setId(personaje.getId());
		}
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEstado(personaje.getEstado());
		personajeDTO.setSuperPoder(personaje.getSuperPoder());
		personajeDTO.setIdComic(personaje.getComic().getId());

		return personajeDTO;
	}

}
