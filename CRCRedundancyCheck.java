package Assignment2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CRCRedundancyCheck {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		String message,key;
		System.out.println("Enter Message bits : ");
		message = sc.nextLine();
		int msgLen = message.length();
		int[] data=new int[msgLen];
		for(int i=0; i<msgLen; i++)
			data[i]=Integer.parseInt(String.valueOf(message.charAt(i)));

		System.out.println("Enter Key bits : ");
		key = sc.nextLine();
		int keyLen = key.length();
		int[] divisor = new int[keyLen];
		for(int i=0; i<keyLen; i++)
			divisor[i]=Integer.parseInt(String.valueOf(key.charAt(i)));

		int totallength=msgLen+keyLen-1;
		int[] divident=new int[totallength];
		int[] reminder=new int[totallength];
		int[] crcGenerated=new int[totallength];
		  
		for(int i=0;i<data.length;i++)
			divident[i]=data[i];

		System.out.print("new dividend Appended with 0's:");
		for(int i=0; i< divident.length; i++)
			System.out.print(divident[i]);        
		System.out.println();

		for(int j=0; j<divident.length; j++){
			reminder[j] = divident[j];
		}

		reminder=divide(divident, divisor, reminder);

		for(int i=0;i<divident.length;i++)
		{
			crcGenerated[i]=(divident[i]^reminder[i]);
		}
		System.out.println("String transmitted is : "+Arrays.toString(crcGenerated));

		System.out.println("Enter any bit String that is recieved");
		String crcCheck = sc.next();
		for(int i=0; i<crcGenerated.length; i++)
			crcGenerated[i]=Integer.parseInt(String.valueOf(crcCheck.charAt(i)));
      
		for(int j=0; j<crcGenerated.length; j++){
			reminder[j] = crcGenerated[j];
		}

		reminder=divide(crcGenerated, divisor, reminder);

		for(int i=0; i< reminder.length; i++)
		{
			if(reminder[i]!=0)
			{
				System.out.println("******Error!!!*****");
				break;
			}
			if(i==reminder.length-1)
				System.out.println("*****No Error******");
		}
	}

	static int[] divide(int divident[],int divisor[], int reminder[])
	{
		int current=0;
		while(true)
		{
			for(int i=0;i<divisor.length;i++)
				reminder[current+i]=(reminder[current+i]^divisor[i]);

			while(reminder[current]==0 && current!=reminder.length-1)
				current++;

			if((reminder.length-current)<divisor.length)
				break;
		}
		return reminder;
	}

}
