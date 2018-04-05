package evolsoft.concesionario;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import evolsoft.concesionario.dto.ClienteDTO;
import evolsoft.concesionario.dto.CocheDTO;
import evolsoft.concesionario.dto.VendedorDTO;
import evolsoft.concesionario.exception.NotFoundExcept;
import evolsoft.concesionario.service.ClienteService;
import evolsoft.concesionario.service.CocheService;
import evolsoft.concesionario.service.VendedorService;

@SpringUI
public class VaadinUI extends UI {

	private static final long serialVersionUID = 1L;

	@Autowired
	CocheService cocheService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	VendedorService vendedorService;

	/*
	 * NOTIFICATIONS
	 */
	Notification errorNotification = new Notification("Warning", "Invalid data in some fields",
			Notification.Type.WARNING_MESSAGE);

	/*
	 * GRIDS
	 */
	Grid<CocheDTO> gridCoche = new Grid<>();
	Grid<ClienteDTO> gridCliente = new Grid<>();
	Grid<VendedorDTO> gridVendedor = new Grid<>();

	/*
	 * LAYOUTS
	 */

	// Coche
	VerticalLayout cocheElimination = new VerticalLayout();
	VerticalLayout cocheAddition = new VerticalLayout();
	VerticalLayout cocheEdition = new VerticalLayout();
	HorizontalLayout cocheModification = new HorizontalLayout();
	VerticalLayout cocheContent = new VerticalLayout();
	HorizontalLayout cocheSellOut = new HorizontalLayout();

	// Cliente
	VerticalLayout clienteElimination = new VerticalLayout();
	VerticalLayout clienteAddition = new VerticalLayout();
	VerticalLayout clienteEdition = new VerticalLayout();
	HorizontalLayout clienteModification = new HorizontalLayout();
	VerticalLayout clienteContent = new VerticalLayout();

	// Vendedor
	VerticalLayout vendedorElimination = new VerticalLayout();
	VerticalLayout vendedorAddition = new VerticalLayout();
	VerticalLayout vendedorEdition = new VerticalLayout();
	HorizontalLayout vendedorModification = new HorizontalLayout();
	VerticalLayout vendedorContent = new VerticalLayout();

	// Data
	HorizontalLayout dataContent = new HorizontalLayout();

	// Web
	VerticalLayout webContent = new VerticalLayout();

	/*
	 * TEXTFIELDS
	 */

	// COCHE
	private TextField cocheAddMatriculaTF = new TextField("Matricula");
	private TextField cocheAddMarcaTF = new TextField("Marca");
	private TextField cocheAddModeloTF = new TextField("Modelo");
	private TextField cocheAddMotorTF = new TextField("Motor");
	private TextField cocheAddBastidorTF = new TextField("N. Bastidor");
	private TextField cocheAddPrecioTF = new TextField("Precio");
	private TextField cocheAddFechaVentaTF = new TextField("F. venta");

	private TextField cocheEditIdTF = new TextField("ID Coche");
	private TextField cocheEditMatriculaTF = new TextField("Matricula");
	private TextField cocheEditMarcaTF = new TextField("Marca");
	private TextField cocheEditModeloTF = new TextField("Modelo");
	private TextField cocheEditMotorTF = new TextField("Motor");
	private TextField cocheEditBastidorTF = new TextField("N. Bastidor");
	private TextField cocheEditPrecioTF = new TextField("Precio");
	private TextField cocheEditFechaVentaTF = new TextField("F. venta");

	private TextField cocheDeleteIdTF = new TextField("ID coche");

	private TextField cocheSellOutIdCocheTF = new TextField("ID coche");
	private TextField cocheSellOutIdClienteTF = new TextField("ID coche");
	private TextField cocheSellOutIdVendedorTF = new TextField("ID coche");

	// CLIENTE
	private TextField clienteAddDniTF = new TextField("Dni");
	private TextField clienteAddNombreTF = new TextField("Nombre");
	private TextField clienteAddTflTF = new TextField("Telefono");
	private TextField clienteAddEmailTF = new TextField("Email");

	private TextField clienteEditIdTF = new TextField("ID cliente");
	private TextField clienteEditDniTF = new TextField("Dni");
	private TextField clienteEditNombreTF = new TextField("Nombre");
	private TextField clienteEditTlfTF = new TextField("Telefono");
	private TextField clienteEditEmailTF = new TextField("Email");

