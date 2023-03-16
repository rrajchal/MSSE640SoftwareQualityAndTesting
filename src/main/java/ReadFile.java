/**
 * Author: Rajesh Rajchal
 * This class reads txt file with data in each line (decimal data separated by a comma)
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

public class ReadFile {
    List<Double[]> triangleList;
    ReadFile(String fileName) {
        triangleList = readFile(fileName);
    }

    public List<Double[]> readFile(String path) {
        List<Double[]> triangleList = new ArrayList<>();
        InputStream inputStream = TriangleMain.class.getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try {
        String line = reader.readLine();
        while (line != null) {
            String[] values = line.split(",");
            Double[] data = new Double[3];
            data[0] = Double.parseDouble(values[0].trim());
            data[1] = Double.parseDouble(values[1].trim());
            data[2] = Double.parseDouble(values[2].trim());
            triangleList.add(data);
            line = reader.readLine();
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return triangleList;
    }

    public List<Double[]> getTriangleList() {
        return this.triangleList;
    }
}
