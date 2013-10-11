package razvan.visma.test.ui.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import razvan.visma.test.business.PriceCalculator;
import razvan.visma.test.dao.CustomerDAO;
import razvan.visma.test.dao.DAOFactoryMockup;
import razvan.visma.test.dao.ProductDAO;
import razvan.visma.test.model.Customer;
import razvan.visma.test.model.Product;
import razvan.visma.test.model.Rebate;

public class PriceCalculationView extends ViewPart {

	public static final String ID = "razvan.visma.test.views.PriceCalculationView";

	private CustomerDAO customerDAO = DAOFactoryMockup.getInstance()
			.getCustomerDAO();

	private ProductDAO productDAO = DAOFactoryMockup.getInstance()
			.getProductDAO();

	private TableViewer productTable;
	private TableViewer customerTable;

	private Text quantity;

	private Action calculatePrice;

	private Text result;

	public PriceCalculationView() {
		// TODO Auto-generated constructor stub

		// System.out.println("4  PriceCalculationView");
	}

	@Override
	public void createPartControl(Composite parent) {

		GridLayout layout = new GridLayout(4, false);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_BOTH);

		parent.setLayout(layout);
		parent.setLayoutData(gridData);

		// Composite customerComposite = new Composite(parent, SWT.NONE);
		Label customerLabel = new Label(parent, SWT.NONE);
		customerLabel.setText("Customers: ");

		// Composite productComposite = new Composite(parent, SWT.NONE);
		Label productLabel = new Label(parent, SWT.NONE);
		productLabel.setText("Products: ");

		// Composite quantityComposite = new Composite(parent, SWT.NONE);
		Label quantityLabel = new Label(parent, SWT.NONE);
		quantityLabel.setText("Quantity: ");

		Label resultLabel = new Label(parent, SWT.NONE);
		resultLabel.setText("Result: ");

		// 1
		createCustomerTable(parent);
		// 2
		createProductTable(parent);
		// 3
		quantity = new Text(parent, SWT.BORDER);
		// 4
		result = new Text(parent, SWT.READ_ONLY | SWT.BORDER);

		createAction();

		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		mgr.add(calculatePrice);

		updateActionEnablement();
	}

	private void createProductTable(Composite parent) {

		productTable = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createProductColumns(parent, productTable);

		final Table table = productTable.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		productTable.setContentProvider(new ArrayContentProvider());

		productTable.setInput(productDAO.getProducts());

		// ---- Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = false;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		customerTable.getControl().setLayoutData(gridData);

	}

	private void createCustomerTable(Composite parent) {

		customerTable = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createCustomerColumns(parent, customerTable);

		final Table table = customerTable.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		customerTable.setContentProvider(new ArrayContentProvider());

		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		customerTable.setInput(customerDAO.getCustomers());

		// ---- Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = false;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		customerTable.getControl().setLayoutData(gridData);

	}

	private void createCustomerColumns(Composite parent, TableViewer viewer) {

		String[] titles = { "Customer", "Rebate Type", "Rebate %" };
		int[] bounds = { 120, 150, 120 };

		// [0] First Column is the Customer Name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Customer cust = (Customer) element;
				return cust.getName();
			}
		});

		// [1] Second Column is Rebate
		col = createTableViewerColumn(viewer, titles[1], bounds[1]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Customer cust = (Customer) element;
				Rebate rebate = cust.getRebate();
				if (rebate == null) {
					return "-";
				} else {
					return rebate.toString();
				}
			}
		});

		// [2] Discount Percent
		col = createTableViewerColumn(viewer, titles[2], bounds[2]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Customer cust = (Customer) element;
				Rebate rebate = cust.getRebate();
				if (rebate == null) {
					return "-";
				} else {
					return String.valueOf(rebate.getDiscount());
				}
			}
		});

	}

	private void createProductColumns(Composite parent, TableViewer viewer) {

		String[] titles = { "Product", "Price", "Rebate Type", "Rebate %" };
		int[] bounds = { 120, 120, 120, 120 };

		// [0] First Column is the Customer Name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Product prod = (Product) element;
				return prod.getName();
			}
		});

		// [1] Price
		col = createTableViewerColumn(viewer, titles[1], bounds[1]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Product prod = (Product) element;
				return String.valueOf(prod.getPrice());
			}
		});

		// [2] Second Column is Rebate
		col = createTableViewerColumn(viewer, titles[2], bounds[2]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Product prod = (Product) element;
				Rebate rebate = prod.getRebate();
				if (rebate == null) {
					return "-";
				} else {
					return rebate.toString();
				}
			}
		});

		// [3] discount percent
		col = createTableViewerColumn(viewer, titles[3], bounds[3]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Product prod = (Product) element;
				Rebate rebate = prod.getRebate();
				if (rebate == null) {
					return "-";
				} else {
					return String.valueOf(rebate.getDiscount());
				}
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound) {

		final TableViewerColumn tableViewerColumn = new TableViewerColumn(
				viewer, SWT.NONE);
		final TableColumn column = tableViewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		// column.setMoveable(true);
		return tableViewerColumn;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void createAction() {

		calculatePrice = new Action("CalculatePrice") {
			@Override
			public void run() {
				IStructuredSelection selCust = (IStructuredSelection) customerTable
						.getSelection();
				IStructuredSelection selProd = (IStructuredSelection) productTable
						.getSelection();
				Customer cust = (Customer) selCust.getFirstElement();
				Product prod = (Product) selProd.getFirstElement();
				int quantity = readQuantity();

				Rebate customerRebate = cust.getRebate();
				Rebate productRebate = prod.getRebate();

				double discount = 0;

				if (customerRebate != null) {
					discount = discount + customerRebate.getDiscount();
				}

				if (productRebate != null) {
					discount = discount + productRebate.getDiscount();
				}

				double calculatedPrice = PriceCalculator.calculatePrice(
						prod.getPrice(), quantity, discount);

				result.setText(String.valueOf(calculatedPrice));
			}
		};

		quantity.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				updateActionEnablement();

			}
		});

		customerTable
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent arg0) {
						updateActionEnablement();

					}
				});

		productTable
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent arg0) {
						updateActionEnablement();
					}
				});
	}

	private int readQuantity() {
		String quantityInput = quantity.getText();

		// System.out.println(">>" + quantityInput);

		int quantity = 0;

		try {
			quantity = Integer.parseInt(quantityInput);
		} catch (NumberFormatException nfe) {
			// ingnored
		}
		return quantity;
	}

	public void updateActionEnablement() {
		IStructuredSelection selCust = (IStructuredSelection) customerTable
				.getSelection();
		IStructuredSelection selProd = (IStructuredSelection) productTable
				.getSelection();

		calculatePrice.setEnabled(selCust.size() > 0 && selProd.size() > 0
				&& readQuantity() > 0);
	}

}
