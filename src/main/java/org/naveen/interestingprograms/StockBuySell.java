package org.naveen.interestingprograms;

public class StockBuySell {
	
	private static class Interval{
		int buy;
		int sell;
		public String toString(){
			return "[" + buy + ", " + sell + "]";
		}
	}
	
	public static void main(String[] args) {
		int[] a = {100, 80, 60, 50, 40, 35, 95};
		int c = 0, n = a.length;
		Interval[] intervals = new Interval[n/2+1];
		int i = 0;
		
		while (true){
			while(i < n-1 && a[i+1] <= a[i]) i++;
			if (i >= n-1) break;
			int min = i++;
			while (i < n && a[i] >= a[i-1]) i++;
			int max = i-1;
			
			Interval temp = new Interval();			
			intervals[c] = temp;
			temp.buy = a[min];
			temp.sell = a[max];
			
			c++;
		}
		
		if (c == 0) {
			System.out.println("No such possibility exist");
		}
		else {
			for (Interval intrvl : intervals){
				if (intrvl == null) break;
				System.out.print(intrvl);
			}
		}
	}

}
