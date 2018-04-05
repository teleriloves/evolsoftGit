package evolsoft.concesionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evolsoft.concesionario.dto.VendedorDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.service.VendedorService;

@RestController
@RequestMapping(value = "api/vendedor")
public class VendedorController {

	@Autowired
	private VendedorService vendedorService;

	@RequestMapping(method = RequestMethod.GET)
	public List<VendedorDTO> retrieveAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return vendedorService.findAll(page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public VendedorDTO findOne(@PathVariable("id") Integer id) throws NotFoundExcept {
		return vendedorService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public VendedorDTO create(@RequestBody VendedorDTO vendedorDTO) {
		return vendedorService.create(vendedorDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @RequestBody VendedorDTO vendedorDTO) {
		vendedorService.update(id, vendedorDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void update(@PathVariable("id") Integer id) {
		vendedorService.delete(id);
	}

}
