import java.util.*;

public class Main {
    public static void main(String args[]) {
        int N, beg, end, nextBeg, nextEnd;
        StringBuilder str = new StringBuilder("");
        StringBuilder buffer = new StringBuilder("");

        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        for (int i = 0; i < N; i++) {
           str.append(in.next().charAt(0));
        }

        beg = 0;
        end = N-1;

        boolean isLeft =true;
        while(beg <= end){
            if(str.charAt(beg) < str.charAt(end))
                buffer.append(str.charAt(beg++));
            else if(str.charAt(beg) > str.charAt(end))
                buffer.append(str.charAt(end--));
            else{
                int l = beg+1 , r=end-1;

                while(l <= r){
                    if(str.charAt(l) < str.charAt(r)) {
                        isLeft = true;
                        break;
                    }
                    else if(str.charAt(l) > str.charAt(r)){
                        isLeft = false;
                        break;
                    }
                    else{
                        l++;
                        r--;
                    }
                }

                if(isLeft)
                    buffer.append(str.charAt(beg++));
                else
                    buffer.append(str.charAt(end--));

            }
            if(buffer.length() == 80){
                System.out.println(buffer);
                buffer.setLength(0);
            }
        }
        System.out.println(buffer);
    }
}
