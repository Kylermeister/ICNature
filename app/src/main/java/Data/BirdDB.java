package Data;

import java.io.IOException;

public class BirdDB extends DB {
    public BirdDB(String filename) {
        super(filename);
    }

    // Create a bird and put it into bird constructor
    public Bird createBird() throws IOException {
        String[] data = readData();
        if (data != null && data.length == 4) {
            return new Bird(data[0], data[1], data[2], data[3]);
        }
        return null;
    }
}
