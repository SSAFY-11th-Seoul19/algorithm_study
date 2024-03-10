import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, min;
	static ArrayList<Integer> cranes = new ArrayList<>();
	static ArrayList<Integer> boxes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cranes.add(Integer.parseInt(st.nextToken()));
		}

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			boxes.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(cranes, Collections.reverseOrder()); // 정렬
		Collections.sort(boxes, Collections.reverseOrder());
		
		if (boxes.get(0) > cranes.get(0)) { // 아무런 박스도 들 수 없는 경우
			System.out.println(-1);
			return;
		}
		
		while (!boxes.isEmpty()) { // 박스가 빌 때까지
			int boxIdx = 0; // 박스의 index
			for (int craneIdx = 0; craneIdx < N;) { // 크레인만큼 돌며
				if (boxIdx == boxes.size()) { // 박스를 다 들면 끝
					break;
				}
				if (cranes.get(craneIdx) >= boxes.get(boxIdx)) { // 들 수 있다면
					boxes.remove(boxIdx); // 박스 제거
					craneIdx++; // 다음 크레인으로 넘어가기 => 제일 큰 크레인부터 찾아준 것
					continue; // 제거했으므로 박스idx++ X
				}
				boxIdx++; // 너무 무거워서 못 들었으니 다음 박스로 이동
			}
			min++;
		}
		System.out.println(min);
	}
}
