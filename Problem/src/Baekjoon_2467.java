import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 31392KB 436ms
// 1. 투 포인터
// 2. 양 끝에서 합산 >> 합산 시 long  사용 숫자 하나 값이 int 최대 값까지 가능해서
// 3. 가장 0에 가까운 값 들고있기
public class Baekjoon_2467 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, result1, result2;
    static long sum;
    static ArrayList<Integer> solution = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            solution.add(Integer.parseInt(st.nextToken()));
        }

        int left = 0;
        int right = N - 1;
        result1 = solution.get(0);
        result2 = solution.get(N-1);
        sum = Math.abs(result1 + result2);

        while (left < right){
            long sumTemp = solution.get(left) + solution.get(right);
            if(Math.abs(sumTemp) < sum){
                result1 = solution.get(left);
                result2 = solution.get(right);
                sum = Math.abs(sumTemp);
            }

            if(sum == 0){
                break;
            }

            if(sumTemp > 0){
                right--;
            }else{
                left++;
            }
        }

        System.out.println(result1 + " " + result2);
    }
}
