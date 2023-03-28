/**
 * This class will test the Triangle class from txt file input
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReadFileTest {
    static List<Double[]> equilateralTriangleList;
    static List<Double[]> isoscelesTriangleList;
    static List<Double[]> scaleneTriangleList;
    static List<Double[]> invalidTriangleList;

    @BeforeAll
    public static void setup() {
        System.out.println("Reading files...");
        String equilateralFile = "equilateral.txt";     // equilateral.txt: contains 50 triangles data
        String isoscelesFile = "isosceles.txt";         // isosceles.txt: contains 50 triangles data
        String scaleneFile = "scalene.txt";             // equilateral.txt: contains 50 triangles data
        String invalidFile = "invalid.txt";             // invalid.txt: contains 50 invalid data
        ReadFile readEquilateralFile = new ReadFile(equilateralFile);
        equilateralTriangleList = readEquilateralFile.getTriangleDataList();

        ReadFile readIsoscelesFile = new ReadFile(isoscelesFile);
        isoscelesTriangleList = readIsoscelesFile.getTriangleDataList();

        ReadFile readScaleneFile = new ReadFile(scaleneFile);
        scaleneTriangleList = readScaleneFile.getTriangleDataList();

        ReadFile readInvalidFile = new ReadFile(invalidFile);
        invalidTriangleList = readInvalidFile.getTriangleDataList();
    }

    @Test
    public void testEquilateralReadFile() {
        Assertions.assertEquals(equilateralTriangleList.size(), 50);
    }

    @Test
    public void testIsoscelesReadFile() {
        Assertions.assertEquals(isoscelesTriangleList.size(), 50);
    }

    @Test
    public void testScaleneReadFile() {
        Assertions.assertEquals(scaleneTriangleList.size(), 50);
    }
    @Test
    public void testInvalidReadFile() {
        Assertions.assertEquals(invalidTriangleList.size(), 50);
    }

}
