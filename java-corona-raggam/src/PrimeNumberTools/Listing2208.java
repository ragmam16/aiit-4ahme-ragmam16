/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimeNumberTools;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author maxim
 */
public class Listing2208 {
    public static void main(String[] args){
        ThreadedPrimeNumberTools pt;
        BufferedReader in = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
        int num;
        try{
            while(true){
              System.out.print("Bitte eine Zahl eingeben: ");
              System.out.flush();
              num = (new Integer(in.readLine())).intValue();
              if(num == -1){
                 break;
              }
              pt = new ThreadedPrimeNumberTools();
              pt.printPrimeFactors(num);
            }
        } catch(IOException ioe){
            System.out.println("Fehler");
        }
  }
}
