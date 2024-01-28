import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[] arr = new char[N];
        for (int i=0; i<N; i++) {
            arr[i] = sc.next().charAt(0);
        }

        int lp = 0, rp = N-1;
        List<Character> ans = new ArrayList<>();
        while (true) {
            if (lp == rp) {
                ans.add(arr[lp]);
                break;
            }
            if (arr[lp] < arr[rp]) {
                ans.add(arr[lp++]);
            } else if (arr[rp] < arr[lp]) {
                ans.add(arr[rp--]);
            } else {
                int start = lp, end = rp;
                boolean flag = true; // 타고타고 갔을 때 결국에 왼쪽 값이 더 작은지
                while(arr[start] == arr[end]) {
                    if (end>0) end--;
                    if (start<N-1) start++;
                    if(arr[start] > arr[end]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) ans.add(arr[lp++]);
                else ans.add(arr[rp--]);
            }
        }

        for (int i=0; i<ans.size(); i++) {
            if (i != 0 && i % 80 == 0) System.out.println();
            System.out.print(ans.get(i));
        }

    }
}
