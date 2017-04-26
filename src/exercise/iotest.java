package exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zfh on 17-3-26.
 */
public class iotest {
    static int quickpow(int a,int n,int k){
        int b = 1;
        while (n>0)
        {
            if ((n & 1)!=0)
                b = (b*a)%k;
            n = n >> 1 ;
            a = (a*a)%k;
        }
        return b;
    }

    public static void main(String[] args) {
//        String s1 = "Programming";
//        String s2 = new String("Programming");
//        String s3 = "Program";
//        String s4 = "ming";
//        String s5 = "Program" + "ming";
//        String s6 = s3 + s4;
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s5);
//        System.out.println(s1 == s6);
//        System.out.println(s1 == s6.intern());
//        System.out.println(s2 == s2.intern());
//        System.out.println(quickpow(5,7,7));
//        System.out.println(Math.pow(5,7)%7);

//        Scanner sc = new Scanner(System.in);
//        String str = sc.next();
//        int n = sc.nextInt();
//        long[][] dp = new long[str.length() + 1][]; //不用long的话通过率只能为90%
//        for (int i = 0; i <= str.length(); i++) {
//            dp[i] = new long[n];
//        }
//        dp[0][0] = 1;
//        for (int i = 1; i <= str.length(); i++) {
//            char c = str.charAt(i - 1);
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < 10; k++) {
//                    if (c == 'X' || c == '0' + k) {
//                        dp[i][(j * 10 + k) % n] += dp[i - 1][j];
//                    }
//                }
//            }
//        }
//        System.out.println(dp[str.length()][0]);
        int n=10;
        System.out.println(Math.pow(2,n+1));
        System.out.println(combination(n*(n+1)/2, n)+1+3*n-(long)Math.pow(2,n+1));
        System.out.println(combination(2*n,n)/(n+1));
    }

    static long combination(int n, int m){
        long result=1;
        long tmp=factorial(m);
        while (m-->0){
            result*=n--;
        }
        return result/tmp;
    }

    static long factorial(int n){
        long result=1;
        while (n>1){
            result*=n--;
        }
        return result;
    }
}
