import java.util.*;
import java.io.*;

public class 1092 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> cranes = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        // 크레인이 들 수 있는 무게 역순으로 정렬
        Collections.sort(cranes, Collections.reverseOrder());
        // 박스들의 무게 역순으로 정렬
        Collections.sort(boxes, Collections.reverseOrder());

        // 크레인이 들 수 있는 최대 무게 < 가장 무거운 박스 무게 ---> 모든 박스를 다 옮길 수 없는 경우
        if(cranes.get(0) < boxes.get(0)) {
            System.out.println("-1");
            return;
        }

        int count = 0;
        while(boxes.size() != 0) {
            int craneIndex = 0; // 지금 박스를 옮길 크레인의 순서 인덱스

            for (int i = 0; i < boxes.size(); i++) { // 박스 처음부터 끝까지 탐색
                if(craneIndex == cranes.size()) break; // 모든 크레인들이 박스를 옮겼다면 break

                // craneIndex번째 크레인이 옮길 수 있는 박스를 만난 경우
                if(cranes.get(craneIndex) >= boxes.get(i)) {
                    craneIndex += 1; // 다음 크레인을 가리키도록 인덱스+1
                    boxes.remove(i); // 박스 제거
                    i--;
                }
            }

            count += 1;
        }

        System.out.println(count);

    }
}
