// 현재 위치를 index로 하여 위치에 도달하기까지 걸린 시간을 배열에 저장
// 현재 위치를 q에 넣고, q에 저장된 값을 빼서 현재 위치 갱신
// 현재 위치에 -1,+1,*2한 index를 다시 큐에 저장
// 큐가 비거나 목적지에 최단으로 도달하기까지 거리 배열에 시간을 저장한다.

import java.io.*;
import java.util.*;

public class Main {
	public static int MAX = 100000;
	public static int[] distance = new int[MAX + 1]; // 위치 == index, 시간 == value
	public static int[] move = { -1, 1, 2 }; // 움직일 수 있는 거리 (2 => *2)

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		bfs(n, k);
	}

	public static void bfs(int n, int k) {
		Deque<Integer> dq = new ArrayDeque<>();
		dq.addLast(n);
		distance[n] = 1;
		while (!(dq.isEmpty())) {
			int position = dq.pollFirst();
			if (position == k) {
				System.out.println(distance[position] - 1);
				break;
			}
			for (int i : move) {
				int idx = i == 2 ? position * i : position + i;
				if (idx >= 0 && idx <= MAX && distance[idx] == 0) {
					distance[idx] = distance[position] + 1;
					dq.addLast(idx);
				}
			}
		}
	}
}
