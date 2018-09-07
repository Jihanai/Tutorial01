import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Kuis3BSenin {
	static MinHeapComp minHeap = new MinHeapComp(); 
	static MaxHeapComp maxHeap = new MaxHeapComp(); 
	static PriorityQueue<Integer> minQueue = new PriorityQueue<>(minHeap); //untuk angka dari tengah hingga yang paling besar
	static PriorityQueue<Integer> maxQueue = new PriorityQueue<>(maxHeap); //untuk angka terkecil hingga angka tengah
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		String[] input = read.readLine().split(" ");
		for(int i = 0; i < n; i++){
			int angka = Integer.parseInt(input[i]);
			if(i == 0){
				minQueue.add(angka);
				System.out.print(angka);
				continue;
			}
			else if(angka < minQueue.peek()){
				maxQueue.add(angka);
			}
			else{
				minQueue.add(angka);
			}
			fixChaos();
			if(i % 2 != 0){
				int median = (minQueue.peek() + maxQueue.peek()) /2;
				System.out.print(" " + median);
			}
			else{
				int median = (minQueue.size() < maxQueue.size()) ? maxQueue.peek() : minQueue.peek();
				System.out.print(" " + median);
			}
		}
		System.out.println();
	}
	private static void fixChaos(){ 
		//if sizes of heaps differ by 2, then it's a chaos, since median must be the middle element
		if( Math.abs(maxQueue.size() - minQueue.size()) > 1){
	            //check which one is the culprit and take action by kicking out the root from culprit into victim
			if(maxQueue.size() > minQueue.size()){
				minQueue.add(maxQueue.poll());
	        }
			else{ maxQueue.add(minQueue.poll());}
	        }
	    
	}
	
}

class MinHeapComp implements Comparator<Integer>{
	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1, o2);
	}
}
class MaxHeapComp implements Comparator<Integer>{
	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o2, o1);
	}
}
