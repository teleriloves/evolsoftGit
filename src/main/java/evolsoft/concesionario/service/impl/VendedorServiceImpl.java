package evolsoft.concesionario.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evolsoft.concesionario.configuration.PageReqConfig;
import evolsoft.concesionario.dao.VendedorDAO;
import evolsoft.concesionario.dto.VendedorDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.model.Vendedor;
import evolsoft.concesionario.service.VendedorService;

@Service
public class VendedorServiceImpl implements VendedorService {

	@Autowired
	private VendedorDAO vendedorDAO;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public VendedorDTO findById(Integer id) throws NotFoundExcept {
		final Vendedor vendedor = Optional.ofNullable(vendedorDAO.findOne(id))
				.orElseThrow(() -> new NotFoundExcept("Vendedor con id " + id + " no encontrado"));
		return map(vendedor);
	}

	@Override
	public List<VendedorDTO> findAll(Integer page, Integer size) {
		final Iterable<Vendedor> allVendedores = vendedorDAO.findAll(PageReqConfig.newPageRequest(page, size));
		final List<VendedorDTO> vendedores = new ArrayList<>();
		allVendedores.forEach(vendedor -> {
			final VendedorDTO vendedorDTO = map(vendedor);
			vendedores.add(vendedorDTO);
		});
		return vendedores;
	}

	@Override
	public VendedorDTO create(VendedorDTO vendedorDTO) {
		final Vendedor vendedor = map(vendedorDTO);
		return map(vendedorDAO.save(vendedor));
	}

	@Override
	public void update(Integer id, VendedorDTO vendedorDTO) {
		final Vendedor vendedor = map(vendedorDTO);
		vendedor.setId(id);
		vendedorDAO.save(vendedor);
	}

	@Override
	public void delete(Integer idVendedor) {
		vendedorDAO.delete(idVendedor);
	}

	@Override
	public Vendedor map(VendedorDTO vendedorDTO) {
		return dozer.map(vendedorDTO, Vendedor.class);
	}

	@Override
	public VendedorDTO map(Vendedor vendedor) {
		return dozer.map(vendedor, VendedorDTO.class);
	}

}
