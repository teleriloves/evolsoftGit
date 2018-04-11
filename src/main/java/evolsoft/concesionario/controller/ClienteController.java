package evolsoft.concesionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping	
	public List<ClienteDTO> retrieveAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return clienteService.findAll(page, size);
	}

	@GetMapping("/{id}")
	public ClienteDTO findOne(@PathVariable("id") Integer id) throws NotFoundExcept {
		return clienteService.findById(id);
	}

	@PostMapping
	public ClienteDTO create(@RequestBody ClienteDTO clienteDTO) {
		return clienteService.create(clienteDTO);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody ClienteDTO clienteDTO) {
		clienteService.update(id, clienteDTO);
	}

	@DeleteMapping("/{id}")
	public void update(@PathVariable("id") Integer id) {
		clienteService.delete(id);
	}
	
	@PostMapping("/insertList")
	public void createList(@RequestBody List<ClienteDTO> listClienteDto) {
		clienteService.createList(listClienteDto);
	}

}
