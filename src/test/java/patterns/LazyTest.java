package patterns;

import org.junit.Assert;
import org.junit.Test;
import patterns.singleton.Lazy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LazyTest {
    
    @Test
    public void generateSingletons() throws InterruptedException {

        Set<Lazy> set = Collections.newSetFromMap(new ConcurrentHashMap<>());

        class Filler extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 10_000_000; i++) {
                    set.add(Lazy.getInstance());
                }
            }
        }

        long start = System.nanoTime();

        List<Filler> fillers = new ArrayList<Filler>();

        for (int i = 0; i < 3; i++) {
            fillers.add(new Filler());
        }

        for (Filler filler : fillers) {
            filler.start();
        }

        for (Filler filler : fillers) {
            filler.join();
        }

        Assert.assertEquals("multiple instance of Singleton detected", set.size(), 1);

        long stop = System.nanoTime();

        System.out.println((stop - start)/1_000_000_000.);

    }
}
