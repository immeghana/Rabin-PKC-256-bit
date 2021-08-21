

//the values of p,q and n are to be passed to receiver along with the encrypted text.


// This is the code that has to run from the side of receiver.


import java.math.BigInteger;//it will be used to generate a datatype that is capable to store 256 bit number;

import java.nio.charset.Charset;

import java.security.SecureRandom; 

import java.util.Random; 

import java.lang.Math;

import java.util.Scanner;

public class Decryption{

    private static Random r = new SecureRandom(); 

	public static void main(String[] args) 
	{ 
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the public key n");
		BigInteger n = sc.nextBigInteger();
		
		System.out.println("enter the private key p given by sender");
		BigInteger p = sc.nextBigInteger(); 
		
		System.out.println("enter the private key q given by sender");
		BigInteger q = sc.nextBigInteger();
		
		
        	String finalMessage = null; 
        	int i = 1; 
		
        	System.out.println("enter the message");
        	BigInteger ciphermsg=sc.nextBigInteger();
    
        	BigInteger[] m2 = decryption(ciphermsg, p, q); 
        	for (BigInteger b : m2) { 
            		String dec = new String( b.toByteArray(),Charset.forName("ascii")); 
            
        		System.out.println("Message received by Receiver : " + dec); 

		} 
	}
	
	public static BigInteger[] decryption(BigInteger cry, BigInteger p, BigInteger q){
		BigInteger four=new BigInteger("4");
		BigInteger one=new BigInteger("1");
	    
		BigInteger n=p.multiply(q);
		BigInteger c=cry;
		
		BigInteger powerofC=p.add(one).divide(four);
		BigInteger powerofC1=q.add(one).divide(four);
	    
	    //BigInteger a1=c.modpow(powerofC,p);
	    
		BigInteger a1 = c.modPow(p.add(BigInteger.ONE).divide(four),p);
	    
		BigInteger a2=p.subtract(a1);
	    //BigInteger b1=c.modpow(powerofC1,q);
	   
		BigInteger b1 = c.modPow(q .add(BigInteger.ONE).divide(four),q); 
	    
		BigInteger b2=q.subtract(b1);
	    
	    
	    //forp1 that is (a1,b1);
	    
		BigInteger x1=q.modInverse(p);
		BigInteger x2=p.modInverse(q);
		
		BigInteger p1=q.multiply(x1).multiply(a1).add(p.multiply(x2).multiply(b1)).mod(n);
	    
	 
	    //forp2 that is (a1,b2);
	    
	   
		BigInteger p2=q.multiply(x1).multiply(a1).add(p.multiply(x2).multiply(b2)).mod(n);
	    
	    //for p3 that is (a2,b1);
	    
		BigInteger p3=q.multiply(x1).multiply(a2).add(p.multiply(x2).multiply(b1)).mod(n);
	    
	    //for p4 that is (a2,b2);
	    
		BigInteger p4=q.multiply(x1).multiply(a2).add(p.multiply(x2).multiply(b2)).mod(n);
	    
	    	return new BigInteger[] { p1, p2, p3, p4 }; 
	    
	}
}