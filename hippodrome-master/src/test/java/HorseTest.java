import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    void rightExceptionNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }
    @Test
    void rightMessageExceptionNullName() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         "})
    void rightExceptionEmptyName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         "})
    void rightMessageExceptionEmptyName(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    void rightExceptionNegativeSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Voron", -1.0, 1.0));
    }
    @Test
    void rightMessageExceptionNegativeSpeed() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Voron", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    void rightExceptionNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Voron", 1.0, -1.0));
    }
    @Test
    void rightMessageExceptionNegativeDistance() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Voron", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = { "Voron", "Snegok", "Zvezda"})
    void getName(String name) {
        Horse horse = new Horse(name, 1.0, 1.0);
        assertEquals(name, horse.getName());
    }
    @ParameterizedTest
    @CsvSource({"1.0", "2.0", "3.0"})
    void getSpeed(Double speed) {
        Horse horse = new Horse("Voron", speed, 1.0);
        assertEquals(speed, horse.getSpeed());
    }
    @ParameterizedTest
    @CsvSource({"1.0", "2.0", "3.0"})
    void getDistance(Double distance) {
        Horse horse = new Horse("Voron", 1.0, distance);
        assertEquals(distance, horse.getDistance());
    }
    @Test
    void getDistance() {
        Horse horse = new Horse("Voron", 1.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void getRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("Voron", 1.0, 1.0).move();
            horseMockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @CsvSource ({"0.5, 2.0, 3.0",
                 "0.2, 5.0, 2.0",})
    void move(double random, double speed, double distance) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse horse = new Horse("Voron", speed, distance);
            horse.move();
            double expectedDistance = distance + speed * random;
            assertEquals(horse.getDistance(), expectedDistance);
        }
    }
}
