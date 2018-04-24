import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CaeserCipherMain {

	static HashMap<Character,Integer> hmap=new HashMap<Character,Integer>();
	//Method to convert Plain Text to Cipher Text
	public String plainTexttoCipherText(int k,String p)
	{
		char[] cTchar = new char[p.length()] ;
		for(int i=0;i<p.length();i++)
		{
		char pT = p.charAt(i);
		int v=hmap.get(pT);
		//Adding the key value to the each character in plain text to convert to cipher text
		v=(v+k)%26;
		if(v<0)
		{
			v=26+v+1;
		}
		Set setkeys = hmap.keySet();
        Iterator it = setkeys.iterator();
        while(it.hasNext())
        {
            char key = (char) it.next();
            if (hmap.get(key).equals(v))
            {
            		cTchar[i]=key;
                System.out.println(key);
            }
        }
		}
		String cipherText=String.valueOf(cTchar);
		return cipherText;
	}
	//Method to convert the cipher text to plain text
	public String cipherTexttoPlainText(int k,String c)
	{
		char[] pTchar = new char[c.length()] ;
		for(int i=0;i<c.length();i++)
		{
		char cT = c.charAt(i);
		int key=hmap.get(cT);
		//Subtracting the key value to the each character in cipher text to convert to plain text
		key=(key-k)%26;
		Set setkeys = hmap.keySet();
        Iterator it = setkeys.iterator();
        while(it.hasNext())
        {
            char Ckey = (char) it.next();
            if (hmap.get(Ckey).equals(key))
            {
            	pTchar[i]=Ckey;
                System.out.println(Ckey);
            }
        }
		}
		String plainText=String.valueOf(pTchar);
		return plainText;
	}
	public static void main(String[] args) {
		//Assigning the each Alphabet to a number, for convertion of plain/cipher text
		hmap.put('A',1);hmap.put('H',8);hmap.put('O',15);hmap.put('V',22);
		hmap.put('B',2);hmap.put('I',9);hmap.put('P',16);hmap.put('W',23);
		hmap.put('C',3);hmap.put('J',10);hmap.put('Q',17);hmap.put('X',24);
		hmap.put('D',4);hmap.put('K',11);hmap.put('R',18);hmap.put('Y',25);
		hmap.put('E',5);hmap.put('L',12);hmap.put('S',19);hmap.put('Z',26);
		hmap.put('F',6);hmap.put('M',13);hmap.put('T',20);
		hmap.put('G',7);hmap.put('N',14);hmap.put('U',21);
		int key = 0;
		String plainText = null;
		String cipherText = null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter a key:");
		key=sc.nextInt();
		System.out.println("Please enter text that needs to be encrypted:(Plain Text) ");
		plainText=sc.next();
		CaeserCipherMain cc=new CaeserCipherMain();
		String cipher = cc.plainTexttoCipherText(key,plainText.toUpperCase());
		System.out.println("Cipher Text After Encryption is :"+cipher);
		
		System.out.println("Please enter encrypted text to get plain text :");
		cipherText=sc.next();
		
		String plain = cc.cipherTexttoPlainText(key,cipherText.toUpperCase());
		System.out.println("Plain Text After Decryption is :"+plain);
	}

}
