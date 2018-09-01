package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class SlotMachineStatistic {

    private final Random random = new Random();

    private int numberOfSlots;
    private int numberOfCards;
    private int numberOfExperiments;

    private final List<TypeCounter> TYPES = Arrays.asList(
            new AllUniques(),
            new OneDouble(),
            new TwoDoubles(),
            new OneTriple(),
            new OneQuadruple()
    );

    private abstract class TypeCounter {
        public String name;
        public int counter;
        public TypeCounter(String name) { this.name = name; }
        public abstract boolean isItMine(Integer[] outcome);
        protected List<Integer> getRepeatableItems(Integer[] outcome) {
            List<Integer> original = new ArrayList<>();
            original.addAll(Arrays.asList(outcome));
            HashSet<Integer> uniques = new HashSet<>(original);
            for (Integer unique : uniques) { original.remove(unique); }
            return original;
        }
        @Override
        public String toString() { return name + "  probability=" + 1.*counter/numberOfExperiments; }
    }

    private class AllUniques extends TypeCounter {
        public AllUniques() { super("AllUniques"); }
        @Override
        public boolean isItMine(Integer[] outcome) {
            boolean result = getRepeatableItems(outcome).size() == 0;
            if (result) counter++;
            return result;
        }
    }

    private class OneDouble extends TypeCounter {
        public OneDouble() { super("OneDouble"); }
        @Override
        public boolean isItMine(Integer[] outcome) {
            boolean result = getRepeatableItems(outcome).size() == 1;
            if (result) counter++;
            return result;
        }
    }

    private class TwoDoubles extends TypeCounter {
        public TwoDoubles() { super("TwoDoubles");}
        @Override
        public boolean isItMine(Integer[] outcome) {
            List<Integer> repeatableItems = getRepeatableItems(outcome);
            boolean result = (repeatableItems.size() == 2) &&
                    (repeatableItems.get(0) != repeatableItems.get(1));
            if (result) counter++;
            return result;
        }
    }

    private class OneTriple extends TypeCounter {
        public OneTriple() { super("OneTriple");}
        @Override
        public boolean isItMine(Integer[] outcome) {
            List<Integer> repeatableItems = getRepeatableItems(outcome);
            boolean result = (repeatableItems.size() == 2) &&
                    (repeatableItems.get(0) == repeatableItems.get(1));
            if (result) counter++;
            return result;
        }
    }

    private class OneQuadruple extends TypeCounter {
        public OneQuadruple() { super("OneQuadruple");}
        @Override
        public boolean isItMine(Integer[] outcome) {
            List<Integer> repeatableItems = getRepeatableItems(outcome);
            boolean result = (repeatableItems.size() == 3) &&
                    (repeatableItems.get(0) == repeatableItems.get(1))  &&
                    (repeatableItems.get(0) == repeatableItems.get(2));
            if (result) counter++;
            return result;
        }
    }

    public SlotMachineStatistic(int numberOfSlots,
                                int numberOfCards,
                                int numberOfExperiments) {
        this.numberOfSlots = numberOfSlots;
        this.numberOfCards = numberOfCards;
        this.numberOfExperiments = numberOfExperiments;
    }

    private Integer[] getNextOutcome(){
        Integer[] result = new Integer[numberOfSlots];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(numberOfCards);
        }
        return result;
    }

    private SlotMachineStatistic throwDice() {
        for (int i = 0; i < this.numberOfExperiments; i++) {
            Integer[] outcome = this.getNextOutcome();
            for (TypeCounter type : this.TYPES) {
                if (type.isItMine(outcome)) break;
            }
        }
        return this;
    }

    private void print() {
        System.out.println("Number of slots: " + numberOfSlots);
        System.out.println("Number of cards: " + numberOfCards);
        System.out.println("Number of experiments: " + numberOfExperiments);
        int summary = 0;
        for (TypeCounter type : this.TYPES) {
            System.out.println(type);
            summary += type.counter;
        }
        System.out.println("Summary probability= " + 1.*summary/this.numberOfExperiments + '\n');
    }

    public static void main(String[] args) {

        SlotMachineStatistic statistic;
        statistic = new SlotMachineStatistic( 4, 4, 1_000_000 );
        statistic.throwDice().print();
    }

}
