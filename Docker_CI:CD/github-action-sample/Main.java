import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<int[]> sticks = new ArrayList<>();


        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            sticks.add(new int[]{x, h});
        }

        sticks.sort(Comparator.comparingInt(a -> a[0]));

        int max_start_idx = 0;
        int max_end_idx = 0;
        int max_h = 0;

        for(int i = 0; i < N; i++) {
            if(sticks.get(i)[1] > max_h) {
                max_h = sticks.get(i)[1];
                max_start_idx = i;
                max_end_idx = i;
            } else if(sticks.get(i)[1] == max_h) {
                max_end_idx = i;
            }
        }

        int answer = 0;

        int left_x = sticks.get(0)[0];
        int left_h = sticks.get(0)[1];
        int right_x = sticks.get(N - 1)[0];
        int right_h = sticks.get(N - 1)[1];

        for(int i = 1; i <= max_start_idx; i++) {
            if(sticks.get(i)[1] > left_h) {
                answer += (sticks.get(i)[0] - left_x) * left_h;
                left_x = sticks.get(i)[0];
                left_h = sticks.get(i)[1];
            }
        }

        for(int i = N - 2; i >= max_end_idx; i--) {
            if(sticks.get(i)[1] > right_h) {
                answer += (right_x - sticks.get(i)[0]) * right_h;
                right_x = sticks.get(i)[0];
                right_h = sticks.get(i)[1];
            }
        }

        answer += max_h * (sticks.get(max_end_idx)[0] - sticks.get(max_start_idx)[0] + 1);
        System.out.println(answer);
    }
}
