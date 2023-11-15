import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantTest {

    @Test
    void evaluate() {

    }

    @Test
    void testToString() {
        assertEquals("-2.1", new Constant(-2.1).toString());
        assertEquals("2.5", new Constant(2.5).toString());
        assertEquals("0", new Constant(0).toString());
        assertEquals("0", new Constant(-0).toString());
    }
}