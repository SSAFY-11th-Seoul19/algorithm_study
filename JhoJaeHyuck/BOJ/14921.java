import java.util.*;

public class Main
{
    static Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
	    int N = sc.nextInt();
	    
	    int[] arr = new int[N];
	    for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
	    
	    Arrays.sort(arr);
	    
	    int left = 0;
	    int right = N - 1;
	    int answer = 987654321;
	    
	    while (left < right) {
	        int temp = arr[right] + arr[left];
	        
	        if (Math.abs(temp) < Math.abs(answer)) answer = temp;
	        
	        if (temp > 0) right--;
	        else left++;
	    }
	    
	    System.out.println(answer);
	}
}
