import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14472KB 152ms
// 1. 탐색 위치의 좌우 최대 값 저장
// 2. 두 최대 값 중 작은 높이 까지만 물을 채울 수 있음
// 3. 그 작은 높이가 현재 높이보다 작으면 물 못채움
public class Baekjoon_14719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int H, W, result;
    static int[] water;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        H  = Integer.parseInt(st.nextToken());
        W  = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        water = new int[W+1];

        for(int  i=0; i<W; i++){
            water[i]  = Integer.parseInt(st.nextToken());
        }

        int leftH, rightH;
        for(int i=1; i<W-1; i++){
            leftH = 0;
            rightH = 0;

            for(int l=i-1; l>=0; l--){
                leftH = Math.max(leftH, water[l]);
            }

            for(int r=i+1; r<W; r++){
                rightH = Math.max(rightH, water[r]);
            }

            rightH = Math.min(rightH, leftH);

            if(rightH - water[i] > 0) {
                result += rightH - water[i];
            }
        }

        System.out.println(result);
    }
}
