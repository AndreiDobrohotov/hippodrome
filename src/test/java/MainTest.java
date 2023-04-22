import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Disabled
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void mainMethodTimeOut() {
        try {
            Main.main(new String[]{""});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
