package ee.bcs.avliit;


import ee.bcs.valiit.tasks.Lesson1MathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lesson1MathUtilTest {

    @Test
    public void test() {
        int result = Lesson1MathUtil.min(3,4);
        Assertions.assertEquals(3, result);

    }

}
