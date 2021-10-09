import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon_14502 {
    static int N, M, result;
    static int[][] lab, copyLab;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static List<Pair> area0 = new LinkedList<Pair>();
    static List<Pair> virus = new LinkedList<Pair>();

    static int countArea(){
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(copyLab[i][j] == 0){ cnt++; }
            }
        }
        return cnt;
    }

    static void setCopyLab(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                copyLab[i][j] = lab[i][j];
            }
        }
    }

    static void infectionVirus(){
        setCopyLab();
        boolean[][] visited = new boolean[N+1][M+1];
        Queue<Pair> que = new LinkedList<>();

        for(int i=0; i<virus.size(); i++) {
            que.add(new Pair(virus.get(i).getX(), virus.get(i).getY()));
            visited[virus.get(i).getX()][virus.get(i).getY()] = true;
        }

        while (!que.isEmpty()){
            int x = que.peek().getX();
            int y = que.poll().getY();

            for(int j=0; j<4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M){ continue; }

                if(!visited[nx][ny] && copyLab[nx][ny] == 0) {
                    que.add(new Pair(nx, ny));
                    copyLab[nx][ny] = 2;
                    visited[nx][ny] = true;
                }
            }
        }

        result = Math.max(result, countArea());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N+1][M+1];
        copyLab = new int[N+1][M+1];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 0) {
                    area0.add(new Pair(i, j));
                }else if(lab[i][j] == 2){
                    virus.add(new Pair(i, j));
                }
            }
        }

        // 3중 for문으로 벽 세우기
        for(int i=0; i<area0.size(); i++){
            lab[area0.get(i).getX()][area0.get(i).getY()] = 1;
            for(int j=i+1; j<area0.size(); j++){
                lab[area0.get(j).getX()][area0.get(j).getY()] = 1;
                for(int z=j+1; z<area0.size(); z++){
                    lab[area0.get(z).getX()][area0.get(z).getY()] = 1;
                    infectionVirus();
                    lab[area0.get(z).getX()][area0.get(z).getY()] = 0;
                }
                lab[area0.get(j).getX()][area0.get(j).getY()] = 0;
            }
            lab[area0.get(i).getX()][area0.get(i).getY()] = 0;
        }

        System.out.println(result);
    }
}

class Pair{
    int x,y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
}