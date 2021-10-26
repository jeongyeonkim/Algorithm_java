import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//  14216KB, 152KB
// 1. 1의 자리부터 소수 숫자만들면서 오름차순으로 가능한 숫자 저장
// 2. makeNum 재귀로 1번 조합
// 3. checkNum으로 소수 판별
public class Baekjoon_2023 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static ArrayList<Integer> result = new ArrayList<>();

    static boolean checkNum(int num){
        for(int i=2; i*i<=num; i++){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }

    static void makeNum(int num, int numLength){
        if(numLength == n){
            result.add(num);
            return;
        }

        for(int  i=1; i<=9; i+=2){
            if(checkNum(num*10  +  i)){
                makeNum(num*10 + i, numLength + 1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        makeNum(2, 1);
        makeNum(3, 1);
        makeNum(5, 1);
        makeNum(7, 1);

        for(int i=0; i<result.size(); i++){
            System.out.println(result.get(i));
        }
    }
}
