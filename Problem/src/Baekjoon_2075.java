import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 218024KB 900ms
// 1. 값이 10억까지라서 queue에 1500개의 수의 값을 다 들고 있으면 메모리 초과
// 2. N 번째 큰 수 만 구하는 것이기 때문에 queue에는 n 개 초과의 숫자를 갖고있을 필요 x
// 3. 가장 작은 수보다 크면 queue에 있는거 1개 뺴고 넣기
// 4. queue 크기가 n 보다 작으면 그냥 넣기
public class Baekjoon_2075 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int n;
        long inputNumber;
        PriorityQueue<Long> que = new PriorityQueue<>();
        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                inputNumber = Long.parseLong(st.nextToken());

                if(que.size() < n){
                    que.add(inputNumber);
                }else if(que.peek() < inputNumber){
                    que.remove();
                    que.add(inputNumber);
                }
            }
        }

        System.out.println(que.peek());
    }
}
