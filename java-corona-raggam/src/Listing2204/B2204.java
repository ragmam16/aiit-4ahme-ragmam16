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
class B2204 extends A2204 implements Runnable{
    public void run(){
      int i = 0;
      while(true){
        if (Thread.interrupted()){
           break;
        }
        System.out.println(i++);
      }
   }
}
