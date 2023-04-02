package com.triangle.springproject;

/**
 * Author: Rajesh Rajchal
 * Triangle class to determine triangle type
 * Equilateral, Isosceles, Scalene, or Invalid
 */
class Triangle {
    private final double sideA;
    private final double sideB;
    private final double sideC;

    Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public TriangleType getTriangleType() {
        if (!isValidTriangle()) {
            System.out.println("Invalid Triangle: " + this);
            return TriangleType.INVALID;
        } else if (!checkLimit()) {
            System.out.println("Invalid Triangle Sides: " + this);
            return TriangleType.INVALID;
        } else {
            System.out.println(this);
            if (isEquilateral()) {
                return TriangleType.EQUILATERAL;
            } else if (isIsosceles()) {
                return TriangleType.ISOSCELES;
            } else {
                return TriangleType.SCALENE;
            }
        }
    }

    private boolean checkLimit() {
        return this.sideA <= 1000 && this.sideB <= 1000 && this.sideC <= 1000;
    }

    public boolean isValidTriangle() {
        if (this.sideA > 0 && this.sideB > 0 && this.sideC > 0) {
            return (this.sideA + this.sideB > this.sideC) &&
                    (this.sideA + this.sideC > this.sideB) &&
                    (this.sideB + this.sideC > this.sideA);
        }
        return false;
    }

    public boolean isEquilateral() {
        return isValidTriangle() && this.sideA == this.sideB && this.sideB == this.sideC;
    }

    public boolean isIsosceles() {
        return isValidTriangle() &&
                (this.sideA == this.sideB || this.sideB == this.sideC || this.sideA == this.sideC);
    }

    public boolean isScalene() {
        return isValidTriangle() && !isEquilateral() && !isIsosceles();
    }

    public double getSideA() {
        return this.sideA;
    }

    public double getSideB() {
        return this.sideB;
    }

    public double getSideC() {
        return this.sideC;
    }

    @Override
    public String toString() {
        return "Triangle{sideA=" + sideA + ", sideB=" + sideB + ", sideC=" + sideC + "}";
    }
}