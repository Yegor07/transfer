package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MainModelTest {
    MainModelImplementation m;

    @BeforeEach
    void initTest() {
        m = new MainModelImplementation();
    }

    @Test
    void transfer() {
        assertEquals("10.00", m.transfer("5", "2"));
        assertEquals("5.00", m.transfer("2.5", "2"));
        assertEquals("12.50", m.transfer("5", "2.5"));
    }

    @Test
    void invalidData() {
        assertThrows(NumberFormatException.class, () -> m.transfer("5", "dsffsfd"));
        assertThrows(NumberFormatException.class, () -> m.transfer("5", "-"));
        assertThrows(NumberFormatException.class, () -> m.transfer("5", "%-+2f"));
        assertThrows(NumberFormatException.class, () -> m.transfer("dfsdfds", "3"));
        assertThrows(NumberFormatException.class, () -> m.transfer("-212fr", "3"));
        assertThrows(NumberFormatException.class, () -> m.transfer("%/-1", "3"));
    }
}
