import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MatrixTranspositionCipher {
	static HashMap<Integer,String> hm=new HashMap<Integer,String>();
	//Method for fetching the size of the matrix, by getting the perfect square near to the length of the plain text
	public int perfectSquare(double num)
	{
		if(Math.sqrt(num)%1==0)
		{
			return (int)Math.sqrt(num);
		}
		else
		{
			int x = (int) Math.round(Math.sqrt(num));
			if(x==(int)Math.sqrt(num))
			{
				x=x+1;
			}
			return x;
		}
	}
	//Method to create a matrix with the plain Text
	public void createMatrix(String pT,int n)
	{
		StringBuilder sb=new StringBuilder();
		StringBuilder sbA=new StringBuilder();
		sb.append(pT);
		while(sb.length()!=n*n)
		{
			sb.append('%');
		}
		char[][] matrix1=new char[n][n];
		int k=0;
		while(k<sb.length())
		{
		for(int i=0; i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				matrix1[i][j]=sb.charAt(k);
				System.out.print(" "+matrix1[i][j]);
				k++;	
			}
			System.out.println("");
		 }
		}
		//storing values column wise in hashmap
		int hKey=1;
		for(int j=0; j<n;j++)
		{
			sbA = new StringBuilder();
			for(int i=0;i<n;i++)
			{
				sbA.append(matrix1[i][j]);
				hm.put(hKey, sbA.toString());
			}
			hKey++;
		}
	}
	//Method to convert the Plain Text to Cipher text
	public String plainToCipher(String key)
	{
		StringBuilder cT=new StringBuilder();
		String[] keys=key.split(",");
		for(int i=0;i<keys.length;i++)
		{
			int k=Integer.parseInt(keys[i]);
			cT.append(hm.get(k));
		}
		return cT.toString();
	}
	//Method to convert cipher text to plain Text
	public String cipherToPlain(String cT,String key)
	{
		HashMap<Integer,String> hmap=new HashMap<Integer,String>();
		String[] keys=key.split(",");
		//Spliting the cipher text to substring that are equal to the length of the key
		String[] subStr = cT.split("(?<=\\G.{"+keys.length+"})");
		System.out.println(Arrays.toString(subStr));
		for(int i=0;i<keys.length;i++)
		{
			int k=Integer.parseInt(keys[i]);
			hmap.put(k, subStr[i]);
		}
		StringBuilder stb = new StringBuilder();
		for(int i=1;i<=keys.length;i++)
		{
			stb.append(hmap.get(i));
		}
		int n=keys.length;
		char[][] matrix1=new char[n][n];
		int k=0;
		while(k<stb.length())
		{
		for(int i=0; i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				matrix1[i][j]=stb.charAt(k);
				System.out.print(" "+matrix1[i][j]);
				k++;	
			}
			System.out.println("");
		 }
		}
		StringBuilder plain = new StringBuilder();
		for(int j=0; j<n;j++)
		{
			
			for(int i=0;i<n;i++)
			{
				plain.append(matrix1[i][j]);
			}
		}
		return plain.toString();
	}
	public static void main(String[] args) {
		int sqNum;
		String key;
		String plainText = null;
		String cipherText = null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter text that needs to be encrypted:(Plain Text) ");
		plainText=sc.nextLine();
		MatrixTranspositionCipher mt=new MatrixTranspositionCipher();
		sqNum=mt.perfectSquare(plainText.length());
		//Replacing the spaces in a plain Text with %
		plainText = plainText.replaceAll("\\s+","%");
		mt.createMatrix(plainText,sqNum);
		System.out.println("Please enter a key (sequence of numbers eg: 5,3,2,1,4 format) less than or equal to "+sqNum+" : ");
		key=sc.nextLine();
		cipherText = mt.plainToCipher(key);
		System.out.println("Cipher Text After encryption is "+cipherText);
		String plainTextAfterDec=mt.cipherToPlain(cipherText,key);
		System.out.println("Plain Text After Decryption with the above key is ::  "+plainTextAfterDec.replaceAll("%", " "));
	}
}
