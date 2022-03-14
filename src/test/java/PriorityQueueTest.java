import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{3, 1, 2}, new int[]{1, 2, 3, 4}),
                Arguments.of(new int[]{-3, -1, -2, 5}, new int[]{-3, -2, -1, 5, 6}),
                Arguments.of(new int[]{3, -2, -5, -1, 2}, new int[]{-5, -2, -1, 2, 3, 4}),
                Arguments.of(new int[]{-3, 1, 11, 0, 9, 3}, new int[]{-3, 0, 1, 3, 9, 11, 12}),
                Arguments.of(new int[]{3, 7, 2, -1, -2}, new int[]{-2, -1, 2, 3, 7, 8}),
                Arguments.of(new int[]{5, 4, 2, 3}, new int[]{2, 3, 4, 5, 6})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with argument={0},{1}")
    @MethodSource("streamProvider")
    public void TestPriorityQueue(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> testQueue = new PriorityQueue<Integer>();
        int[] result = new int[random_array.length];

        for (int value : random_array) {
            testQueue.add(value);
        }

        for (int i = 0; testQueue.size() != 0; i++) {
            result[i] = testQueue.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_thenOfferNull(){
        Exception exception = assertThrows(NullPointerException.class, () ->{
            PriorityQueue<Integer> testQueue = new PriorityQueue<Integer>();
            testQueue.offer(null);
        });

        assertTrue(exception.getMessage() == null);
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThenOne(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            PriorityQueue<Integer> testQueue = new PriorityQueue<Integer>(0);
        });

        assertTrue(exception.getMessage() == null);
    }

    @Test
    public void whenExceptionThrown_thenCannotCastDifferentObject(){
        Exception exception = assertThrows(ClassCastException.class, () ->{
            PriorityQueue<Object> testQueue = new PriorityQueue<Object>();
            testQueue.add(123);
            testQueue.add("123");
        });

        String expectedMessage = "cannot be cast";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
