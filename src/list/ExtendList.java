package list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExtendList<T> extends ArrayList<T> {

    public <R> List<R> map(Function<T, R> function) {
//        List<R> list = new ArrayList<>();
//        for (Object elem : toArray()) {
//            list.add(function.apply((T) elem));
//        }
//        return list;
        return this.stream()
                .map(elem -> function.apply(elem))
                .collect(Collectors.toList());
    }

    public void fill(int capacity, Supplier<T> supplier) {
        for (int i = 0; i < capacity; i++) {
            add(supplier.get());
        }
    }

    public boolean forall(Predicate<T> predicate) {
//        int trueElementsCount = 0;
//        for (Object elem : toArray()) {
//            boolean test = predicate.test((T) elem);
//            if (test) {
//                trueElementsCount++;
//            }
//        }
//        return trueElementsCount == size();
        return this.stream().allMatch(predicate);
    }

    public List<T>[] partition(Predicate<T> predicate) {
        List<T> list1 = new ArrayList<>();
        List<T> list2 = new ArrayList<>();
        for (Object elem : toArray()) {
            boolean test = predicate.test((T) elem);
            if (test) {
                list1.add((T) elem);
            } else {
                list2.add((T) elem);
            }
        }
        return new List[]{list1, list2};
    }

    public T reduce(T elem,BinaryOperator<T> binaryOperator){
//        for (Object element : toArray()) {
//            elem = binaryOperator.apply(elem, (T) element);
//        }
//        return elem;
        return this.stream().reduce(elem,binaryOperator);
    }


    public static void main(String[] args) {

        ExtendList<Integer> extendList = new ExtendList<>();

        extendList.fill(100,() -> new Random().nextInt());

        List<Integer> s = extendList.map(integer -> integer * integer);
        System.out.println(s);
        List<String> map = extendList.map(integer -> "number = " + integer);
        System.out.println(map);


        List<Integer>[] result = extendList.partition(integer -> integer % 2 == 0);

        Integer sum = extendList.reduce(1, (integer, integer2) -> integer * integer2);
        System.out.println(sum);


    }
}
