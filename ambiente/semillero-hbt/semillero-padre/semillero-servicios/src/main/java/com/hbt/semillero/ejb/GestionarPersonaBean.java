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

import com.hbt.semillero.Exceptions.RolPersonajeExceptions;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.PersonasDTO;
import com.hbt.semillero.dto.RolPersonajeDTO;
import com.hbt.semillero.entidad.Personas;
import com.hbt.semillero.entidad.RolPersonaje;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * las personas
 * 
 * @author Eric Varilla
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonaBean implements IGestionarPersonasLocal{

	final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager EntityManager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void crearPersona(PersonasDTO personaNueva) throws RolPersonajeExceptions {
		logger.debug("Aqui inicia el metodo crearPersona");

		try {
			// Entidad nueva
						Personas personas = convertirPersonasDTOToPersonas(personaNueva);
						// Se almacena la informacion y se maneja la enidad persona
						EntityManager.persist(personas);
		} catch (Exception e) {
			logger.error("Ocurrio un error creando la persona" + personaNueva);
			throw new RolPersonajeExceptions("COD-0014", "Error al ejecutar el metodo crearPersona ", e);
		}
			

	
		logger.debug("Aqui finaliza el metodo crearRolPersonaje");
		
	}

	@Override
	public PersonasDTO modificarPersona(PersonasDTO personaNueva) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarPersona(Long idPersona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersonasDTO consultarPersona(Long idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonasDTO> consultarPersonas() throws RolPersonajeExceptions {
		logger.debug("Se ejecuta el metodo consultar consultarPersonas");

		List<PersonasDTO> resultadopersonaDTO = new ArrayList<>();
		try {

			List<Personas> resultados = EntityManager.createQuery("select p from Personas p").getResultList();
			for (Personas personas : resultados) {
				resultadopersonaDTO.add(convertirPersonaToPersonaDTO(personas));
			}
			logger.debug("Finaliza el metodo consultar consultarPersonas");
		} catch (Exception e) {
			logger.error("Error al consultar los las personas... " + e);
			throw new RolPersonajeExceptions("COD-0019", "Error al ejecutar el metodo consultarPersonas", e);
		}

		return resultadopersonaDTO;
	}
	
	
	/**
	 * 
	 * Metodo encargado de transformar un RolPerosnaje a un PersonasDTO
	 * 
	 * @param personas
	 * @return
	 */
	@SuppressWarnings("unused")
	private PersonasDTO convertirPersonaToPersonaDTO(Personas personas) {
		PersonasDTO personasDTO = new PersonasDTO();
		personasDTO.setId(personas.getId());
		personasDTO.settDocumento(personas.gettDocumento());
		personasDTO.setDocumento(personas.getDocumento());
		personasDTO.setFechaNacimiento(personas.getFechaNacimiento());

		return personasDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un PersonasDTO a un Personas
	 * 
	 * @param PersonasDTO
	 * @return
	 */
	@SuppressWarnings("unused")
	private Personas convertirPersonasDTOToPersonas(PersonasDTO personaNueva) {
		Personas personas = new Personas();
		personas.setId(personaNueva.getId());
		personas.settDocumento(personaNueva.gettDocumento());
		personas.setDocumento(personaNueva.getDocumento());
		personas.setFechaNacimiento(personaNueva.getFechaNacimiento());

		return personas;
	}
	
	

}
