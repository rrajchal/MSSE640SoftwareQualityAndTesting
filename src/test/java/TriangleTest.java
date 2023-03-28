/**
 * This class is used to test Triangle class
 * This class tests data from text files and some data
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TriangleTest {


    // Test files were created with the help of ChatGPT
    @Test
    public void fileTest() {
        List<Double[]> equilateralTriangleList;
        List<Double[]> isoscelesTriangleList;
        List<Double[]> scaleneTriangleList;
        List<Double[]> invalidTriangleList;

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

        // Testing 50 lines of equilateral triangle data, all list should be equilateral
        for (Double[] data : equilateralTriangleList) {
            Triangle triangle = new Triangle(data[0], data[1], data[2]);
            TriangleType type = triangle.getTriangleType();
            Assertions.assertEquals(type, TriangleType.EQUILATERAL);
        }

        // Testing 50 lines of isosceles triangle data, all list should be isosceles
        for (Double[] data : isoscelesTriangleList) {
            Triangle triangle = new Triangle(data[0], data[1], data[2]);
            TriangleType type = triangle.getTriangleType();
            Assertions.assertEquals(type, TriangleType.ISOSCELES);
        }

        // Testing 50 lines of scalene triangle data, all list should be scalene
        for (Double[] data : scaleneTriangleList) {
            Triangle triangle = new Triangle(data[0], data[1], data[2]);
            TriangleType type = triangle.getTriangleType();
            Assertions.assertEquals(type, TriangleType.SCALENE);
        }

        // Testing 50 lines of invalid triangle data, all list should be scalene
        for (Double[] data : invalidTriangleList) {
            Triangle triangle = new Triangle(data[0], data[1], data[2]);
            TriangleType type = triangle.getTriangleType();
            Assertions.assertEquals(type, TriangleType.INVALID);
        }
    }

    @Test
    public void equilateralTriangleTest(){
        Triangle equilateral1 = new Triangle(2.0, 2.0, 2.0);
        Triangle equilateral2 = new Triangle(1.0, 1.0, 1.0);
        Triangle equilateral3 = new Triangle(5.0, 5.0, 5.0);
        Triangle equilateral4 = new Triangle(100.0, 100.0, 100.0);

        TriangleType type1 = equilateral1.getTriangleType();
        TriangleType type2 = equilateral2.getTriangleType();
        TriangleType type3 = equilateral3.getTriangleType();
        TriangleType type4 = equilateral4.getTriangleType();

        Assertions.assertEquals(type1, TriangleType.EQUILATERAL);
        Assertions.assertEquals(type2, TriangleType.EQUILATERAL);
        Assertions.assertEquals(type3, TriangleType.EQUILATERAL);
        Assertions.assertEquals(type4, TriangleType.EQUILATERAL);
    }

    @Test
    public void isoscelesTriangleTest(){
        Triangle isosceles1 = new Triangle(2.0, 2.0, 3.0);
        Triangle isosceles2 = new Triangle(1.0, 1.0, 1.5);
        Triangle isosceles3 = new Triangle(5.0, 5.0, 7.0);
        Triangle isosceles4 = new Triangle(100.0, 100.0, 10.0);

        TriangleType type1 = isosceles1.getTriangleType();
        TriangleType type2 = isosceles2.getTriangleType();
        TriangleType type3 = isosceles3.getTriangleType();
        TriangleType type4 = isosceles4.getTriangleType();

        Assertions.assertEquals(type1, TriangleType.ISOSCELES);
        Assertions.assertEquals(type2, TriangleType.ISOSCELES);
        Assertions.assertEquals(type3, TriangleType.ISOSCELES);
        Assertions.assertEquals(type4, TriangleType.ISOSCELES);
    }

    @Test
    public void scaleneTriangleTest(){
        Triangle scalene1 = new Triangle(1.5, 2.0, 3.0);
        Triangle scalene2 = new Triangle(1.0, 2.0, 1.5);
        Triangle scalene3 = new Triangle(5.0, 6.0, 7.0);
        Triangle scalene4 = new Triangle(100.0, 95.0, 10.0);

        TriangleType type1 = scalene1.getTriangleType();
        TriangleType type2 = scalene2.getTriangleType();
        TriangleType type3 = scalene3.getTriangleType();
        TriangleType type4 = scalene4.getTriangleType();

        Assertions.assertEquals(type1, TriangleType.SCALENE);
        Assertions.assertEquals(type2, TriangleType.SCALENE);
        Assertions.assertEquals(type3, TriangleType.SCALENE);
        Assertions.assertEquals(type4, TriangleType.SCALENE);
    }

    @Test
    public void invalidTriangleTest() {
        Triangle scalene1 = new Triangle(1.0, 2.0, 3.0);
        Triangle scalene2 = new Triangle(1.0, -2.0, 1.5);
        Triangle scalene3 = new Triangle(5.0, 6.0, 100.0);
        Triangle scalene4 = new Triangle(100.0, 9.0, 10.0);

        TriangleType type1 = scalene1.getTriangleType();
        TriangleType type2 = scalene2.getTriangleType();
        TriangleType type3 = scalene3.getTriangleType();
        TriangleType type4 = scalene4.getTriangleType();

        Assertions.assertEquals(type1, TriangleType.INVALID);
        Assertions.assertEquals(type2, TriangleType.INVALID);
        Assertions.assertEquals(type3, TriangleType.INVALID);
        Assertions.assertEquals(type4, TriangleType.INVALID);
    }

    @Test
    public void boundaryValuesValidTest() {
        Triangle equilateral1 = new Triangle(1.0, 1.0, 1.0);
        Triangle equilateral2 = new Triangle(1000.0, 1000.0, 1000.0);
        Triangle scalene1 = new Triangle(110.0, 900.0, 1000.0);
        Triangle scalene2 = new Triangle(999, 1.0, 999.9);

        Assertions.assertEquals(equilateral1.getTriangleType(), TriangleType.EQUILATERAL);
        Assertions.assertEquals(equilateral2.getTriangleType(), TriangleType.EQUILATERAL);
        Assertions.assertEquals(scalene1.getTriangleType(), TriangleType.SCALENE);
        Assertions.assertEquals(scalene2.getTriangleType(), TriangleType.SCALENE);
    }

    @Test
    public void boundaryValuesInvalidTest() {
        Triangle equilateral1 = new Triangle(1000.1, 1000.1, 1000.1);
        Triangle scalene1 = new Triangle(101.0, 900.0, 1000.1);
        Triangle scalene2 = new Triangle(0.0, 9.0, 1000);
        Triangle scalene3 = new Triangle(1000.0, 1001.0, 1000.1);

        Assertions.assertEquals(equilateral1.getTriangleType(), TriangleType.INVALID);
        Assertions.assertEquals(scalene1.getTriangleType(), TriangleType.INVALID);
        Assertions.assertEquals(scalene2.getTriangleType(), TriangleType.INVALID);
        Assertions.assertEquals(scalene3.getTriangleType(), TriangleType.INVALID);
    }
}
