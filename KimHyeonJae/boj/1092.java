import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		LinkedList<Integer> cranes = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			cranes.add(Integer.parseInt(st.nextToken()));
		}
		cranes.sort((o1, o2) -> {
			if (o1 < o2) {
				return -1;
			}
			if (o1 == o2) {
				return 0;
			}
			return 1;
		});

		int m = Integer.parseInt(br.readLine());
		LinkedList<Integer> boxes = new LinkedList<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < m; i++) {
			boxes.add(Integer.parseInt(st.nextToken()));
		}
		boxes.sort((o1, o2) -> {
			if (o1 < o2) {
				return -1;
			}

			if (o1 == o2) {
				return 0;
			}
			return 1;
		});

		if (boxes.get(boxes.size() - 1) > cranes.get(cranes.size() - 1)) {
			System.out.println(-1);
			return;
		}

		int time = 0;
		while (!boxes.isEmpty()) {
			for (int craneIdx = cranes.size() - 1; craneIdx >= 0; craneIdx--) {
				int crane = cranes.get(craneIdx);
				int left = 0;
				int right = boxes.size() - 1;

				while (left <= right) {
					int mid = (left + right) / 2;
					if (boxes.get(mid) <= crane) {
						left = mid + 1;
						continue;
					}
					right = mid - 1;
				}
				if (right == -1) {
					cranes.remove(craneIdx);
					continue;
				}
				boxes.remove(right);
			}
			time += 1;
		}

		System.out.println(time);
	}
}
