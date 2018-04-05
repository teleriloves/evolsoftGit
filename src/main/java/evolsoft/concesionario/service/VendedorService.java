package evolsoft.concesionario.service;

import java.util.List;

import evolsoft.concesionario.dto.VendedorDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.model.Vendedor;

public interface VendedorService {

	VendedorDTO findById(Integer id) throws NotFoundExcept;

	List<VendedorDTO> findAll(Integer page, Integer size);

	VendedorDTO create(VendedorDTO vendedorDTO);

	void update(Integer id, VendedorDTO vendedorDTO);

	void delete(Integer idVendedor);

	public Vendedor map(VendedorDTO vendedor);

	public VendedorDTO map(Vendedor vendedor);

}
