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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evolsoft.concesionario.dto.CocheDTO;
import evolsoft.concesionario.dto.SoldCarDTO;
import evolsoft.concesionario.dto.CocheDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.service.CocheService;

@RestController
@RequestMapping(value = "api/coche")
public class CocheController {

	@Autowired
	private CocheService cocheService;
	
	@GetMapping
	public List<CocheDTO> retrieveAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return cocheService.findAll(page, size);
	}

	@GetMapping("/{id}")
	public CocheDTO findOne(@PathVariable("id") Integer id) throws NotFoundExcept {
		return cocheService.findById(id);
	}

	@PostMapping
	public CocheDTO create(@RequestBody CocheDTO cocheDTO) {
		return cocheService.create(cocheDTO);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody CocheDTO cocheDTO) {
		cocheService.update(id, cocheDTO);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		cocheService.delete(id);
	}

	@GetMapping("/sortedByPrice")
	public List<CocheDTO> listCochesSortedByPrice(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return cocheService.listCochesSortedByPrice(page, size);
	}
	
	@GetMapping("/sold")
	public List<CocheDTO> findCarsAlreadySold() {
		return cocheService.findCarsAlreadySold();
	}

	@PutMapping("/sellCar")
	public void sellCar(@RequestBody SoldCarDTO soldCarDTO) throws NotFoundExcept {
		cocheService.newSell(soldCarDTO.getIdCoche(), soldCarDTO.getIdCliente(), soldCarDTO.getIdVendedor());
	}

	@PostMapping("/insertList")
	public void createList(@RequestBody List<CocheDTO> listCocheDto) {
		cocheService.createList(listCocheDto);
	}
}
