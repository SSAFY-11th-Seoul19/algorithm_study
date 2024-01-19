class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        
        int n = board.length;
        int [] basket = new int[moves.length+2];
        int basketIdx = 2;
        int cnt = 0;
        for (int a =0; a<moves.length;a++) {
            //moves 값을 하나씩 가져온다
            //그 값(m)이 board의 col이 된다.
            int m = moves[a]-1;
            int doll=0;
            for (int i = 0; i<n; i++) {
                //m열의 데이터를 0행부터 n-1 행까지 본다.
                if (board[i][m]!= 0){
                    doll = board[i][m];
                    board[i][m]= 0;
                    break;
                } 
                }
        if (doll != 0) {
            basket[basketIdx]=doll;
            basketIdx++;
        
        if (basket[basketIdx-1]==basket[basketIdx-2]) {
            basketIdx -=2;
            cnt++;
        }
         }
        }
    
    answer = cnt*2;
    return answer;
    }
}



// 화면 NxN 위에는 크레인, 오른쪽엔 바구니
// 각 격자당 인형이 있다.
// 크레인을 좌우로 움직여 가장 위에 있는 인형 집을 수 있다. stack
// 같은 모양 인형 두개가 연속하면 터지면서 사라진다.
// 인형이 없는 곳에서 크레인이 작동하면 아무일도 일어나지 않음
// 바구니 크기는 제약 없음
// 2차원 배열 board, 크레인 작동시킨 위치가 담긴 배열이 매개변수
// 5<= N <= 30, int moves[]=new int [] 크기는 1~1000
// moves[]에 들어가는 값은 1<=x<=N이다.
// 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return
// 사라진 인형의 갯수 => 두개가 같아서 사라진 횟수*2
// 인형의 종류 1~100개 0은 비어있음

// 받는 정보는 board에 대한 데이터, 크레인이 움직이는 위치
// 바구니 어레이는 moves의 크기와 같게 설정
// moves에 있는 값을 하나씩 받아오면
// 그 열에 있는 가장 위에 있는 행(0~N)의 값을 0으로 만들고
// 그 값을 arr 바구니에 넣는다.
// 그 열의 5번째 
// 바구니에 넣은 값이 넣은 인덱스의 앞과 비교하여 같으면 
// 그 인덱스와 그 전 인덱스의 값을 초기화 시킨다.
