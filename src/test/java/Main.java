import dev.vedcodee.it.ThreadManager;
import dev.vedcodee.it.enums.Mode;

import javax.swing.plaf.SliderUI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {


    public static void main(String[] args) {
        ThreadManager threadManager = new ThreadManager();
        
        List<String> list = new CopyOnWriteArrayList<>();


        long time = 0L;
        int test = 2;
        Runnable baseTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Start task: " + Thread.currentThread().getName());
                for (int i = 0; i < 1000; i++) {
                    list.add("a");
                    for (int j = 0; j < 10; j++) {
                        list.remove("a");
                    }
                }
                for (String s : list) {
                    list.remove("cacca");
                }
                System.out.println("End task: " + Thread.currentThread().getName());
            }
        };



        if(test == -1) return;

        if(test == 1) {
            time = System.currentTimeMillis();
            for (int i = 0; i < 100; i++)
                threadManager.addThread(baseTask, Mode.ASYNC);
            System.out.println("Finished in " + (System.currentTimeMillis() - time) + "ms");
        }

        if(test == 2) {
            time = System.currentTimeMillis();
            for (int i = 0; i < 100; i++)
                threadManager.addThread(baseTask, Mode.ASYNC);
            System.out.println("Finished in " + (System.currentTimeMillis() - time) + "ms");
        }

    }




}
