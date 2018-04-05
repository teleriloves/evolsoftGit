package evolsoft.concesionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evolsoft.concesionario.dto.ClienteDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.service.ClienteService;

@RestController
@RequestMapping(value = "api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET)
	public List<ClienteDTO> retrieveAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return clienteService.findAll(page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ClienteDTO findOne(@PathVariable("id") Integer id) throws NotFoundExcept {
		return clienteService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ClienteDTO create(@RequestBody ClienteDTO clienteDTO) {
		return clienteService.create(clienteDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @RequestBody ClienteDTO clienteDTO) {
		clienteService.update(id, clienteDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void update(@PathVariable("id") Integer id) {
		clienteService.delete(id);
	}

}
