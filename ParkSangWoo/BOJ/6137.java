import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] chars = new char[N];

        for (int i = 0; i < N; i++) {
            chars[i] = br.readLine().charAt(0);
        }

        int left = 0;
        int right = N - 1;
        int count = 0;

        StringBuilder result = new StringBuilder();

        while (left <= right) {
            // 문자열 생성
            if (count == 80) {
                result.append("\n");
                count = 0;
            }

            // 문자열 생성 시 왼쪽과 오른쪽 비교
            if (chars[left] < chars[right]) {
                result.append(chars[left++]);
            } else if (chars[left] > chars[right]) {
                result.append(chars[right--]);
            } else {
                // 만약 왼쪽과 오른쪽이 같다면, 다음 비교까지 계속 이동
                int tempLeft = left;
                int tempRight = right;

                while (tempLeft < tempRight && chars[tempLeft] == chars[tempRight]) {
                    tempLeft++;
                    tempRight--;
                }

                if (tempLeft < tempRight && chars[tempLeft] < chars[tempRight]) {
                    result.append(chars[left++]);
                } else {
                    result.append(chars[right--]);
                }
            }

            count++;
        }

        System.out.println(result.toString());
    }
}
