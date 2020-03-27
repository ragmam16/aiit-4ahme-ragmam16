/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlasseThread222;

/**
 *
 * @author maxim
 */
public class Listing2201 {
    public static void main(String[] args) {
        MyThread2201 t = new MyThread2201();
        t.start();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException ie){
            System.out.println("Fehler!");
        }
        t.stop();
    }
}
