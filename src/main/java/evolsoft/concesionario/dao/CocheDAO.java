package evolsoft.concesionario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import evolsoft.concesionario.model.Coche;

public interface CocheDAO extends PagingAndSortingRepository<Coche, Integer>{
	
	public Coche findByNumBastidor(@Param(value ="numBastidor") Integer numBastidor);
	
	public Coche findByMatricula(@Param(value = "matricula") String matricula);
	
	public List<Coche> findByMarca(@Param(value = "marca") String marca);
	
	public List<Coche> findByModelo(@Param(value = "modelo") String modelo);
	
	public List<Coche> findByMarcaAndModelo(@Param(value = "marca") String marca, @Param(value = "modelo") String modelo);
	
	/*EJEMPLO DE EJERCICIO
	 * 
	 * AÑADIR LA BUSQUEDA POR RANGO DE PRECIO
	 * IMPORTANTE: añadir dependencias para solo tener que introducir el código
	 */
	
	@Query(value = "select c from Coche c where c.precio between :minPrice and :maxPrice")
	public List<Coche> findCarsBtwPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
	
	/*	EJEMPLO DE EJERCICIO
	 * 
	 *  BUSQUEDA DE COCHES EN STOCK
	 *  		En nuestro caso, los vehiculos en stock seran aquellos cuya fecha de venta sea nula
	 */
	
	@Query(value = "select c from Coche c where c.fechaVenta is null")
	public List<Coche> findCarsInStock();
	
	/*EJEMPLO DE EJERCICIO
	 * 
	 * BUSQUEDA ORDENADA POR PRECIO
	 * 			Intentar poner la ordenacion del metodo en la busqueda
	 */
	
	@Query(value = "select c from Coche c order by c.precio asc")
	public List<Coche> listCarsSortedByPrice();
	
	/*    EJEMPLO DE EJERCICIO
	 * 
	 * 		Encontrar todos los coches ya vendidos por el concesionario 
	 */
	
	@Query(value = "select c from Coche c where c.fechaVenta is not null")
	public List<Coche> findCarsAlreadySold();
	
	
}
