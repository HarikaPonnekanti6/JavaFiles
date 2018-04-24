package Assignment2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BitStuffing {
	int space;
	String content = null;
	//create a method for Bit stuffing that is converting the text file to bits and add 0 after every consecutive 1's
	public String BitStuffingFromText2Binary(File file)
	{
		FileReader reader = null;
		int ascii;
		String s = null;
		StringBuilder binaryStr = new StringBuilder();
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
			//checking if the string contains '_' if yes converts that into space and at receiver it converts space to '_'
			if(c[i] == '_') {
				c[i] = ' ';
			}
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
		binaryStr.append(s);
		return binaryStr.toString().replaceAll("11111", "111110");
	}
//Method to remove BitStuffing and convert to text and adds into new file.
	public void binary2Text(String s)
	{
		s.replaceAll("111110", "11111");
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < s.length()/8; i++) {
			String sub = s.substring(i * 8, (i + 1) * 8);
			int ch =  Integer.parseInt(sub, 2);
			char c1 = (char) ch;
			if(c1 == ' ') 
			{
				c1= '_';
			}
			if(i != s.length()/8-1)
			{
				str.append(c1);
			}
		}
		try {
			String s1="";
			//checks if the original string contains space or not
			if(content.contains(" "))
			{
				char[] c2 = str.toString().toCharArray();
				for(int j=0;j<str.length();j++)
				{
					if(c2[j] == '_') {

						c2[j] = ' ';
					}
					s1+=c2[j];
				}
				BufferedWriter out = new BufferedWriter(new FileWriter("/users/harika93/eclipse-workspace/ComputerNetworks/resource/BitStuffingOutput2.txt"));
				out.write(s1.toString());
				out.close();
			}
			else
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("/users/harika93/eclipse-workspace/ComputerNetworks/resource/BitStuffingOutput2.txt"));
				out.write(str.toString());
				out.close();
			}

		}
		catch (IOException e)
		{
			System.out.println("Exception");

		}
	}

	public static void main(String[] args) {
		File file = new File("/users/harika93/eclipse-workspace/ComputerNetworks/resource/BitStuffInpu2.txt"); //Loading Input text file
		BitStuffing bitStuffing = new BitStuffing();
		String newString = bitStuffing.BitStuffingFromText2Binary(file);
		System.out.println("New String : "+newString);
		bitStuffing.binary2Text(newString);
	}

}

