import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class VigenereCipher {
	static HashMap<Character,String> hmapRows=new HashMap<Character,String>();
	static HashMap<Character,Integer> hmap=new HashMap<Character,Integer>();
	//Method to convert Plain Text to Cipher Text
	public String plainTexttoCipherText(String k,String p)
	{
		char[] cTchar = new char[p.length()] ;
		for(int i=0;i<p.length();i++)
		{
			char v1=p.charAt(i);
			char v2=k.charAt(i);
			int x=hmap.get(v2);
			String s1 = hmapRows.get(v1);
			cTchar[i]=s1.charAt(x);
		}
		String cipherText=String.valueOf(cTchar);
		return cipherText;
	}
	//Method to convert the cipher text to plain text
	public String cipherTexttoPlainText(String k,String c)
	{
		char[] pTchar = new char[c.length()] ;
		for(int i=0;i<c.length();i++)
		{
			char v1=c.charAt(i);
			char v2=k.charAt(i);
			String s1 = hmapRows.get(v2);
			int x = s1.indexOf(v1);
			Set setkeys = hmap.keySet();
	        Iterator it = setkeys.iterator();
	        while(it.hasNext())
	        {
	            char key = (char) it.next();
	            if (hmap.get(key).equals(x))
	            {
	            		pTchar[i]=key;
	            }
	        }
		}
		String plainText=String.valueOf(pTchar);
		return plainText;
	}
	
	public static void main(String[] args) {
		//Assigning the Vigenere table to a HashMap
		hmap.put('A',0);hmap.put('H',7);hmap.put('O',14);hmap.put('V',21);
		hmap.put('B',1);hmap.put('I',8);hmap.put('P',15);hmap.put('W',22);
		hmap.put('C',2);hmap.put('J',9);hmap.put('Q',16);hmap.put('X',23);
		hmap.put('D',3);hmap.put('K',10);hmap.put('R',17);hmap.put('Y',24);
		hmap.put('E',4);hmap.put('L',11);hmap.put('S',18);hmap.put('Z',25);
		hmap.put('F',5);hmap.put('M',12);hmap.put('T',19);
		hmap.put('G',6);hmap.put('N',13);hmap.put('U',20);
		
		hmapRows.put('A', "ABCDEFGHIJKLMNOPQRSTUVWXYZ"); hmapRows.put('B', "BCDEFGHIJKLMNOPQRSTUVWXYZA");
		hmapRows.put('C', "CDEFGHIJKLMNOPQRSTUVWXYZAB"); hmapRows.put('D', "DEFGHIJKLMNOPQRSTUVWXYZABC");
		hmapRows.put('E', "EFGHIJKLMNOPQRSTUVWXYZABCD"); hmapRows.put('F', "FGHIJKLMNOPQRSTUVWXYZABCDE");
		hmapRows.put('G', "GHIJKLMNOPQRSTUVWXYZABCDEF"); hmapRows.put('H', "HIJKLMNOPQRSTUVWXYZABCDEFG");
		hmapRows.put('I', "IJKLMNOPQRSTUVWXYZABCDEFGH"); hmapRows.put('J', "JKLMNOPQRSTUVWXYZABCDEFGHI");
		hmapRows.put('K', "KLMNOPQRSTUVWXYZABCDEFGHIJ"); hmapRows.put('L', "LMNOPQRSTUVWXYZABCDEFGHIJK");
		hmapRows.put('M', "MNOPQRSTUVWXYZABCDEFGHIJKL"); hmapRows.put('N', "NOPQRSTUVWXYZABCDEFGHIJKLM");
		hmapRows.put('O', "OPQRSTUVWXYZABCDEFGHIJKLMN"); hmapRows.put('P', "PQRSTUVWXYZABCDEFGHIJKLMNO");
		hmapRows.put('Q', "QRSTUVWXYZABCDEFGHIJKLMNOP"); hmapRows.put('R', "RSTUVWXYZABCDEFGHIJKLMNOPQ");
		hmapRows.put('S', "STUVWXYZABCDEFGHIJKLMNOPQR"); hmapRows.put('T', "TUVWXYZABCDEFGHIJKLMNOPQRS");
		hmapRows.put('U', "UVWXYZABCDEFGHIJKLMNOPQRST"); hmapRows.put('V', "VWXYZABCDEFGHIJKLMNOPQRSTU");
		hmapRows.put('W', "WXYZABCDEFGHIJKLMNOPQRSTUV"); hmapRows.put('X', "XYZABCDEFGHIJKLMNOPQRSTUVW");
		hmapRows.put('Y', "YZABCDEFGHIJKLMNOPQRSTUVWX"); hmapRows.put('Z', "ZABCDEFGHIJKLMNOPQRSTUVWXY");
		//
		String str=null;
		StringBuilder key=new StringBuilder();
		String plainText = null;
		String cipherText = null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter a key Text:");
		str=sc.next();
		System.out.println("Please enter text that needs to be encrypted:(Plain Text) ");
		sc.nextLine();
		plainText=sc.nextLine();
		//Removing the space in plain text
		plainText = plainText.replaceAll("\\s+","");
	    key.append(str);
		if(plainText.length()>str.length())
		{
			key.append(str.substring(0, plainText.length()-str.length()));
		}
		VigenereCipher vc=new VigenereCipher();
		String cipher = vc.plainTexttoCipherText(key.toString().toUpperCase(),plainText.toUpperCase());
		System.out.println("Cipher Text After Encryption is :"+cipher);
		
		System.out.println("Please enter encrypted text to get plain text :");
		cipherText=sc.next();
		
		String plain = vc.cipherTexttoPlainText(key.toString().toUpperCase(),cipherText.toUpperCase());
		System.out.println("Plain Text After Decryption is :"+plain);
	}

}
