import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 16848KB 260ms
// 1. 브루트포스
// 2. 각 집에서 각각의 치킨집 거리 저장 >> chickenDistance
// 3. 재귀사용해서 M개의 치킨집 선택
// 4. 기존에 계산했던 집에서의 선택된 치킨 집 중에서 가까운 곳을 합산 거리에 넣음
// 5. 합산 거리가 지금까지의 결과 값보다 크면 바로 return
public class Baekjoon_15686 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, result, tempValue;
    static int[] selectedStore;
    static ArrayList<Info> chickenStore = new ArrayList<>();
    static ArrayList<Info> house = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> chickenDistance = new ArrayList<ArrayList<Integer>>();

    static void choiceStoreAndMin(int index, int cnt){
        if(cnt == M){
            int sum = 0;
            for(int i=0; i<house.size(); i++){
                int minDistance = Integer.MAX_VALUE;
                for(int j=0; j<M; j++){
                    minDistance = Math.min(minDistance, chickenDistance.get(i).get(selectedStore[j]));
                }
                sum += minDistance;
                if(sum > result){ return; }
            }

            result = Math.min(result, sum);
            return;
        }

        for(int i=index; i<chickenStore.size(); i++){
            selectedStore[cnt] = i;
            choiceStoreAndMin(i+1, cnt+1);
        }
    }

    static int calculationDistance(int r1, int c1, int r2, int c2){
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        result =  Integer.MAX_VALUE;
        selectedStore = new int[M+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                tempValue = Integer.parseInt(st.nextToken());
                if(tempValue == 1){
                    house.add(new Info(i, j));
                }else if(tempValue == 2){
                    chickenStore.add(new Info(i, j));
                }
            }
        }

        for(int i=0; i<house.size(); i++){
            ArrayList<Integer> tempDis = new ArrayList<>();
            for(int j=0; j<chickenStore.size(); j++){
                int  resultDis = calculationDistance(house.get(i).getX(), house.get(i).getY(), chickenStore.get(j).getX(), chickenStore.get(j).getY());
                tempDis.add(resultDis);
            }
            chickenDistance.add(tempDis);
        }

        choiceStoreAndMin(0, 0);

        System.out.println(result);
    }
}

class Info{
    int x, y;
    public Info(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){  return x; }
    public int getY(){  return y; }
}
