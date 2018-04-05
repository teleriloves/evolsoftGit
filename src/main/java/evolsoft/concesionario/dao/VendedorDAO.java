package evolsoft.concesionario.dao;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import evolsoft.concesionario.model.Vendedor;

public interface VendedorDAO extends PagingAndSortingRepository<Vendedor, Integer> {

	public Vendedor findByDni(@Param(value = "dni") String dni);

	public List<Vendedor> findByNombre(@Param(value = "nombre") String nombre);
}

