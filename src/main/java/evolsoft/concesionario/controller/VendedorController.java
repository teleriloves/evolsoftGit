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

import evolsoft.concesionario.dto.VendedorDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.service.VendedorService;

@RestController
@RequestMapping(value = "api/vendedor")
public class VendedorController {

	@Autowired
	private VendedorService vendedorService;

	@GetMapping
	public List<VendedorDTO> retrieveAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return vendedorService.findAll(page, size);
	}

	@GetMapping("/{id}")
	public VendedorDTO findOne(@PathVariable("id") Integer id) throws NotFoundExcept {
		return vendedorService.findById(id);
	}

	@PostMapping
	public VendedorDTO create(@RequestBody VendedorDTO vendedorDTO) {
		return vendedorService.create(vendedorDTO);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody VendedorDTO vendedorDTO) {
		vendedorService.update(id, vendedorDTO);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		vendedorService.delete(id);
	}
	
	@PostMapping("/insertList")
	public void createList(@RequestBody List<VendedorDTO> listVendedorDto) {
		vendedorService.createList(listVendedorDto);
	}

}
