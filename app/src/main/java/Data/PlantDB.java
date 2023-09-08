package Data;

import java.io.IOException;

public class PlantDB extends DB {
    public PlantDB(String filename) {
        super(filename);
    }
 // Create a plant and put it into plant constructor
    public Plant createPlant() throws IOException {
        String[] data = readData();
        if (data != null && data.length == 4) {
            return new Plant(data[0], data[1], data[2], data[3]);
        }
        return null;
    }
}
