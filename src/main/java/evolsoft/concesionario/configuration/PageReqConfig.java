package evolsoft.concesionario.configuration;

import org.springframework.data.domain.PageRequest;

public class PageReqConfig {

	public static PageRequest newPageRequest(Integer page, Integer size) {
		int pageNumber = (page == null) ? 0 : page;
		int pageSize = (size == null) ? 10 : size;
		return new PageRequest(pageNumber, pageSize);
	}
}