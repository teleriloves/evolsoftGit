package evolsoft.concesionario.dao;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import evolsoft.concesionario.model.Cliente;

public interface ClienteDAO extends PagingAndSortingRepository<Cliente, Integer> {

	public Cliente findByDni(@Param(value = "dni") String dni);

	public List<Cliente> findByNombre(@Param(value = "nombre") String nombre);

}
