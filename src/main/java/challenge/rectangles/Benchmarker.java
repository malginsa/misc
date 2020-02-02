package challenge.rectangles;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static challenge.rectangles.Calculator.bruteForce;
import static challenge.rectangles.PointsCollectionUtils.readPredefinedSet;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class Benchmarker
{
    private HashSet<Point> points;

    public static void main(String[] args) throws RunnerException
    {
        Options options = new OptionsBuilder()
                .include(Benchmarker.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Setup
    public void init() throws IOException
    {
        points = readPredefinedSet(300, 300);
    }

    @Benchmark
    public void fireBruteForce(Blackhole blackhole){
        int result = bruteForce(points);
        blackhole.consume(result);
    }
}
