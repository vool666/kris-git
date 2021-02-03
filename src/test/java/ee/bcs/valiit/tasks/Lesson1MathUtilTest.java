package ee.bcs.valiit.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson1MathUtilTest {

    @Test
    void min() {
        assertEquals(0, Lesson1MathUtil.min(0,1));
    }

    @Test
    void max() {
        assertEquals(1, Lesson1MathUtil.min(0,1));
    }

    @Test
    void abs() {
        assertEquals(1, Lesson1MathUtil.min(-1, 1));
    }

    @Test
    void isEven() {
        assertFalse(Lesson1MathUtil.isEven(3));
    }

    @Test
    void min3() {
        assertEquals(1, Lesson1MathUtil.min3(1,2,3));
    }

    @Test
    void max3() {
        assertEquals(3, Lesson1MathUtil.min3(1,2,3));
    }
}