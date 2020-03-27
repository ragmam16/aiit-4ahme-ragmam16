/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlasseThread222;

import java.util.Scanner;

/**
 *
 * @author maxim
 */
public class MyThread2201 extends Thread{
    
    public void run(){
        int i = 1;
        while(true){    
            System.out.print("Eingabe: ");
            Scanner eingabe = new Scanner(System.in);
            String s = eingabe.next();
            System.out.println(i+": " + s);
            i++;
        }
        
    }
}
