package exercise;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by zfh on 17-4-22.
 */
public class migong {
    static final int N=10;
    static final int[][] mv=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    static class Pos{
        int x;
        int y;
        int layer;

        Pos(int x, int y, int layer){
            this.x=x;
            this.y=y;
            this.layer=layer;
        }
    }

    static int solution(char[][] map){
        Queue<Pos> queue=new ArrayDeque<>(N*N);

//        queue.add();
        while (!queue.isEmpty()){
            Pos curPos=queue.remove();
            for (int[] step : mv){
                int x=curPos.x+step[0];
                int y=curPos.y+step[1];
                int layer=curPos.layer+1;
                Pos nextPos=new Pos(x,y,layer);

            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        char[][] map=new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i]=sc.nextLine().toCharArray();
        }

    }
}
