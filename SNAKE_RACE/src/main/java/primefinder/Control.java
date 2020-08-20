/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primefinder;

import java.util.Scanner;

/**
 *
 */
public class Control extends Thread {
    
    private final static int NTHREADS = 3;
    private final static int MAXVALUE = 30000000;
    private final static int TMILISECONDS = 5000;

    private final int NDATA = MAXVALUE / NTHREADS;

    private PrimeFinderThread pft[];
    
    private Control() {
        super();
        this.pft = new  PrimeFinderThread[NTHREADS];

        int i;
        for(i = 0;i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i*NDATA, (i+1)*NDATA);
            pft[i] = elem;
        }
        pft[i] = new PrimeFinderThread(i*NDATA, MAXVALUE + 1);
    }
    
    public static Control newControl() {
        return new Control();
    }

    @Override
    public void run() {
        for(int i = 0;i < NTHREADS;i++ ) {
            pft[i].start();
        }
        
        while (true) {
        	
        	try {
        		sleep(TMILISECONDS);
        		
        	}
        	catch(InterruptedException e){
        		e.printStackTrace();
        	}
            for(int i = 0;i < NTHREADS;i++ ) {
                    pft[i].detener();
                    System.out.println("Cantidad de numeros primos: " + pft[i].getPrimes().size());
            }
        	System.out.println("");
        	System.out.println("Presione Enter para continuar");
        		
       		Scanner scan = new Scanner(System.in);
       		String llave = scan.nextLine();
       		while(!llave.isEmpty()) {
        			System.out.println("No reconocido");
        			System.out.println("Presione Enter para continuar");
        			scan = new Scanner(System.in);
        			llave = scan.nextLine();
      		}
       		for(int i = 0;i < NTHREADS;i++ ) {
                   pft[i].reanudar();
                }
        	
        }
        
    }
    
}
