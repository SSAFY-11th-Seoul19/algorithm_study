import java.util.*;

public class Main {
   public static void main(String args[]) {
       int N, beg, end;
       int[] chemicals = new int [100000];

       Scanner in = new Scanner(System.in);

       N = in.nextInt();
       for(int i =  0; i < N; i++){
           chemicals[i] =  in.nextInt();
       }

       beg = 0;
       end = N-1;

       int ans = Integer.MAX_VALUE;

       while(beg < end) {
           int cur = chemicals[beg] + chemicals[end];

           if(Math.abs(ans) > Math.abs(cur)) ans = cur;

           if(cur < 0) beg++;
           else if (cur > 0) end--;
           else break;
       }
       System.out.println(ans);
   }
}
