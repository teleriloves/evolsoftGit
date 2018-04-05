package evolsoft.concesionario.service;

import java.util.List;

import evolsoft.concesionario.dto.CocheDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.model.Coche;

public interface CocheService {

	CocheDTO findById(Integer id) throws NotFoundExcept;

	List<CocheDTO> findAll(Integer page, Integer size);

	CocheDTO create(CocheDTO cocheDTO);

	void update(Integer id, CocheDTO cocheDTO);

	void delete(Integer idCoche);

	public Coche map(CocheDTO coche);

	public CocheDTO map(Coche coche);

	public List<CocheDTO> listCochesSortedByPrice(Integer page, Integer size);

	public List<CocheDTO> findCarsAlreadySold();
	
}
