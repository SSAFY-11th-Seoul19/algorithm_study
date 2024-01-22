import java.util.*;

public class Main
{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int N = sc.nextInt();

        char[] arr = new char[N];
        char[] ans = new char[N];

        for (int i = 0; i < N; i++) arr[i] = sc.next().charAt(0);

        int left = 0;
        int right = N-1;
        int idx = 0;

        while (left <= right) {
            if (arr[left] < arr[right]) ans[idx++] = arr[left++];
            else if (arr[right] < arr[left]) ans[idx++] = arr[right--];
            else {
                int tempR = right - 1;
                int tempL = left + 1;
                boolean allSame = true;

                while (tempL <= tempR) {
                    if (arr[tempL] < arr[tempR]) {
                        ans[idx++] = arr[left++];
                        allSame = false;
                        break;
                    }
                    else if (arr[tempL] > arr[tempR]) {
                        ans[idx++] = arr[right--];
                        allSame = false;
                        break;
                    }
                    tempL++;
                    tempR--;
                }

                if (allSame) ans[idx++] = arr[left++];
            }
        }

        System.out.print(ans[0]);
        for (int i = 1; i < N; i++) {
            if (i % 80 == 0) System.out.println();
            System.out.print(ans[i]);
        }
    }
}
