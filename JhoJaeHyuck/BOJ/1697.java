import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        Queue<Pair> q = new LinkedList<>();
        int[] visited = new int[100001];

        int ans = -1;

        q.add(new Pair(N, 0));
        visited[N] = 1;

        while (!q.isEmpty()) {
            Pair current = q.poll();

            if (current.first == K) {
                ans = current.second;
                break;
            }

            if (current.first <= 99999) {
                if (visited[current.first + 1] == 0) {
                    q.add(new Pair(current.first + 1, current.second + 1));
                    visited[current.first + 1] = 1;
                }
            }

            if (current.first >= 1) {
                if (visited[current.first - 1] == 0) {
                    q.add(new Pair(current.first - 1, current.second + 1));
                    visited[current.first - 1] = 1;
                }
            }

            if (current.first <= 50000) {
                if (visited[current.first * 2] == 0) {
                    q.add(new Pair(current.first * 2, current.second + 1));
                    visited[current.first * 2] = 1;
                }
            }
        }

        System.out.println(ans);
    }

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
