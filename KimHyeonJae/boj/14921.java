import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력부와 용액을 담는 변수 liquids
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] liquids = new int[number];
        for (int idx = 0; idx < number; idx++) {
            liquids[idx] = Integer.parseInt(stringTokenizer.nextToken());
        }
        // 용액 정렬
        Arrays.sort(liquids);
        int left = 0, right = number - 1;
        int answer = Integer.MAX_VALUE;

        // 투포인터 이용한
        while (left < right) {
            if (Math.abs(liquids[left] + liquids[right]) < Math.abs(answer)) {
                answer = liquids[left] + liquids[right];
            }
            // answer 이 0 일 경우 더 돌 필요가 없음
            if (answer == 0) {
                break;
            }
            if (liquids[left] + liquids[right] < 0) {
                left += 1;
                continue;
            }
            right -= 1;
        }
        System.out.println(answer);
    }
}