	private TextField clienteDeleteIdTF = new TextField("ID cliente");

	// VENDEDOR
	private TextField vendedorAddDniTF = new TextField("Dni");
	private TextField vendedorAddNombreTF = new TextField("Nombre");
	private TextField vendedorAddTflTF = new TextField("Telefono");
	private TextField vendedorAddEmailTF = new TextField("Email");

	private TextField vendedorEditIdTF = new TextField("ID vendedor");
	private TextField vendedorEditDniTF = new TextField("Dni");
	private TextField vendedorEditNombreTF = new TextField("Nombre");
	private TextField vendedorEditTlfTF = new TextField("Telefono");
	private TextField vendedorEditEmailTF = new TextField("Email");

	private TextField vendedorDeleteIdTF = new TextField("ID vendedor");

	/*
	 * BUTTONS
	 */

	// COCHE
	Button cocheAddButton = new Button("Añadir Coche", event -> {
		try {
			addCoche(event);
		} catch (NotFoundExcept e) {
			errorNotification.show(Page.getCurrent());
		}
	});

	Button cocheEditButton = new Button("Editar Coche", event -> {
		try {
			editCoche(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

	Button cocheDeleteButton = new Button("Eliminar Coche", event -> {
		try {
			deleteCoche(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

//	Button cocheSellButton = new Button("Marcar como vendidos", event -> {
//		try {
//			sellCar(event);
//		} catch (NotFoundExcept e) {
//			Notification.show(e.getMessage());
//		}
//	});

	// CLIENTE

	Button clienteAddButton = new Button("Añadir Cliente", event -> {
		try {
			addCliente(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

	Button clienteEditButton = new Button("Editar Cliente", event -> {
		try {
			editCliente(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

	Button clienteDeleteButton = new Button("Eliminar Cliente", event -> {
		try {
			deleteCliente(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

	// VENDEDOR

	Button vendedorAddButton = new Button("Añadir Vendedor", event -> {
		try {
			addVendedor(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

	Button vendedorEditButton = new Button("Editar Vendedor", event -> {
		try {
			editVendedor(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

	Button vendedorDeleteButton = new Button("Eliminar Vendedor", event -> {
		try {
			deleteVendedor(event);
		} catch (NotFoundExcept e) {
			Notification.show(e.getMessage());
		}
	});

	/*
	 * Init Method
	 */
	@Override
	protected void init(VaadinRequest request) {
		buttonsFormat();
		setDataGrids();
		setWebContent();
		Page.getCurrent().setTitle("Concesionario");
		setContent(webContent);
	}

	/*
	 * GRID SETTINGS
	 */
	private void setGridCoche() {
		gridCoche.addColumn(cocheDTO -> cocheDTO.getId()).setCaption("ID");
		gridCoche.addColumn(cocheDTO -> cocheDTO.getMatricula()).setCaption("Matricula");
		gridCoche.addColumn(cocheDTO -> cocheDTO.getMarca()).setCaption("Marca");
		gridCoche.addColumn(cocheDTO -> cocheDTO.getModelo()).setCaption("Modelo");
		gridCoche.addColumn(cocheDTO -> cocheDTO.getMotor()).setCaption("Motorización");
		gridCoche.addColumn(cocheDTO -> cocheDTO.getNumBastidor()).setCaption("Num. Bastidor");
		gridCoche.addColumn(cocheDTO -> cocheDTO.getPrecio()).setCaption("Marca");
		gridCoche.addColumn(cocheDTO -> cocheDTO.getFechaVenta()).setCaption("Marca");
		listCoches();
	}

	private void setGridCliente() {

		gridCliente.addColumn(clienteDTO -> clienteDTO.getId()).setCaption("ID");
		gridCliente.addColumn(clienteDTO -> clienteDTO.getDni()).setCaption("DNI");
		gridCliente.addColumn(clienteDTO -> clienteDTO.getNombre()).setCaption("Nombre");
		gridCliente.addColumn(clienteDTO -> clienteDTO.getEmail()).setCaption("Email");
		gridCliente.addColumn(clienteDTO -> clienteDTO.getTlf()).setCaption("Telefono");

		listClientes();
	}

	private void setGridVendedor() {

		gridVendedor.addColumn(vendedorDTO -> vendedorDTO.getId()).setCaption("ID");
		gridVendedor.addColumn(vendedorDTO -> vendedorDTO.getDni()).setCaption("DNI");
		gridVendedor.addColumn(vendedorDTO -> vendedorDTO.getNombre()).setCaption("Nombre");
		gridVendedor.addColumn(vendedorDTO -> vendedorDTO.getEmail()).setCaption("Email");
		gridVendedor.addColumn(vendedorDTO -> vendedorDTO.getTlf()).setCaption("Telefono");
		listVendedores();
	}

	private void setDataGrids() {
		setGridCoche();
		setGridCliente();
		setGridVendedor();
	}

	/*
	 * BUTTONS FORMAT
	 */
	public void buttonsFormat() {
		cocheAddButton.setIcon(VaadinIcons.CHECK_CIRCLE);
		cocheDeleteButton.setIcon(VaadinIcons.CLOSE_CIRCLE);
		cocheEditButton.setIcon(VaadinIcons.RETWEET);
		clienteAddButton.setIcon(VaadinIcons.CHECK_CIRCLE);
		clienteDeleteButton.setIcon(VaadinIcons.CLOSE_CIRCLE);
		clienteEditButton.setIcon(VaadinIcons.RETWEET);
		vendedorAddButton.setIcon(VaadinIcons.CHECK_CIRCLE);
		vendedorDeleteButton.setIcon(VaadinIcons.CLOSE_CIRCLE);
		vendedorEditButton.setIcon(VaadinIcons.RETWEET);
	}

	/*
	 * DATA SETTINGS
	 */

//	private void setCocheSellOut() {
//		cocheSellOut.addComponent(new Label("Venta de coche"));
//		cocheSellOut.addComponent(cocheSellOutIdCocheTF);
//		cocheSellOut.addComponent(cocheSellOutIdClienteTF);
//		cocheSellOut.addComponent(cocheSellOutIdVendedorTF);
//		cocheSellOut.addComponent(cocheSellButton);
//	}

	private void setCocheElimination() {
		cocheElimination.addComponent(new Label("Eliminar Coche"));
		cocheElimination.addComponent(cocheDeleteIdTF);
		cocheElimination.addComponent(cocheDeleteButton);
	}

	private void setCocheAddition() {
		cocheAddition.addComponent(new Label("Añadir coche"));
		cocheAddition.addComponent(new Label(""));
		cocheAddition.addComponent(new Label(""));
		cocheAddition.addComponent(cocheAddMatriculaTF);
		cocheAddition.addComponent(cocheAddMarcaTF);
		cocheAddition.addComponent(cocheAddModeloTF);
		cocheAddition.addComponent(cocheAddMotorTF);
		cocheAddition.addComponent(cocheAddBastidorTF);
		cocheAddition.addComponent(cocheAddPrecioTF);
		cocheAddition.addComponent(cocheAddFechaVentaTF);
		cocheAddition.addComponent(cocheAddButton);
	}

	private void setCocheEdition() {
		cocheEdition.addComponent(new Label("Editar coche"));
		cocheEdition.addComponent(cocheEditIdTF);
		cocheEdition.addComponent(cocheEditMatriculaTF);
		cocheEdition.addComponent(cocheEditMarcaTF);
		cocheEdition.addComponent(cocheEditModeloTF);
		cocheEdition.addComponent(cocheEditMotorTF);
		cocheEdition.addComponent(cocheEditBastidorTF);
		cocheEdition.addComponent(cocheEditPrecioTF);
		cocheEdition.addComponent(cocheEditFechaVentaTF);
		cocheEdition.addComponent(cocheEditButton);
	}

	private void setCocheModification() {
		setCocheAddition();
		setCocheEdition();
		cocheModification.addComponent(cocheAddition);
		cocheModification.addComponent(cocheEdition);
	}

	private void setCocheContent() {
		setCocheModification();
		setCocheElimination();
//		setCocheSellOut();
		cocheContent.addComponent(gridCoche);
		cocheContent.addComponent(cocheModification);
		cocheContent.addComponent(cocheElimination);
		cocheContent.addComponent(cocheSellOut);
	}

	private void setClienteElimination() {
		clienteElimination.addComponent(new Label("Eliminar cliente"));
		clienteElimination.addComponent(clienteDeleteIdTF);
		clienteElimination.addComponent(clienteDeleteButton);

	}

	private void setClienteAddition() {
		clienteAddition.addComponent(new Label("Añadir cliente"));
		clienteAddition.addComponent(new Label(""));
		clienteAddition.addComponent(new Label(""));
		clienteAddition.addComponent(clienteAddDniTF);
		clienteAddition.addComponent(clienteAddNombreTF);
		clienteAddition.addComponent(clienteAddTflTF);
		clienteAddition.addComponent(clienteAddEmailTF);
		clienteAddition.addComponent(clienteAddButton);
	}

	private void setClienteEdition() {
		clienteEdition.addComponent(new Label("Editar cliente"));
		clienteEdition.addComponent(clienteEditIdTF);
		clienteEdition.addComponent(clienteEditDniTF);
		clienteEdition.addComponent(clienteEditNombreTF);
		clienteEdition.addComponent(clienteEditTlfTF);
		clienteEdition.addComponent(clienteEditEmailTF);
		clienteEdition.addComponent(clienteEditButton);
	}

	private void setClienteModification() {
		setClienteAddition();
		setClienteEdition();
		clienteModification.addComponent(clienteAddition);
		clienteModification.addComponent(clienteEdition);

	}

	private void setClienteContent() {
		setClienteModification();
		setClienteElimination();
		clienteContent.addComponent(gridCliente);
		clienteContent.addComponent(clienteModification);
		clienteContent.addComponent(clienteElimination);
	}

	private void setVendedorElimination() {
		vendedorElimination.addComponent(new Label("Eliminar vendedor"));
		vendedorElimination.addComponent(vendedorDeleteIdTF);
		vendedorElimination.addComponent(vendedorDeleteButton);

	}

	private void setVendedorAddition() {
		vendedorAddition.addComponent(new Label("Añadir vendedor"));
		vendedorAddition.addComponent(new Label(""));
		vendedorAddition.addComponent(new Label(""));
		vendedorAddition.addComponent(vendedorAddDniTF);
		vendedorAddition.addComponent(vendedorAddNombreTF);
		vendedorAddition.addComponent(vendedorAddTflTF);
		vendedorAddition.addComponent(vendedorAddEmailTF);
		vendedorAddition.addComponent(vendedorAddButton);
	}

	private void setVendedorEdition() {
		vendedorEdition.addComponent(new Label("Editar vendedor"));
		vendedorEdition.addComponent(vendedorEditIdTF);
		vendedorEdition.addComponent(vendedorEditDniTF);
		vendedorEdition.addComponent(vendedorEditNombreTF);
		vendedorEdition.addComponent(vendedorEditTlfTF);
		vendedorEdition.addComponent(vendedorEditEmailTF);
		vendedorEdition.addComponent(vendedorEditButton);
	}

	private void setVendedorModification() {
		setVendedorAddition();
		setVendedorEdition();
		vendedorModification.addComponent(vendedorAddition);
		vendedorModification.addComponent(vendedorEdition);
	}

	private void setVendedorContent() {
		setVendedorModification();
		setVendedorElimination();
		vendedorContent.addComponent(gridVendedor);
		vendedorContent.addComponent(vendedorModification);
		vendedorContent.addComponent(vendedorElimination);
	}

	private void setDataContent() {
		setCocheContent();
		setClienteContent();
		setVendedorContent();
		dataContent.addComponent(cocheContent);
		dataContent.addComponent(clienteContent);
		dataContent.addComponent(vendedorContent);
	}

	private void listCoches() {
		gridCoche.setItems(cocheService.findAll(0, 30));
	}

	private void listClientes() {
		gridCliente.setItems(clienteService.findAll(0, 30));
	}

	private void listVendedores() {
		gridVendedor.setItems(vendedorService.findAll(0, 30));
	}

	/*
	 * MAIN VIEW SETTINGS
	 */
	private void setWebContent() {
		setDataContent();
		webContent.addComponent(new Label("Concesionario"));
		webContent.addComponent(dataContent);
	}

	/*
	 * ADDITIONS / ELIMINATIONS / EDITIONS
	 */
	private void addCoche(ClickEvent clickEvent) throws NotFoundExcept {
		CocheDTO cocheDTO = new CocheDTO();
		addCocheSetValues(cocheDTO);
		cocheService.create(cocheDTO);
		addCocheEraseFields();
		this.refresh(clickEvent);
	}

	private void addCocheSetValues(CocheDTO cocheDTO) {
		cocheDTO.setMatricula(cocheAddMatriculaTF.getValue());
		cocheDTO.setMarca(cocheAddMarcaTF.getValue());
		cocheDTO.setModelo(cocheAddModeloTF.getValue());
		cocheDTO.setMotor(cocheAddMotorTF.getValue());
		cocheDTO.setNumBastidor((Integer.parseInt(cocheAddBastidorTF.getValue())));
		cocheDTO.setPrecio(Double.parseDouble(cocheAddPrecioTF.getValue()));
		cocheDTO.setFechaVenta(cocheAddFechaVentaTF.getValue());
	}

	private void addCocheEraseFields() {
		cocheAddMatriculaTF.setValue("");
		cocheAddMarcaTF.setValue("");
		cocheAddModeloTF.setValue("");
		cocheAddMotorTF.setValue("");
		cocheAddBastidorTF.setValue("");
		cocheAddPrecioTF.setValue("");
		cocheAddFechaVentaTF.setValue("");
	}

	private void editCoche(ClickEvent event) throws NotFoundExcept {
		CocheDTO cocheDTO = cocheService.findById(Integer.parseInt(cocheEditIdTF.getValue()));
		editCocheSetValues(cocheDTO);
		cocheService.update(cocheDTO.getId(), cocheDTO);
		editCocheEraseFields();
		this.refresh(event);
	}

	private void editCocheSetValues(CocheDTO cocheDTO) {
		if (!cocheEditMarcaTF.isEmpty())
			cocheDTO.setMarca(cocheEditMarcaTF.getValue());
		if (!cocheEditModeloTF.isEmpty())
			cocheDTO.setModelo(cocheEditModeloTF.getValue());
		if (!cocheEditMotorTF.isEmpty())
			cocheDTO.setMotor(cocheEditMotorTF.getValue());
		if (!cocheEditBastidorTF.isEmpty())
			cocheDTO.setNumBastidor(Integer.parseInt(cocheEditBastidorTF.getValue()));
		if (!cocheEditPrecioTF.isEmpty())
			cocheDTO.setPrecio(Double.parseDouble(cocheEditPrecioTF.getValue()));
		if (!cocheEditFechaVentaTF.isEmpty())
			cocheDTO.setFechaVenta(cocheEditFechaVentaTF.getValue());
	}

	private void editCocheEraseFields() {
		cocheEditMarcaTF.setValue("");
		cocheEditModeloTF.setValue("");
		cocheEditMotorTF.setValue("");
		cocheEditBastidorTF.setValue("");
		cocheEditPrecioTF.setValue("");
		cocheEditFechaVentaTF.setValue("");
	}

	private void deleteCoche(ClickEvent event) throws NotFoundExcept {
		cocheService.delete(Integer.parseInt(cocheDeleteIdTF.getValue()));
		cocheDeleteIdTF.setValue("");
		this.refresh(event);
	}

	private void addCliente(ClickEvent event) throws NotFoundExcept {
		ClienteDTO clienteDTO = new ClienteDTO();
		addClienteSetValues(clienteDTO);
		clienteService.create(clienteDTO);
		addClienteEraseFields();
		this.refresh(event);
	}

	private void addClienteSetValues(ClienteDTO clienteDTO) {
		clienteDTO.setDni(clienteAddDniTF.getValue());
		clienteDTO.setNombre(clienteAddNombreTF.getValue());
		clienteDTO.setTlf(Integer.parseInt(clienteAddTflTF.getValue()));
		clienteDTO.setEmail(clienteAddEmailTF.getValue());
	}

	private void addClienteEraseFields() {
		clienteAddDniTF.setValue("");
		clienteAddNombreTF.setValue("");
		clienteAddTflTF.setValue("");
		clienteAddEmailTF.setValue("");
	}

	private void editCliente(ClickEvent event) throws NotFoundExcept {
		ClienteDTO clienteDTO = clienteService.findById(Integer.parseInt(clienteEditIdTF.getValue()));
		editClienteSetValues(clienteDTO);
		clienteService.update(clienteDTO.getId(), clienteDTO);
		editClienteEraseFields();
		this.refresh(event);
	}

	private void editClienteSetValues(ClienteDTO clienteDTO) {
		if (!clienteEditDniTF.isEmpty())
			clienteDTO.setDni(clienteEditDniTF.getValue());
		if (!clienteEditNombreTF.isEmpty())
			clienteDTO.setNombre(clienteEditNombreTF.getValue());
		if (!clienteEditTlfTF.isEmpty())
			clienteDTO.setTlf(Integer.parseInt(clienteEditTlfTF.getValue()));
		if (!clienteEditEmailTF.isEmpty())
			clienteDTO.setEmail(clienteEditEmailTF.getValue());
	}

	private void editClienteEraseFields() {
		clienteEditDniTF.setValue("");
		clienteEditNombreTF.setValue("");
		clienteEditTlfTF.setValue("");
		clienteEditEmailTF.setValue("");
	}

	private void deleteCliente(ClickEvent event) throws NotFoundExcept {
		clienteService.delete(Integer.parseInt(clienteDeleteIdTF.getValue()));
		clienteDeleteIdTF.setValue("");
		this.refresh(event);
	}

	private void addVendedor(ClickEvent event) throws NotFoundExcept {
		VendedorDTO vendedorDTO = new VendedorDTO();
		addVendedorSetValues(vendedorDTO);
		vendedorService.create(vendedorDTO);
		addVendedorEraseFields();
		this.refresh(event);
	}

	private void addVendedorSetValues(VendedorDTO vendedorDTO) {
		vendedorDTO.setDni(vendedorAddDniTF.getValue());
		vendedorDTO.setNombre(vendedorAddNombreTF.getValue());
		vendedorDTO.setTlf(Integer.parseInt(vendedorAddTflTF.getValue()));
		vendedorDTO.setEmail(vendedorAddEmailTF.getValue());
	}

	private void addVendedorEraseFields() {
		vendedorAddDniTF.setValue("");
		vendedorAddNombreTF.setValue("");
		vendedorAddTflTF.setValue("");
		vendedorAddEmailTF.setValue("");
	}

	private void editVendedor(ClickEvent event) throws NotFoundExcept {
		VendedorDTO vendedorDTO = vendedorService.findById(Integer.parseInt(vendedorEditIdTF.getValue()));
		editVendedorSetValues(vendedorDTO);
		vendedorService.update(vendedorDTO.getId(), vendedorDTO);
		editVendedorEraseFields();
		this.refresh(event);
	}

	private void editVendedorSetValues(VendedorDTO vendedorDTO) {
		if (!vendedorEditDniTF.isEmpty())
			vendedorDTO.setDni(vendedorEditDniTF.getValue());
		if (!vendedorEditNombreTF.isEmpty())
			vendedorDTO.setNombre(vendedorEditNombreTF.getValue());
		if (!vendedorEditTlfTF.isEmpty())
			vendedorDTO.setTlf(Integer.parseInt(vendedorEditTlfTF.getValue()));
		if (!vendedorEditEmailTF.isEmpty())
			vendedorDTO.setEmail(vendedorEditEmailTF.getValue());
	}

	private void editVendedorEraseFields() {
		vendedorEditDniTF.setValue("");
		vendedorEditNombreTF.setValue("");
		vendedorEditTlfTF.setValue("");
		vendedorEditEmailTF.setValue("");
	}

	private void deleteVendedor(ClickEvent event) throws NotFoundExcept {
		vendedorService.delete(Integer.parseInt(vendedorDeleteIdTF.getValue()));
		vendedorDeleteIdTF.setValue("");
		this.refresh(event);
	}

//	private void sellCar(ClickEvent event) throws NotFoundExcept {
//		cocheService.newSell(Integer.parseInt(cocheSellOutIdCocheTF.getValue()),
//				Integer.parseInt(cocheSellOutIdClienteTF.getValue()), Integer.parseInt(cocheSellOutIdVendedorTF.getValue()));
//		this.refresh(event);
//	}

	public void refresh(ClickEvent clickEvent) throws NotFoundExcept {
		listCoches();
		listClientes();
		listVendedores();
	}

}
