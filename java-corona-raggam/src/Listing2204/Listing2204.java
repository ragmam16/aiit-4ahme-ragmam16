/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listing2204;

/**
 *
 * @author maxim
 */
public class Listing2204 {
    public static void main(String[] args){
        B2204 b = new B2204();
        Thread t = new Thread(b);
        t.start();
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ie){
            System.out.println("Fehler");
        }
        t.interrupt();
   }
}
