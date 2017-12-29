package com.telefonica.hunting;



import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by POBO on 29/12/2017.
 */
public class Challenge {
    private static final String ANALYZE_1 = "What are the time and space complexity of your solution?";
    private static final String ANALYZE_2 = "What are the limitations of your solution?";
    private static final String ANALYZE_3 = "Why you have decided to implement on that way?";
    private static final String POSSIBLE_IMPROVEMENTS = "What do you think we could do to get a better implementation?";
    private static final String SEND = "Send MySequence class to javi(at)11paths(dot)com";
    private static final String SOLVE_CHALLENGE = "Implement class MySequence. Replace Object type from method signatures with the more suitable type you consider";

    //private static Logger log = LoggerFactory.getLogger(Challenge.class);

    private static class MySequence<T extends Comparable<? super T>> {
        private List<T> dups = new ArrayList<T>();
        List<T> unique = new ArrayList();

        private MySequence() {
        }

        public void add(T n){
            binaryAdd(n);
        }

        private void binaryAdd(T n) {
            List<T> sorted = unique;
            Collections.sort(sorted);

            if (Collections.binarySearch(sorted, n) > 0){
                unique.remove(n);
                dups.add(n);
                //System.out.println("Added " + n.toString() + " to dups");
            }else{
                unique.add(n);
                //System.out.println("Added " + n.toString() + " to unique");
            }

        }
        @Deprecated
        private void simpleAdd(T n) {
            List<T> sorted = unique;
            if (unique.contains(n)){
                unique.remove(n);
                dups.add(n);
                //System.out.println("Added " + n.toString() + " to dups");
            }else{
                unique.add(n);
                //System.out.println("Added " + n.toString() + " to unique");
            }

        }

        /**
         * Internal, only for per testing
         */
        private void clean (){
            dups = new ArrayList<T>();
            unique = new ArrayList<T>();
        }


        public T getFirstNonDuplicated() {
            if (unique.size() > 0)
                return unique.get(0);
            return null;
        }
    }

    private void steps() {
        List<String> stepsOfTheChallenge = new ArrayList();
        stepsOfTheChallenge.add(SOLVE_CHALLENGE);
        stepsOfTheChallenge.add(ANALYZE_1);
        stepsOfTheChallenge.add(ANALYZE_2);
        stepsOfTheChallenge.add(ANALYZE_3);
        stepsOfTheChallenge.add(POSSIBLE_IMPROVEMENTS);
        stepsOfTheChallenge.add(SEND);
    }

    public static void main(String[] args) {
        System.out.println("Help DOC : " + "This is an example of a possible MySequence instance use");
        MySequence s = new MySequence();
        s.add(Integer.valueOf(5));
        s.add(Integer.valueOf(3));
        s.add(Integer.valueOf(5));
        s.add(Integer.valueOf(6));
        Object thisShouldBeNumber_3 = s.getFirstNonDuplicated();
        System.out.println("Output Previous operation returns 3" + " --> " + thisShouldBeNumber_3.toString());
        s.add(Integer.valueOf(3));
        Object thisShouldBeNumber_6 = s.getFirstNonDuplicated();
        System.out.println("Output Previous operation returns 6" + " --> " + thisShouldBeNumber_6.toString());


        System.out.println("-------------- Perf check");
        Random rdn = new Random();
        // warm-up
        IntStream.range(0, 10000).forEach(x -> s.add(rdn.nextInt()));
        s.getFirstNonDuplicated();
        s.getFirstNonDuplicated();
        s.getFirstNonDuplicated();
        s.clean();
        long total = 0;
        for (int i = 0; i < 10; i++) {
            long time = System.currentTimeMillis();
            IntStream.range(0, 50000).forEach(x -> s.add(rdn.nextInt()));
            total += (System.currentTimeMillis() - time);
            System.out.println("Total Time simple unsorted: " + (System.currentTimeMillis() - time));
            s.clean();
        }
        System.out.println("AVG:" + (total / 10));
        total = 0;
        for (int i = 0; i < 10; i++) {
            long time = System.currentTimeMillis();
            IntStream.range(0, 50000).forEach(x -> s.binaryAdd(rdn.nextInt()));
            total+=(System.currentTimeMillis() - time);
            System.out.println("Total Time binary search: " + (System.currentTimeMillis() - time));
            s.clean();
        }
        System.out.println("AVG:" + (total / 10));
    }

}
