package raz.test.collection;

public class AdminRole implements Comparable<Object>{
	
	
	/**
	 * keep an internal ROLE_ORDER, how should the roles be ordered in a meaningful
	 * way.
	 * The natural sorting order is the order from the ENUM. 
	 * So you don't even need the additional <code>int order</code> property
	 * 
	 * this is needed when we calculate diffRoles, we want sort the roles in the
	 * order of their importance and NOT alphabetically
	 * */
	private enum ROLE_TYPE /*implements Comparable<ROLE_TYPE>*/ {

		 
		SYSADMIN("sysadmin" /*, 1*/), //most important
		CCL("ccl"/*, 2*/),
		CCLD("ccld"/*,3*/),
		CCADMIN("ccadmin"/*,4*/),
		UNDEFINED("null"/*, 999*/)
		;

		private String role;
		//private int order;

		ROLE_TYPE(String role /*, int order*/) {
			this.role = role;
			//this.order = order;
		}
		
		public static ROLE_TYPE fromValue(String val){
			
			for(ROLE_TYPE item : ROLE_TYPE.values()){
				if(item.role.equals(val)){
					return item;
				}
			}
			return UNDEFINED;
		}
	}
	
	private String cnum;
	private String role;
	private String capabilityId;
	private ROLE_TYPE roleOrder;
	
	public AdminRole(String cnum, String role,  String capabilityId){
		this.setCnum(cnum);
		this.setRole(role);
		this.setCapabilityId(capabilityId);
		this.roleOrder = ROLE_TYPE.fromValue(role);
	}
	

	
	private ROLE_TYPE getRoleOrder(){
		return roleOrder;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
		this.roleOrder = ROLE_TYPE.fromValue(role);
	}

	public String getCapabilityId() {
		return capabilityId;
	}

	public void setCapabilityId(String capabilityId) {
		this.capabilityId = capabilityId;
	}
	
	@Override
	public String toString(){
		return "["+role+"]";
	}

	@Override
	public int compareTo(Object o) {
		
		return getRoleOrder().compareTo( ((AdminRole) o).getRoleOrder() );
	}

}
