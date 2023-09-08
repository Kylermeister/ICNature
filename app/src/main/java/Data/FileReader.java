package Data;

import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {

    public static String readTextFile(Resources resources, int resourceId) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            InputStream inputStream = resources.openRawResource(resourceId);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
