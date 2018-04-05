package evolsoft.concesionario.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evolsoft.concesionario.configuration.PageReqConfig;
import evolsoft.concesionario.dao.ClienteDAO;
import evolsoft.concesionario.dto.ClienteDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.model.Cliente;
import evolsoft.concesionario.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public ClienteDTO findById(Integer id) throws NotFoundExcept {
		final Cliente cliente = Optional.ofNullable(clienteDAO.findOne(id))
				.orElseThrow(() -> new NotFoundExcept("Cliente con id " + id + " no encontrado"));
		return map(cliente);
	}

	@Override
	public List<ClienteDTO> findAll(Integer page, Integer size) {
		final Iterable<Cliente> allClientes = clienteDAO.findAll(PageReqConfig.newPageRequest(page, size));
		final List<ClienteDTO> clientes = new ArrayList<>();
		allClientes.forEach(cliente -> {
			final ClienteDTO clienteDTO = map(cliente);
			clientes.add(clienteDTO);
		});
		return clientes;
	}

	@Override
	public ClienteDTO create(ClienteDTO clienteDTO) {
		final Cliente cliente = map(clienteDTO);
		return map(clienteDAO.save(cliente));
	}

	@Override
	public void update(Integer id, ClienteDTO clienteDTO) {
		final Cliente cliente = map(clienteDTO);
		cliente.setId(id);
		clienteDAO.save(cliente);
	}

	@Override
	public void delete(Integer idCliente) {
		clienteDAO.delete(idCliente);
	}

	@Override
	public Cliente map(ClienteDTO clienteDTO) {
		return dozer.map(clienteDTO, Cliente.class);
	}

	@Override
	public ClienteDTO map(Cliente cliente) {
		return dozer.map(cliente, ClienteDTO.class);
	}

}
