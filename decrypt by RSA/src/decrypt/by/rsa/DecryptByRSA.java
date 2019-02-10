/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//By : Abdullah Shublaq
package decrypt.by.rsa;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author jit
 */
public class DecryptByRSA {

    public static String decrypt(String message, BigInteger n, int d) {
     
        char Digit; 
        char Char;
        String FourNumberStr = "";
        BigInteger FourNumberInt;
        BigInteger m;
        BigInteger power;
        String decMessage = "";
        String HexStr = "";
        String TwoDigitStr;
        int TwoDigitInt;
        int Hex;
        int count = 0;
        int i = 0;
        int length = message.length();
        boolean oddLength = false;
        if (message.charAt(length - 1) == '*') {
            message = message.substring(0, length - 1);
            length--;
            oddLength = true;
        }
        while (length != 0) {
            if (count != 4) {
                Digit = message.charAt(i);
                FourNumberStr = FourNumberStr + Digit;
                count++;
                i++;
            } else if (count == 4) {
                FourNumberInt = BigInteger.valueOf(Integer.parseInt(FourNumberStr));
                power = FourNumberInt.pow(d);
                m = power.mod(n);
                HexStr = HexStr + String.format("%04d", m.intValue());
                //if (HexStr.length() == 1) {
                //  decMessage = decMessage + "A";
                //TwoDigitStr = HexStr.substring(0, 1);
                //} else {
                //  if (HexStr.length() % 2 == 0) {
                TwoDigitStr = HexStr.substring(0, 2);
                // } else {
                //    TwoDigitStr = HexStr.substring(0, 1);
                // }
                // }
                TwoDigitInt = Integer.parseInt(TwoDigitStr);
                Hex = TwoDigitInt + 65;
                Char = (char) Hex;
                decMessage = decMessage + Char;
                //if (HexStr.length() != 1) {
                //  if (HexStr.length() % 2 == 0) {
                TwoDigitStr = HexStr.substring(2);
                // } else {
                //   TwoDigitStr = HexStr.substring(1);
                //}
                TwoDigitInt = Integer.parseInt(TwoDigitStr);
                Hex = TwoDigitInt + 65;
                Char = (char) Hex;
                decMessage = decMessage + Char;
                //}
                count = 0;
                FourNumberStr = "";
                HexStr = "";
                length -= 4;
            }
        }
        if (oddLength) {
            decMessage = decMessage.substring(0, decMessage.length() - 2) + decMessage.charAt(decMessage.length() - 1);
        }
        return decMessage;
    }

    public static BigInteger inverse(int p, int q, Integer e) {
        BigInteger bi1, bi2, bi3;
        bi1 = new BigInteger(e.toString());
        Integer number = (p - 1) * (q - 1);
        bi2 = new BigInteger(number.toString());
        bi3 = bi1.modInverse(bi2);
        return bi3;
    }

    /*with n = 53 · 61 and e = 17 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the message");
        String message = in.nextLine().toUpperCase().trim();
        System.out.println("Enter n : (n=p*q) *p,q odd primes* ");
        System.out.print("p = ");
        BigInteger p = in.nextBigInteger();

        System.out.print("q = ");
        BigInteger q = in.nextBigInteger();
        BigInteger n = p.multiply(q);
        System.out.println("n = " + p + " * " + q + " = " + n);

        System.out.print("Enter e : ");
        int e = in.nextInt();

        BigInteger d = inverse(p.intValue(), q.intValue(), e);

        System.out.println("The decrypted mesaage for \" " + message + " \" is : " + decrypt(message, n, d.intValue()));

    }
}
