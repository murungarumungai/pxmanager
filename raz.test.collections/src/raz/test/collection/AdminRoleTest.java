package raz.test.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminRoleTest {
	
	static String cnum = "";
	static String capabilityId = "";
	
	public static void main(String[] args){
			
		AdminRole sysAdmin = new AdminRole(cnum, "sysadmin", capabilityId);
		AdminRole ccl = new AdminRole(cnum, "ccl", capabilityId);
		AdminRole undefined =  new AdminRole(cnum, "bla", capabilityId);
		AdminRole ccld = new AdminRole(cnum, "ccld", capabilityId);
		AdminRole ccadmin = new AdminRole(cnum, "ccadmin", capabilityId);
		
		List<AdminRole> roles = new ArrayList<AdminRole>();
		
		roles.add(ccadmin);
		roles.add(undefined);
		roles.add(ccl);
		roles.add(sysAdmin);
		roles.add(ccld);
		
		System.out.println("Before Sorting");
		System.out.println(roles);
		
		System.out.println("After Sorting");
		Collections.sort(roles);
		System.out.println(roles);
	}

}
