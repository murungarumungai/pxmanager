package razvan.visma.test.dao;





public class DAOFactoryMockup {

	private static final DAOFactoryMockup INSTANCE = new DAOFactoryMockup();

	/**
	 * private constructor
	 * */
	private DAOFactoryMockup() {
	}

	public static synchronized DAOFactoryMockup getInstance() {
		return INSTANCE;
	}

	public CustomerDAO getCustomerDAO() {
		return new MockupCustomerDAO();
	}

	public ProductDAO getProductDAO() {
		return new MockupProductDAO();
	}

}
