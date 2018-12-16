import java.util.Scanner;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.File;
public class Heap {

	private String[] data;
	private int nextIndex = 0;

	public Heap(int max) {
		data = new String[max];
	}

	public int getParentIndex(int childIndex){
		return(childIndex-1)/2;
	}

	public int getLeftChildIndex(int parentIndex) {
	    return (2*parentIndex+1);
	}

	public int getRightChildIndex(int parentIndex) {
		return (2*parentIndex+2);
	}

	public void removeMin() {
        int dataLength = data.length;
		if (isEmpty())
              throw new NullPointerException("Heap is empty");
        else {
              data[0] = data[dataLength - 1];
              dataLength--;
              if (dataLength> 0)
                    moveDown(0);
        }
	}

	private void moveDown(int nodeIndex) {
        int leftChildIndex;
        int rightChildIndex;
        int minIndex;
        String tmp;
        leftChildIndex = getLeftChildIndex(nodeIndex);
        rightChildIndex = getRightChildIndex(nodeIndex);
        if (rightChildIndex >= data.length) {
              if (leftChildIndex >= data.length){
                    return;
              }
              else{
                    minIndex = leftChildIndex;
              }
        }
        else {
              if (data[leftChildIndex].compareTo(data[rightChildIndex])<0)
                    minIndex = leftChildIndex;
              else
                    minIndex = rightChildIndex;
        }
        if (data[nodeIndex].compareTo(data[minIndex])>0 ) {
              tmp = data[minIndex];
              data[minIndex] = data[nodeIndex];
              data[nodeIndex] = tmp;
              moveDown(minIndex);
        }
  }

	public boolean isEmpty() {
	    if(nextIndex==0){
	    	return true;
	    }
	    else{
	    	return false;
	    }
	}

	public void add(String s) {
	    data[nextIndex] = s;

	    int currIndex = nextIndex;
	    int parentIndex = getParentIndex(currIndex);
	    while (parentIndex >= 0 && (data[currIndex].compareTo(data[parentIndex]) < 0)) {
	      String tmp = data[parentIndex];
	      data[parentIndex] = data[currIndex];
	      data[currIndex] = tmp;

	      currIndex = parentIndex;
	      parentIndex = getParentIndex(currIndex);
	    }

	    nextIndex++;
	  }

	public static void main(String[] args) throws FileNotFoundException {
	      Heap myHeap = new Heap(65000);
	      Scanner myScanner = new Scanner(new File("words.txt"));
	      while (myScanner.hasNextLine()) {
	          String currentWord = myScanner.nextLine();
	          myHeap.add(currentWord);
	      }
	      while (!myHeap.isEmpty()) {
	        System.out.println(myHeap.removeMin());
	      }
	  }
}
