import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    @Test
    void rightExceptionNullList() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void rightMessageExceptionNullList() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    void rightExceptionEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }
    @Test
    void rightMessageExceptionEmptyList() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    void getHorses() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4),
                new Horse("Туз Пик", 2.5),
                new Horse("Зефир", 2.6),
                new Horse("Пожар", 2.7),
                new Horse("Лобстер", 2.8),
                new Horse("Пегас", 2.9),
                new Horse("Вишня", 3),
                new Horse("Иван", 2.4),
                new Horse("Туз", 2.5),
                new Horse("Зефирка", 2.6),
                new Horse("Огонь", 2.7),
                new Horse("Конь", 2.8),
                new Horse("Пепел", 2.9),
                new Horse("Черешня", 3),
                new Horse("Белый", 2.4),
                new Horse("Буран", 2.5),
                new Horse("Максим", 2.6),
                new Horse("Петр", 2.7),
                new Horse("Легион", 2.8),
                new Horse("Ветер", 2.9),
                new Horse("Стрела", 3),
                new Horse("Буря", 2.4),
                new Horse("Ураган", 2.5),
                new Horse("Михаил", 2.6),
                new Horse("Рон", 2.7),
                new Horse("Лик", 2.8),
                new Horse("Александр", 2.9),
                new Horse("Ирис", 3),
                new Horse("Тигр", 2.9),
                new Horse("Уран", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }


    @Test
    void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < horses.size(); i++) {
            verify(horses.get(i), times(1)).move();
        }
    }
    @Test
    void getWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(new Horse("i", i, i));
    }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(hippodrome.getWinner(), horses.get(49));
    }
}
