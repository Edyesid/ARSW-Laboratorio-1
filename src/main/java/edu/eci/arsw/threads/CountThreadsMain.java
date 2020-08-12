/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
        CountThread contador1 = new CountThread(0,99);
        contador1.run();
        
        CountThread contador2 = new CountThread(99,199);
        contador2.run();
        
        CountThread contador3 = new CountThread(200,299);
        contador3.run();
    }
}
