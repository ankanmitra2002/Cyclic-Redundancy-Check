package error_control;

import java.util.Iterator;
import java.util.Scanner;
/**
 * 
 * @author 
 *
 */
public class CRC {
	
	public static void main(String[] args) throws Exception{
		  Scanner sc = new Scanner(System.in);
		  System.out.print("Enter Divisor:");
		  String divisor = sc.nextLine();
		  System.out.print("Enter Dataword:");
		  String dataword = sc.nextLine();
		  
		  String augmentedData = getAugmentedData(dataword);
		 
//		  System.out.println("The remainder: " + mod2div(augmentedData,divisor));

		  String codeword = dataword + mod2div(augmentedData,divisor);
		  
		  System.out.println("The transmitted Code Word is: " + codeword);
		  
		  
		  String receivedCodeword = receive(codeword);
		  
		  System.out.println("The received Code Word is: " + receivedCodeword);

		  if(Integer.parseInt(mod2div(receivedCodeword,divisor)) == 0)
			  System.out.println("The received code word contains no errors.");
		  else
			  System.out.println("The received code word contains errors.");

	}
	

	static String mod2div(String augmentedDataword,String divisor)
	{
		 //TO BE IMPLEMENTED 
		int[] dividend = new int[augmentedDataword.length()];
		int[] intdivisor = new int[divisor.length()];
		int[] temp = new int[divisor.length()];
		
		
		for(int i = 0; i<augmentedDataword.length();i++) {
			dividend[i] = augmentedDataword.charAt(i) - '0';
		}
		for(int i = 0; i<divisor.length();i++) {
			intdivisor[i] = divisor.charAt(i) - '0';
		}
		for(int i = 0; i<temp.length;i++) {
			temp[i] = dividend[i];
		}
		int length = augmentedDataword.length()-divisor.length()+1;
		for (int i = 0; i <length; i++) {
			int j;
			if(temp[0] == 1) {
				for(j = 1 ; j < divisor.length(); j++) {
					temp[j-1] = temp[j] ^ intdivisor[j];
				}
			}else {
				for(j = 1 ; j < divisor.length(); j++) {
					temp[j-1] = temp[j];
				}
			}
			if(i+j < dividend.length) {
				temp[j-1] = dividend[i+j];
			}
		}
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i<divisor.length()-1;i++) {
			if(temp[i] == 1)
				builder.append('1');
			else
				builder.append('0');
		}
		String str = builder.toString();
		return str;
	}
	
	private static String getAugmentedData(String dataword) {
		//TO BE IMPLEMENTED 
		String str = dataword + "000";
		return str;
	}
	
	private static String receive(String codeword) {
		//TO BE IMPLEMENTED 
		// Simulate error during transmission in this method by changing some bits of codeword 
		String str;
		int check = (int) (Math.random()*2);
		if(check == 1) {
			char c = (codeword.charAt(codeword.length()-1) == '1') ? '0' : '1';
			str = codeword.substring(0,codeword.length()-1) + c;
			return str;
		}
		return codeword;
	}


	



}
