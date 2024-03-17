import java.util.*;
class Solution {
    List<int[]> problemList;
    int[][] timeArr;
    int maxAlp, maxCop;
    
    public int solution(int alp, int cop, int[][] problems) {
        int rLen = problems.length;
        
        // 기본 알고&코딩 공부를 추가하여 문제 리스트 생성
        problemList = new ArrayList<>(rLen+2);
        problemList.add(new int[]{0, 0, 1, 0, 1});
        problemList.add(new int[]{0, 0, 0, 1, 1});
        for (int[] problem : problems) {
            problemList.add(problem);
            
            // 풀어야하는 최대 알고&코딩 수치 저장
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        // [알고, 코딩] => 최소시간
        // 최소 시간을 저장할 배열 초기화
        timeArr = new int[Math.max(alp, maxAlp)+1][Math.max(cop, maxCop)+1];
        for (int[] line : timeArr) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        pq.add(new int[]{0, alp, cop}); //[time, currAlp, currCop]
        timeArr[alp][cop] = 0;
        
        int answer = 0;
        while(!pq.isEmpty()) {
            int[] target = pq.poll();
            
            // 저장한 시간보다 클 경우 continue;
            if (timeArr[target[1]][target[2]] < target[0]) {
                continue;
            }
            
            // 최대 알고&코딩력 이상으로 능력치가 올라가면 종료
            if (target[1] >= maxAlp && target[2] >= maxCop) {
                answer = target[0];
                break;
            }
            
            // 현재 능력치에서 문제를 하나씩 해결하며 pq에 추가
            for (int[] problem : problemList) {  // [0:alp_req, 1:cop_req, 2:alp_rwd, 3:cop_rwd, 4:cost]
                
                // 문제 해결 하한선보다 현재 능력치가 낮다면 continue;
                if (target[1] < problem[0] || target[2] < problem[1]) {
                    continue;
                }
                
                // 문제 해결 후 다음 능력치 계산
                int nTime = target[0] + problem[4];
                int nAlp = Math.min(target[1] + problem[2], maxAlp);
                int nCop = Math.min(target[2] + problem[3], maxCop);
                
                // 해당 능력치에 도달하기 까지 걸린 시간이 최소라면 pq에 추가
                if (nTime < timeArr[nAlp][nCop]) {
                    timeArr[nAlp][nCop] = nTime;
                    pq.add(new int[]{nTime, nAlp, nCop});
                }
            }
        }
        
        return answer;
    }
}
