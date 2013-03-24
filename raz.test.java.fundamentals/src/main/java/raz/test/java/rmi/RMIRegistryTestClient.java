package raz.test.java.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistryTestClient {
	public void testRegistry() throws Exception {
		Registry registry = LocateRegistry.getRegistry("localhost", 1098);
		if (registry != null) {
			String[] entries = registry.list();
			if (entries != null) {
				for (int x = 0; x < entries.length; x++) {
					System.out.println("entry " + x + ": " + entries[x]);
				}
			} else {
				System.out.println("Failed to get entries.");
			}
		} else {
			System.out.println("Failed to get regsitry.");
		}

		String[] listing = Naming.list("//localhost:3455");
		if (listing != null) {
			for (int x = 0; x < listing.length; x++) {
				System.out.println("listing " + x + ": " + listing[x]);
			}
		} else {
			System.out.println("Failed to get listing.");
		}
	}

	public static void main(String[] args) {
		RMIRegistryTestClient client = new RMIRegistryTestClient();
		try {
			client.testRegistry();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}