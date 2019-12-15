/**
 * GestionarComicBean.java
 */
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
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author ccastano
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @throws ComicExceptions
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComic(ComicDTO comicNuevo) throws ComicExceptions {

		try {
			// Entidad nueva
			Comic comic = convertirComicDTOToComic(comicNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			em.persist(comic);

		} catch (Exception e) {
			logger.error("Ocurrio un error creando el comic" + comicNuevo);
			throw new ComicExceptions("COD-0001", "Error al ejecutar el metodo crearComic ", e);
		}
	}

	/**
	 * 
	 * @throws ComicExceptions
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#modificarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, ComicDTO comicNuevo) throws ComicExceptions {

		Comic comicModificar;
		try {
			if (comicNuevo == null) {
				// Entidad a modificar
				// comicModificar = em.find(Comic.class, id);
				comicModificar = (Comic) em.createQuery("SELECT c FROM Comic c WHERE c.id = :id").setParameter("id", id)
						.getResultList();
				// query.executeUpdate();

			} else {
				comicModificar = convertirComicDTOToComic(comicNuevo);
			}
			comicModificar.setNombre(nombre);

			Query query = em.createQuery("UPDATE Comic c SET c.nombre=:comicModificar WHERE c.id=:id")
					.setParameter("id", id).setParameter("comicModificar", comicModificar.getNombre());
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Ocurrio un error modificadno el comic" + comicNuevo);
			throw new ComicExceptions("COD-0002", "Error al ejecutar el metodo modificarComic ", e);
		}
	}

	/**
	 * 
	 * @throws ComicExceptions
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long idComic) throws ComicExceptions {

		try {
			/*
			 * Comic comicEliminar = em.find(Comic.class, idComic); if (comicEliminar !=
			 * null) { em.remove(comicEliminar); }
			 */
			/*
			 * em.flush();// el em no trae con sigo el flush para seguir adelnate y pueda
			 * entrar en el try catch em.clear();
			 */
			Query query = em.createQuery("DELETE FROM Comic c WHERE c.id = :idComic").setParameter("idComic", idComic);
			query.executeUpdate();

		} catch (Exception e) {

			logger.error("Error al eliminar el comic... " + e);
			throw new ComicExceptions("COD-0003", "Error al ejecutar el metodo eliminar comic", e);
		}
	}

	/**
	 * 
	 * @throws ComicExceptions
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(String idComi) throws ComicExceptions {

		try {

			Comic comic = null;
			comic = new Comic();
			Long idComic = Long.parseLong(idComi);
			Query query = em.createQuery("SELECT c FROM Comic c WHERE c.id = :idComic").setParameter("idComic",
					idComic);
			comic = (Comic) query.getResultList();
			// comic = em.find(Comic.class, Long.parseLong(idComic));
			ComicDTO comicDTO = convertirComicToComicDTO(comic);
			return comicDTO;
		} catch (NumberFormatException e) {
			logger.error("Error convirtiendo la cadena a numero: " + idComi);
			throw new ComicExceptions("COD-0004", "no se pudo convertir la cedena", e);
		} catch (Exception e) {
			logger.error("Error al ejecutar el metodo consultarComic " + idComi);
			throw new ComicExceptions("COD-0005", "no se pudo ejecutar el metodo consultarComic ", e);
		}
	}

	/**
	 * 
	 * @throws ComicExceptions
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() throws ComicExceptions {

		logger.debug("Se ejecuta el metodo consultar comics");
		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		try {
			List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
			for (Comic comic : resultados) {
				// logger.debug(comic.getTematicaEnum());
				resultadosComicDTO.add(convertirComicToComicDTO(comic));
				// logger.debug(resultadosComicDTO);
			}

		} catch (Exception e) {
			logger.error("Error al consultar los el comic... " + e);
			throw new ComicExceptions("COD-0006", "Error al ejecutar el metodo consultarComics", e);
		}
		return resultadosComicDTO;
	}

	/*
	 * Metodo que muestra la informacion de comics y por logger muestra el precio
	 * total del comics aplicando el iva que para determinar esos datos se encuentra
	 * en la clase GestionarPresioBean
	 */
	@Override
	public List<ComicDTO> consultarComicsPrecio() throws ComicExceptions {
		// TODO Auto-generated method stub
		logger.debug("Se ejecuta el metodo consultar comics Precio");
		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		try {

			List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
			for (Comic comic : resultados) {

				GestionarPresioBean precio = new GestionarPresioBean();

				double precioTotal = precio.CalcularIva(comic.getTematicaEnum().toString(),
						comic.getPrecio().doubleValue());
				logger.debug("EL precio Del comic" + comic.getNombre() + " es: " + precioTotal);

				resultadosComicDTO.add(convertirComicToComicDTO(comic));

			}
		} catch (Exception e) {
			logger.error("Error al consultar los precios... " + e);
			throw new ComicExceptions("COD-0007", "Error al ejecutar el metodo consultarComicsPrecio", e);
		}
		return resultadosComicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		if (comic.getId() != null) {
			comicDTO.setId(comic.getId().toString());
		}
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		if (comicDTO.getId() != null) {
			comic.setId(Long.parseLong(comicDTO.getId()));
		}
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}

}
