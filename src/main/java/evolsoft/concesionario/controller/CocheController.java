package evolsoft.concesionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evolsoft.concesionario.dto.CocheDTO;
import evolsoft.concesionario.dto.SoldCarDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.service.CocheService;

@RestController
@RequestMapping(value = "api/coche")
public class CocheController {

	@Autowired
	private CocheService cocheService;

	@RequestMapping(method = RequestMethod.GET) //@GetMapping
	public List<CocheDTO> retrieveAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return cocheService.findAll(page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) //@GetMapping
	public CocheDTO findOne(@PathVariable("id") Integer id) throws NotFoundExcept {
		return cocheService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST) //@PostMapping
	public CocheDTO create(@RequestBody CocheDTO cocheDTO) {
		return cocheService.create(cocheDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) //@PutMapping
	public void update(@PathVariable("id") Integer id, @RequestBody CocheDTO cocheDTO) {
		cocheService.update(id, cocheDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) //@DeleteMapping
	public void delete(@PathVariable("id") Integer id) {
		cocheService.delete(id);
	}

	@RequestMapping(value = "/sortedByPrice", method = RequestMethod.GET) //@GetMapping
	public List<CocheDTO> listCochesSortedByPrice(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return cocheService.listCochesSortedByPrice(page, size);
	}

	@RequestMapping(value = "/sold", method = RequestMethod.GET) //@GetMapping
	public List<CocheDTO> findCarsAlreadySold() {
		return cocheService.findCarsAlreadySold();
	}

	@RequestMapping(value = "/sellCar", method = RequestMethod.PUT) //@PutMapping
	public void sellCar(@RequestBody SoldCarDTO soldCarDTO) throws NotFoundExcept {
		cocheService.newSell(soldCarDTO.getIdCoche(), soldCarDTO.getIdCliente(), soldCarDTO.getIdVendedor());
	}

}
