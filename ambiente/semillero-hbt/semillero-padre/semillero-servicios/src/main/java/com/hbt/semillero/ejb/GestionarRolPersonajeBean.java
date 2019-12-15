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
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.hbt.semillero.Exceptions.ComicExceptions;
import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
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
	 * @throws RolPersonajeExceptions 
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#crearRolPersonaje(com.hbt.semillero.dto.RolPersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void crearRolPersonaje(RolPersonajeDTO rolNuevo) throws RolPersonajeExceptions {
		logger.debug("Aqui inicia el metodo crearRolPersonaje");

		try {
			// Entidad nueva
			RolPersonaje rolpersonaje = convertirRolPersonajeDTOToRolPersonaje(rolNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			EntityManager.persist(rolpersonaje);

		} catch (Exception e) {
			logger.error("Ocurrio un error creando el comic" + rolNuevo);
			throw new RolPersonajeExceptions("COD-0014", "Error al ejecutar el metodo crearComic ", e);
		}
		
		
		
	
		logger.debug("Aqui finaliza el metodo crearRolPersonaje");
	}

	@Override
	public void modificarRolPersonaje(Long id, String nombre, RolPersonajeDTO rolNuevo) throws RolPersonajeExceptions {
		logger.debug("Se ejecuta el metodo modificarRolPersonaje");
		RolPersonaje rolModificar;
		try {
			if (rolNuevo == null) {
				// Entidad a modificar
				
				rolModificar = (RolPersonaje) EntityManager.createQuery("SELECT r FROM RolPersonaje r WHERE r.id = :id").setParameter("id", id)
						.getResultList();
				// query.executeUpdate();

			} else {
				rolModificar = convertirRolPersonajeDTOToRolPersonaje(rolNuevo);
			}
			rolModificar.setNombre(nombre);

			Query query = EntityManager.createQuery("UPDATE RolPersonaje r SET r.nombre=:rolModificar WHERE r.id=:id")
					.setParameter("id", id).setParameter("comicModificar", rolModificar.getNombre());
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Ocurrio un error modificadno el modificarRolPersonaje" + rolNuevo);
			throw new RolPersonajeExceptions("COD-0015", "Error al ejecutar el metodo modificarRolPersonaje ", e);
		}

		logger.debug("Finalizo el metodo modificarRolPersonaje");
	}

	@Override
	public void eliminarRolPersonaje(Long idRol) throws RolPersonajeExceptions {
		logger.debug("Se ejecuta el metodo eliminarRolPersonaje");
		try {
			
			
			Query query = EntityManager.createQuery("DELETE FROM RolPersonaje r WHERE r.id = :idRol").setParameter("idRol", idRol);
			query.executeUpdate();

		} catch (Exception e) {

			logger.error("Error al eliminar el Rol... " + e);
			throw new RolPersonajeExceptions("COD-0003", "Error al ejecutar el metodo eliminar Rol", e);
		}
		logger.debug("Finalizo el metodo eliminarRolPersonaje");

	}

	@Override
	public  RolPersonajeDTO consultarRolPersonaje(String idRol) throws RolPersonajeExceptions {
		logger.debug("Se ejecuta el metodo consultar RolPersonajes");
		
		try {
			RolPersonaje rolpersonaje = null;
			rolpersonaje = new RolPersonaje();
			rolpersonaje = EntityManager.find(RolPersonaje.class, Long.parseLong(idRol));
			RolPersonajeDTO rolpersonajeDTO = convertirRolPersonajeToRolPersonajeDTO(rolpersonaje);
			logger.debug("Aqui finaliza el metodo EliminarPersonaje");
			return rolpersonajeDTO;
		} catch (NumberFormatException e) {
			logger.error("Error convirtiendo la cadena a numero: " + idRol);
			throw new RolPersonajeExceptions("COD-0017", "no se pudo convertir la cedena", e);
		} catch (Exception e) {
			logger.error("Error al ejecutar el metodo consultarRolPersonaje " + idRol);
			throw new RolPersonajeExceptions("COD-0018", "no se pudo ejecutar el metodo consultarRolPersonaje ", e);
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolPersonajeDTO> consultarRolPersonaje() throws RolPersonajeExceptions {
		logger.debug("Se ejecuta el metodo consultar RolPersonajes");
	
		List<RolPersonajeDTO> resultadosrolpersonajeDTO = new ArrayList<RolPersonajeDTO>();
		try {

			List<RolPersonaje> resultados = EntityManager.createQuery("select r from RolPersonaje r").getResultList();
			for (RolPersonaje rolpersonaje:resultados) {
				resultadosrolpersonajeDTO.add(convertirRolPersonajeToRolPersonajeDTO(rolpersonaje));
			}
			logger.debug("Finaliza el metodo consultar RolPersonajes");
		} catch (Exception e) {
			logger.error("Error al consultar los el rol... " + e);
			throw new RolPersonajeExceptions("COD-0019", "Error al ejecutar el metodo consultarRolPersonaje", e);
		}
		
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
