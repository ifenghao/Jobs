package beauty;

import java.util.concurrent.TimeUnit;

/**
 * Created by zfh on 17-4-7.
 */
public class beauty {
    static void occupy() throws InterruptedException {
        while (true){
            long start=System.currentTimeMillis();
            while (System.currentTimeMillis()-start<=1000);
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        occupy();
    }
}
