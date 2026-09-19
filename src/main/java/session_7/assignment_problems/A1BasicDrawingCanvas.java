abstract class Shape {

    private static int counter = 1000;
    private final String shapeId;

    public Shape() {
        counter++;
        shapeId = "SH-" + counter;
    }

    public abstract double calculateArea();

    public abstract void scale(double factor);

    public void scale(double xFactor, double yFactor) {
        scale(xFactor);
        scale(yFactor);
    }

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {

    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        radius *= factor;
    }
}

class SquareShape extends Shape {

    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        side *= factor;
    }
}

public class A1BasicDrawingCanvas {

    static void printArea(Shape s) {
        System.out.println(
            "Shape ID: " + s.getShapeId()
            + " | Area: " + s.calculateArea()
        );
    }

    public static void main(String[] args) {

        CircleShape c = new CircleShape(5.0);

        SquareShape sq = new SquareShape(4.0);

        System.out.println(
            "Circle Area: " + c.calculateArea()
        );

        System.out.println(
            "Square Area: " + sq.calculateArea()
        );

        // One-argument scale() overload
        sq.scale(2.0);

        System.out.println(
            "Square Area after scaling: "
            + sq.calculateArea()
        );

        printArea(c);
        printArea(sq);

        // Two-argument overloaded scale()
        c.scale(2.0, 1.5);

        System.out.println(
            "Circle Area after scaling: "
            + c.calculateArea()
        );
    }
}