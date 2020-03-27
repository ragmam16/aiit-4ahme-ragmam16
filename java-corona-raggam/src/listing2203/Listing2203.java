/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listing2203;

/**
 *
 * @author maxim
 */
public class Listing2203 extends Thread{
    int cnt = 0; 
    public void run(){
        Listing2203 th = new Listing2203();
        while(true){
            if(isInterrupted()){
                break;
            }
            printLine(cnt++);
            System.out.println(th.isAlive());
        }
    }
    private void printLine(int cnt){
        System.out.print(cnt);
        for (int i = 0; i < 30; ++i){
          System.out.print(i == cnt % 30 ? "* " : ". ");
        }
        System.out.println();
        try{
          Thread.sleep(100);
        }catch(InterruptedException ie){
          interrupt();
        }
    }
    
   
    
    public static void main(String[] args){
    Listing2203 th = new Listing2203();{
      th.start();
      try {
        Thread.sleep(2000);
      }catch(InterruptedException ie){
          System.out.println("Fehler!!");
      }
      th.interrupt();
    }
  }
}


