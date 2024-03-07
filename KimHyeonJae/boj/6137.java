import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(br.readLine());
        }
        String origin = sb.toString();

        int left = 0, right = length - 1;
        char[] origins = origin.toCharArray();
        sb = new StringBuilder();
        while (left <= right) {
            if (origins[left] < origins[right]) {
                sb.append(origins[left]);
                left += 1;
                continue;
            }
            if (origins[left] > origins[right]) {
                sb.append(origins[right]);
                right -= 1;
                continue;
            }

            int tempLeft = left, tempRight = right;
            boolean isLeft = true;
            while (tempLeft < tempRight) {
                if (origins[tempLeft] < origins[tempRight]) {
                    break;
                }
                if (origins[tempLeft] > origins[tempRight]) {
                    isLeft = false;
                    break;
                }
                tempLeft += 1;
                tempRight -= 1;
            }
            if (isLeft) {
                sb.append(origins[left]);
                left += 1;
                continue;
            }
            sb.append(origins[right]);
            right -= 1;
        }

        String answer = sb.toString();


        for (int i = 0; i < length; i++) {
            if (i!=0 && i % 80 == 0) {
                System.out.println();
            }
            System.out.print(answer.charAt(i));
        }
    }
}
