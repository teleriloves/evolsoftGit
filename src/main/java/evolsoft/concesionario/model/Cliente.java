package evolsoft.concesionario.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;

@Entity
public class Cliente extends Actor {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Coche> coches = new ArrayList<>();

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

}
