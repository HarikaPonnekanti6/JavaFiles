import java.util.Arrays;
import java.util.Scanner;

public class LFSRegister {

	public static void main(String[] args) {
		/*
		 * a) Length of the shift register 
		 * b) Tap positions
c) Seed value
d) Number of clock cycles
For example, if the input to the program is: 4
03 1011 8
		 */
		int length,clocks;
		String tapS;
		String seed;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Length of the shift register ");
		length=sc.nextInt();
		System.out.println("Enter Number of clock cycles ");
		clocks=sc.nextInt();
		System.out.println("Enter Seed value ");
		seed=sc.next();
		System.out.println("Enter Tap positions");
		tapS=sc.next();
		int[] tap=new int[tapS.length()];
		int[] value=new int[seed.length()];
		StringBuilder output=new StringBuilder();
		StringBuilder input = new StringBuilder();
		//Storing Taps values from a string to int array
		for(int i=0;i<tapS.length();i++)
		{	
			tap[i]=Character.getNumericValue(tapS.charAt(i));
		}
		input.append(seed);
		//Reverse the seed value
		seed=input.reverse().toString();
		//Storing the seed value into a int array
		for(int i=0;i<seed.length();i++)
		{	
			value[i]=Character.getNumericValue(seed.charAt(i));
		}
		//Looping through for the clock cycles mentioned
		for(int i=0;i<clocks;i++)
		{
			output.append(String.valueOf(value[length-1]));
			int x=value[length-1];
			for(int j=0;j<tap.length-1;j++)
			{
				int y=value[tap[j]]^x;
				x=y;
			}
			for(int k=length-1;k>0;k--)
			{
				value[k]=value[k-1];
			}
			value[0]=x;
		}
		System.out.println("Pseduo Random code is :"+output.reverse());
	}

}
