import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14440KB 128ms
// 1. 좌회전으로 방향 먼저 바꾸고 시작
// 2. 좌측에 0인 구간이 있다면 이동하고 해당 for 문 탈출
// 3. 현재 자리에서 상하좌우에 갈 수 있는 곳이 있는지 체크 >> isAvailableCleaning
// 4. 있다면 2번 다시 진행
// 5. 없다면 현재 방향의 x(-1) 방향에 벽 있는지 없는 체크
//  5-1. 있다면 이동 후 2번 다시 진행
//  5-2. 없다면 exit
public class Baekjoon_14503 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int r, c, robotX, robotY, robotDir, result;
    static int[][] room;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};

    static boolean isAvailableCleaning(int x, int y){
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx <= 0 || ny <= 0 || nx >= r-1 || ny >= c-1 || room[nx][ny] != 0) { continue; }
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        room = new int[r+1][c+1];
        robotX = Integer.parseInt(st.nextToken()); robotY = Integer.parseInt(st.nextToken()); robotDir = Integer.parseInt(st.nextToken());
        for(int i=0; i<r; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<c; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = 1;
        room[robotX][robotY] = -1;

        while (true){
            for(int i=0; i<4; i++){
                robotDir = (robotDir + 3) % 4;
                int nx = robotX + dx[robotDir];
                int ny = robotY + dy[robotDir];
                if(nx < 1 || ny < 1 || nx >= r-1 || ny >= c-1 || room[nx][ny] != 0) { continue; }

                result++;
                room[nx][ny] = result;
                robotX = nx; robotY = ny;
                break;
            }

            if(!isAvailableCleaning(robotX, robotY)){
                if(room[robotX + (dx[robotDir] * (-1))][robotY + (dy[robotDir] * (-1))] != 1){
                    robotX = robotX + (dx[robotDir] * (-1));
                    robotY = robotY + (dy[robotDir] * (-1));
                }else {
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
