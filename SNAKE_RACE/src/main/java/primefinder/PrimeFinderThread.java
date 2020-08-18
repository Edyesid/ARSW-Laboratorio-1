package primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	public boolean parar=false;
	
	private List<Integer> primes;
	
	public PrimeFinderThread(int a, int b) {
		super();
        this.primes = new LinkedList<Integer>();
		this.a = a;
		this.b = b;
		
	}

        @Override
	public void run(){
        	
            for (int i= a;i < b;i++){
            	synchronized(this) {
            		if (parar) {
            			try {
            				wait();
            			}
            			catch (InterruptedException e) {
            				e.printStackTrace();
            			}
            		}
            	}
                if (isPrime(i)){
                    primes.add(i);
                    //System.out.println(i);
                }
            }
	}
	
	boolean isPrime(int n) {
	    boolean ans;
            if (n > 2) { 
                ans = n%2 != 0;
                for(int i = 3;ans && i*i <= n; i+=2 ) {
                    ans = n % i != 0;
                }
            } else {
                ans = n == 2;
            }
	    return ans;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	public void detener() {
		parar = true;
	}
	
	public void reanudar() {
		parar = false;
		synchronized(this) {
			notifyAll();
		}
	}
	
	
}
