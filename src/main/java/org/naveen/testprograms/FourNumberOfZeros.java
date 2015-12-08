package org.naveen.testprograms;

public class FourNumberOfZeros {

	public static void main(String[] args) {
	    int N = 7;
	    int a = 0,b = 0,i;    
	    
	        if(N==1 || N ==2 || N==4)
	        {
	            System.out.println(2);
	            return;
	        }
	        int A[] = new int[N];
	        A[0] = 0;
	        for(i= 1 ; i < N ; i++)
	            A[i] = -1;
	        A[4%N] = 1;
	        int temp = 4%N;
	        for(i=2 ; i<=N+2 ; i++)
	        {
	            if(A[(temp*10 + 4) % N] != -1)
	            {
	                a = i;
	                b = A[(temp*10 + 4) % N] ;
	                break;
	            }
	            else
	            {
	                A[(temp*10 + 4) % N]  = i;
	                temp = (temp*10 + 4)% N;
	            }
	        }
	        System.out.println((2*a - b));
	}

}
