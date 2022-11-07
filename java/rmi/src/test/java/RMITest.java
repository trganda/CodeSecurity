import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RMITest {
    @Test
    public void RemoteServiceTest() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("1.txt"));
        oos.writeFloat((float) 123123213.1231);
        oos.close();
    }
}
