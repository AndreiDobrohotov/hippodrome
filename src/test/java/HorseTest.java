import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    public static List<String> getEmpties() {
        return List.of(" ","","\n","\r");
    }

    @Test
    public void firstParamIsNull(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null,0));
        assertEquals("Name cannot be null.",exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getEmpties")
    public void firstParamIsBlank(String blank){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(blank,0));
        assertEquals("Name cannot be blank.",exception.getMessage());
    }

    @Test
    public void secondParamIsNegative(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("test",-1));
        assertEquals("Speed cannot be negative.",exception.getMessage());
    }

    @Test
    public void thirdParamIsNegative(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("test",0,-1));
        assertEquals("Distance cannot be negative.",exception.getMessage());
    }

    @Test
    public void getName(){
        String text = "test";
        assertEquals(text, new Horse(text,0).getName());
    }

    @Test
    public void getSpeed(){
        int speed = 1;
        assertEquals(speed, new Horse("test",speed).getSpeed());
    }

    @Test
    public void getDistance(){
        assertEquals(1, new Horse("test",0,1).getDistance());
        assertEquals(0,new Horse("test",0).getDistance());
    }

    //@Test
    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    public void move(double random){
        try(MockedStatic<Horse> staticHorse = Mockito.mockStatic(Horse.class)){
            staticHorse.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            double speed = 3;
            double result = speed*random;
            Horse horse = new Horse("test",speed);
            horse.move();
            assertEquals(result, horse.getDistance());
            staticHorse.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }
    }


}
