package com.triangle.springproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@SpringBootTest
class SpringProjectApplicationTests {


    @Test
    void decisionTableTest() {

        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(4, 4, 9);

        Assertions.assertTrue(t1.isValidTriangle());
        Assertions.assertTrue(t1.isScalene());
        Assertions.assertFalse(t1.isEquilateral());
        Assertions.assertFalse(t1.isIsosceles());

        Assertions.assertFalse(t2.isValidTriangle());
        Assertions.assertFalse(t2.isScalene());
        Assertions.assertFalse(t2.isEquilateral());
        Assertions.assertFalse(t2.isIsosceles());
    }
}
