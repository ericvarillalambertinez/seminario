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
import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Compra;
import com.hbt.semillero.entidad.Personas;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los compra
 * 
 * @author Eric Varilla
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraBean implements IGestionarCompraLocal {

	final static Logger logger = Logger.getLogger(GestionarRolPersonajeBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager EntityManager;

	/**
	 * Metodo para crear un ROl
	 * 
	 * @throws RolPersonajeExceptions
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#crearRolPersonaje(com.hbt.semillero.dto.RolPersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void crearCompra(CompraDTO compraNueva) throws RolPersonajeExceptions {
		logger.debug("Aqui inicia el metodo crearCompra");

		try {
			// Entidad nueva
			
			//Query query = EntityManager.createQuery("INSERT INTO Compra c SET c.comic = :idcomic, c.persona = :idPersona, c.fechaVenta = :fechaVenta I")
			Compra compra = convertirRolPersonajeDTOToRolPersonaje(compraNueva);
			// Se almacena la informacion y se maneja la enidad comic
			EntityManager.persist(compraNueva);

		} catch (Exception e) {
			logger.error("Ocurrio un error creando la compra" + compraNueva);
			throw new RolPersonajeExceptions("COD-0014", "Error al ejecutar el metodo crearCompra ", e);
		}

		logger.debug("Aqui finaliza el metodo crearCompra");
		
	}

	@Override
	public CompraDTO modificarCompra(CompraDTO compraNueva) throws RolPersonajeExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCompra(Long idCompra) throws RolPersonajeExceptions {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CompraDTO consultarCompra(Long idCompra) throws RolPersonajeExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompraDTO> consultarCompra() throws RolPersonajeExceptions {
		logger.debug("Aqui finaliza el metodo consultarCompra");
		List<CompraDTO> resultadoscompraDTO = new ArrayList<>();
		try {
			String query = "SELECT c FROM Compra c";
			// List<PersonajeDTO> listarPersonajes = new ArrayList<PersonajeDTO>();
			List<Compra> resultados = EntityManager.createQuery(query).getResultList();

			for (Compra compra : resultados) {
				resultadoscompraDTO.add(convertirCompraToCompraDTO(compra));
			}

		} catch (Exception e) {
			logger.error("Error al consultar las compras... " + e);
			throw new RolPersonajeExceptions("COD-0011", "Error al ejecutar el metodo consultarCompra", e);
		}

		logger.debug("Aqui finaliza el metodo consultarCompra");

		return resultadoscompraDTO;

	}
	
	
	
	
	/**
	 * 
	 * Metodo encargado de transformar un RolPerosnaje a un CompraDTO
	 * 
	 * @param Compra
	 * @return
	 */
	private CompraDTO convertirCompraToCompraDTO(Compra compra) {
		CompraDTO compraDTO = new CompraDTO();
		compraDTO.setId(compra.getId());
		compraDTO.setComic(compra.getComic().getId());
		compraDTO.setPersona(compra.getComic().getId());
		compraDTO.setFechaVenta(compra.getFechaVenta());
		return compraDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un RolPerosnajeDTO a un Compra
	 * 
	 * @param CompraDTO
	 * @return
	 */
	private Compra convertirRolPersonajeDTOToRolPersonaje(CompraDTO compraDTO) {
		Compra compra = new Compra();
		compra.setId(compraDTO.getId());
		compra.setComic(new Comic());
		compra.getComic().setId(compraDTO.getComic());
		compra.setPersona(new Personas());
		compra.getPersona().setId(compraDTO.getPersona());
		compra.setFechaVenta(compraDTO.getFechaVenta());
		return compra;
	}
	

}
