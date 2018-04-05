package evolsoft.concesionario.service;

import java.util.List;

import evolsoft.concesionario.dto.ClienteDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.model.Cliente;

public interface ClienteService {

	ClienteDTO findById(Integer id) throws NotFoundExcept;

	List<ClienteDTO> findAll(Integer page, Integer size);

	ClienteDTO create(ClienteDTO clienteDTO);

	void update(Integer id, ClienteDTO clienteDTO);

	void delete(Integer idCliente);

	public Cliente map(ClienteDTO cliente);

	public ClienteDTO map(Cliente cliente);

}
