package evolsoft.concesionario.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import evolsoft.concesionario.configuration.PageReqConfig;
import evolsoft.concesionario.dao.CocheDAO;
import evolsoft.concesionario.dto.ClienteDTO;
import evolsoft.concesionario.dto.CocheDTO;
import evolsoft.concesionario.dto.VendedorDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.model.Coche;
import evolsoft.concesionario.service.ClienteService;
import evolsoft.concesionario.service.CocheService;
import evolsoft.concesionario.service.VendedorService;

@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheDAO cocheDAO;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VendedorService vendedorService;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public CocheDTO findById(Integer id) throws NotFoundExcept {
		final Coche coche = Optional.ofNullable(cocheDAO.findOne(id))
				.orElseThrow(() -> new NotFoundExcept("Coche con id " + id + " no encontrado"));
		return map(coche);
	}

	@Override
	public List<CocheDTO> findAll(Integer page, Integer size) {
		final Iterable<Coche> allCoches = cocheDAO.findAll(PageReqConfig.newPageRequest(page, size));
		final List<CocheDTO> coches = new ArrayList<>();
		allCoches.forEach(coche -> {
			final CocheDTO cocheDTO = map(coche);
			coches.add(cocheDTO);
		});
		return coches;
	}

	@Override
	public CocheDTO create(CocheDTO cocheDTO) {
		final Coche coche = map(cocheDTO);
		return map(cocheDAO.save(coche));
	}

	@Override
	public void update(Integer id, CocheDTO cocheDTO) {
		final Coche coche = map(cocheDTO);
		coche.setId(id);
		cocheDAO.save(coche);
	}

	@Override
	public void delete(Integer idCoche) {
		cocheDAO.delete(idCoche);
	}

	@Override
	public Coche map(CocheDTO cocheDTO) {
		Coche coche = dozer.map(cocheDTO, Coche.class);
		Optional.ofNullable(cocheDTO.getId()).ifPresent(optCocheDTO -> {
			coche.setCliente(cocheDAO.findOne(optCocheDTO).getCliente());
			coche.setVendedor(cocheDAO.findOne(optCocheDTO).getVendedor());
		});
		return coche;
	}

	@Override
	public CocheDTO map(Coche coche) {
		return dozer.map(coche, CocheDTO.class);
	}

	@Override
	public List<CocheDTO> listCochesSortedByPrice(Integer page, Integer size) {
		final Iterable<Coche> allCoches = cocheDAO.listCarsSortedByPrice();
		final List<CocheDTO> coches = new ArrayList<>();
		allCoches.forEach(coche -> {
			final CocheDTO cocheDTO = map(coche);
			coches.add(cocheDTO);
		});
		return coches;
	}

	@Override
	public List<CocheDTO> findCarsAlreadySold() {
		List<Coche> coches = cocheDAO.findCarsAlreadySold();
		final List<CocheDTO> cochesInStock = new ArrayList<>();
		coches.forEach(coche -> {
			final CocheDTO cocheDTO = map(coche);
			cochesInStock.add(cocheDTO);
		});
		return cochesInStock;
	}

	@Override
	public List<CocheDTO> findCochesInPriceRange(Integer minPrice, Integer maxPrice) {
		List<Coche> coches = cocheDAO.findCarsBtwPriceRange(minPrice, maxPrice);
		final List<CocheDTO> cochesInRange = new ArrayList<>();
		coches.forEach(coche -> {
			CocheDTO cocheDTO = map(coche);
			cochesInRange.add(cocheDTO);
		});
		return cochesInRange;
	}

	@Override
	public List<CocheDTO> findCochesInStock() {
		List<Coche> coches = cocheDAO.findCarsInStock();
		final List<CocheDTO> cochesInStock = new ArrayList<>();
		coches.forEach(coche -> {
			final CocheDTO cocheDTO = map(coche);
			cochesInStock.add(cocheDTO);
		});
		return cochesInStock;
	}

	@Override
	public void newSell(Integer idCoche, Integer idCliente, Integer idVendedor) throws NotFoundExcept {
		Coche soldCar = Optional.ofNullable(cocheDAO.findOne(idCoche)).orElseThrow(() -> new NotFoundExcept());
		soldCar.setFechaVenta(LocalDate.now().toString());
		addClienteToSoldCar(idCliente, soldCar);
		addVendedorToSoldCar(idVendedor, soldCar);
		cocheDAO.save(soldCar);
	}

	public void addClienteToSoldCar(Integer idCliente, Coche coche) throws NotFoundExcept {
		Optional<ClienteDTO> clienteCoche = Optional.ofNullable(clienteService.findById(idCliente));
		clienteCoche.ifPresent(cliente -> coche.setCliente(clienteService.map(cliente)));
	}

	public void addVendedorToSoldCar(Integer idVendedor, Coche coche) throws NotFoundExcept {
		Optional<VendedorDTO> vendedorCoche = Optional.ofNullable(vendedorService.findById(idVendedor));
		vendedorCoche.ifPresent(vendedor -> coche.setVendedor(vendedorService.map(vendedor)));
	}

}
