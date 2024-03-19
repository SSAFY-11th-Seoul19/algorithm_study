import java.util.*;

class Solution {
    final int ALP_REQ = 0;
    final int COP_REQ = 1;
    final int ALP_RWD = 2;
    final int COP_RWD = 3;
    final int COST = 4;
    int[][] minCost;
    int N;
    int maxAlp = 0;
    int maxCop = 0;

    class Problem implements Comparable<Problem>{
        int alp;
        int cop;
        int cost;
        int max;

        public Problem(int alp, int cop, int cost){
            this.alp = alp;
            this.cop = cop;
            this.cost = cost;
            this.max = Math.max(alp,cop);
        }

        public int compareTo(Problem o){
            if(this.cost == o.cost){
                return o.max - this.max;
            }

            return this.cost - o.cost;
        }
    }

    public int solution(int alp, int cop, int[][] problems) {
        N = problems.length;
        for(int i=0;i<N;i++){
            maxAlp = Math.max(maxAlp,problems[i][ALP_REQ]);
            maxCop = Math.max(maxCop,problems[i][COP_REQ]);
        }

        minCost = new int[1501][1501];
        for(int i=0;i<=1500;i++){
            for(int j=0;j<=1500;j++){
                minCost[i][j] = Integer.MAX_VALUE;
            }
        }

        return findMin(alp,cop,problems);
    }

    public int findMin(int alp, int cop, int[][] problems){
        PriorityQueue<Problem> pq = new PriorityQueue<>();
        pq.add(new Problem(alp,cop,0));

        while(!pq.isEmpty()){
            Problem now = pq.poll();

            if(now.alp >= maxAlp && now.cop >= maxCop){
                return now.cost;
            }

            // 알고력 +1
            if(minCost[now.alp+1][now.cop] > now.cost+1){
                minCost[now.alp+1][now.cop] = now.cost+1;
                pq.add(new Problem(now.alp+1,now.cop,minCost[now.alp+1][now.cop]));
            }

            // 코딩력 +1
            if(minCost[now.alp][now.cop+1] > now.cost+1){
                minCost[now.alp][now.cop+1] = now.cost+1;
                pq.add(new Problem(now.alp,now.cop+1,minCost[now.alp][now.cop+1]));
            }

            for(int[] problem:problems){
                if(now.alp < problem[ALP_REQ] || now.cop < problem[COP_REQ]){
                    continue;
                }

                int changedAlp = now.alp + problem[ALP_RWD];
                int changedCop = now.cop + problem[COP_RWD];

                if(minCost[changedAlp][changedCop] <= now.cost+problem[COST]){
                    continue;
                }

                minCost[changedAlp][changedCop] = now.cost+problem[COST];
                pq.add(new Problem(changedAlp,changedCop, minCost[changedAlp][changedCop]));
            }
        }

        return -1;
    }
}