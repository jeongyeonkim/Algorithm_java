import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14188KB 144ms
// 1. 이분 탐색
// 2. 가로나 세로로 자르는 횟수 합은 k
// 3. 총 나눠진 갯수의 합 (가로 + 1) * (세로 + 1)
public class Baekjoon_20444 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long n ,k;
    public static void main(String[] args) throws IOException {
          st = new StringTokenizer(br.readLine());
          n = Long.parseLong(st.nextToken());
          k = Long.parseLong(st.nextToken());

          long left = 0;
          long right = n/2;

          while (left <= right){
              long mid = (left + right) / 2;

              long sum = (mid + 1) * (n - mid  +1);
              if(sum == k){
                  System.out.println("YES");
                  return;
              }

              if(sum > k){
                  right = mid - 1;
              }else{
                  left = mid + 1;
              }
          }

        System.out.println("NO");
    }
}
