import java.util.ArrayList;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board[0].length;
        int idx, craneObj;
        ArrayList<Integer> stack = new ArrayList<Integer>();
        
        for(int i=0; i<moves.length; i++) {
        	//몇번째 위치에서 인형을 꺼내는지 저장해 놓을 변수
        	int location = moves[i]-1;
        	//크레인이 몇번째 idx위치에 있는지 저장해놓을 변수
        	idx = 0; craneObj = 0; 
        	//크레인이 성공적으로 인형을 잡았을 때 잡은 인형의 종류
        	
        	while(true){
        		// 크레인 위치에 인형이 있을 경우
        		if(board[idx][location]!=0){
        			//인형의 종류를 저장
        			craneObj = board[idx][location];
        			//stack size 선언
        			int sSize = stack.size();
        			if(!stack.isEmpty()&&(stack.get(sSize-1)==craneObj)){
        				stack.remove(sSize-1);
        				answer+=2;
        			}
        			else {
        				stack.add(craneObj);
        			}
        			board[idx][location] = 0;
        			break;
        		}
        		
        		if(idx==N-1) {
        			break;
        		}
        		
        		idx++;
        	}
        }
        return answer;
    }
}
