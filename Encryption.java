

//This code is to compile from the side of sender



import java.math.BigInteger;    //it will be used to generate a datatype that is capable to store 256 bit number;
import java.nio.charset.Charset;

import java.security.SecureRandom;

import java.util.Random; 

import java.lang.Math;

import java.util.Scanner;

public class Encryption {
	
	private static Random r = new SecureRandom(); 
	
	public static BigInteger[] generateKey(int bits){ 
		BigInteger p = randprime(bits); 
		BigInteger q = randprime(bits); 
		BigInteger n = p.multiply(q); 
		return new BigInteger[]{ p, q, n }; 
	} 
	
	public static BigInteger encryption(BigInteger m, BigInteger N) 
	{ 
		BigInteger msgSquare=m.multiply(m);
        	
		BigInteger cipher=msgSquare.mod(N);
        	
		return cipher;
	}
	
	public static BigInteger randprime(int bitLength) 
	{ 
		BigInteger x; 
	
		BigInteger f=new BigInteger("4");
	
		BigInteger three=new BigInteger("3");
        	Random rand = new Random();
        	
		while(true){
         		x = BigInteger.probablePrime(bitLength, rand); 
            		if(x.mod(f).equals(three)) break;
        	}	
        	return x;
	} 
	
	public static void main(String[] args) 
	{ 
		BigInteger[] key =generateKey(256); 
		BigInteger p = key[0]; 
		
		BigInteger q = key[1]; 
		BigInteger n = key[2]; 
		
        	String finalMessage = null; 
        	int i = 1; 
		
		Scanner sc=new Scanner(System.in);
        	System.out.println("enter the message");
        	
		String str=sc.nextLine();
		System.out.println("Message sent by the sender is : " + str); 


		BigInteger msg= new BigInteger(str.getBytes(Charset.forName("US-ASCII"))); 

		BigInteger cipher =encryption(msg, n); 

    		System.out.println("Encrypted Message is : " + cipher); 
    
        	System.out.println("these are the private keys");
		System.out.println(p);

		System.out.println(q);
		System.out.println("this public key");

		System.out.println(n);

	} 
}


