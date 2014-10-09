package raz.test.String.Manipulation;

public class StringFormatDemo {
	
	public static void main(String[] args){
							   ///capabilityWorkflow/${cnum}/${requestId}/attachments/${attachmentId}
		String url = "es/package/%s/attachments/%s";
		
		//String cnum = "Y9BAXG826";
		String packageId = "capabilityWorkflow/Y9BAXG826/REQ10937962";
		String attachmentId = "27050e65-7af9-4377-ad2e-48c2bf655f46";
		
		String finalUrl = String.format(url,  packageId, attachmentId  );
		
		System.out.println(finalUrl);
	}

}
