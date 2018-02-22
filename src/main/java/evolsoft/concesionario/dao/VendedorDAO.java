package evolsoft.concesionario.dao;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import evolsoft.concesionario.model.Actor;

public interface VendedorDAO extends PagingAndSortingRepository<Actor, Integer>{
	
	public Actor findByDni(@Param(value = "dni") String dni);
	
	public List<Actor> findByNombre(@Param(value = "nombre") String nombre);
	
}
