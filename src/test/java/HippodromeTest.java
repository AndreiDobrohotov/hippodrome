import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    public void paramIsNull(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.",exception.getMessage());
    }

    @Test
    public void paramIsEmptyList(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.",exception.getMessage());
    }

    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for(int i=0;i<30;i++){
            horses.add(new Horse("test"+i,1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for(int i=0;i<30;i++){
            assertEquals(horses.get(i),hippodrome.getHorses().get(i));
        }
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for(int i=0;i<50;i++){
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for(int i=0;i<50;i++){
            Mockito.verify(hippodrome.getHorses().get(i)).move();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0,5,9})
    public void getWinner(int index){
        List<Horse> horses = new ArrayList<>();
        for(int i=0;i<10;i++){
            horses.add(new Horse("test"+i,0,i==index?1:0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(index),hippodrome.getWinner());
    }

}
