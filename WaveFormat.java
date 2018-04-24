package Assignment2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WaveFormat {

	public static void main(String[] args) throws IOException {
		int ascii;
		String content = null;
		String s = null;
		StringBuilder binaryStr = new StringBuilder(); 	//To store the Binary string
		StringBuilder graphMan = new StringBuilder();	//To store the Manchester wave format
		StringBuilder graphNRZ = new StringBuilder();	//To store the NRZ wave format
		File file = new File("/users/harika93/eclipse-workspace/ComputerNetworks/resource/input1.txt"); //Loading Input text file
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			//Reads the Input file and stores in a character array
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		char[] c = content.toCharArray();
		for(int i=0;i<c.length;i++)
		{
			ascii = (int) c[i];
			//Converting each character to ASCII
			s = Integer.toBinaryString(ascii);
			//adjusting the length of the binary string
			if(s.length() < 8 )
			{
				int size = 8-s.length();
				while(size != 0)
				{
					s = "0"+s;
					size--;
				}
			}
			else if(s.length() > 8)
			{
				s = s.substring(s.length() - 8);
			}
			binaryStr.append(s);
		}
		System.out.println("Binary is : "+binaryStr.toString());
		//Converting Binary String to Manchester wave format
		int count = 0;
		char previous = '0'; //Assuming that wave starts from 0 
		for(int j=0;j<binaryStr.toString().length();j++)
		{
			char ca = binaryStr.toString().charAt(j);
			if(ca == '0' && previous =='1')
			{
				graphMan.append("-|_");
				count++;
			}
			if(ca == '1' && previous == '0')
			{
				graphMan.append("_|-");
				count++;
			}
			if(ca == '1' && previous == '1')
			{
				graphMan.append("-|_|");
			}
			if(ca == '0' && previous == '0')
			{
				graphMan.append("_|-|");
			}
			previous=ca;
		}
		System.out.println("graph : "+graphMan.toString());
		System.out.println("Number of transistions in Manchester : "+count);
		//Writing the Manchester wave format to a text file.
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("/users/harika93/eclipse-workspace/ComputerNetworks/resource/Manchester.txt"));
			out.write(graphMan.toString());
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Exception");

		}
		//Converting the binary string to NRZ wave format
		previous = '0'; //Assuming that wave starts from 0 
		count = 0;
		for(int k=0;k<binaryStr.toString().length();k++)
		{
			char ca1 = binaryStr.toString().charAt(k);

			if(ca1 == '0' && previous =='1')
			{
				graphNRZ.append("-|");
				count++;	
			}
			if(ca1 == '1' && previous == '0')
			{
				graphNRZ.append("_|");
				count++;
			}
			if(ca1 == '1' && previous == '1')
			{
				graphNRZ.append("-");	
			}
			if(ca1 == '0' && previous == '0')
			{
				graphNRZ.append("_");
			}
			previous = ca1;
		}
		System.out.println("graph2 : "+graphNRZ.toString());
		System.out.println("Number of Transistions for NRZ :"+count);
		//Writing the NRZ wave format to a text file
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("/users/harika93/eclipse-workspace/ComputerNetworks/resource/NRZ.txt"));
			out.write(graphNRZ.toString());
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Exception");
		}
	}
}

