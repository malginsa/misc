package misc;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JmhSample {
    @Benchmark

    public void wellHelloThere() {
        System.out.println("well Hello There");
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
            .include(JmhSample.class.getSimpleName())
            .forks(1)
            .build();

        new Runner(opt).run();
    }
}
