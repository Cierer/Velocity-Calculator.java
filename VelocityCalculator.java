import java.util.Scanner;

public class VelocityCalculator {
    private double vi, a, t;

    // Prevents exceptions by validating numeric input before parsing
    private double readDouble(Scanner input, String prompt) {
        while (true) {
            System.out.println(prompt);

            if (input.hasNextDouble()) {
                return input.nextDouble();
            } else {
                System.out.println("Invalid number. Please try again.");
                input.next();
            }
        }
    }

    // Ensures physically meaningful values
    private double readPositiveDouble(Scanner input, String prompt) {
        while (true) {
            double value = readDouble(input, prompt);

            if (value >= 0) {
                return value;
            } else {
                System.out.println("Value must be zero or positive.");
            }
        }
    }

    // Restricts user input to valid equation choices
    private int readMenuChoice(Scanner input) {
        while (true) {
            System.out.println("Choose equation:");
            System.out.println("1. vf = vi + at");
            System.out.println("2. vf² = vi² + 2at");

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                if (choice == 1 || choice == 2) {
                    return choice;
                }
            } else {
                input.next();
            }

            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

    // Safety boundary
    public void velocityDataEntry() {
        Scanner input = new Scanner(System.in);

        try {
            vi = readDouble(input, "Insert initial velocity:");
            a  = readDouble(input, "Insert acceleration:");
            t  = readPositiveDouble(input, "Insert time:");

            int choice = readMenuChoice(input);
            velocityEquation(choice);

        } catch (Exception e) {
            // Catch unexpected runtime errors 
            System.out.println("Unexpected system error occurred.");
        }
    }

    // Maps user choice to the correct physics equation
    private void velocityEquation(int choice) {
        switch (choice) {
            case 1 -> calculateVf(vi, a, t);
            case 2 -> calculateVfSquared(vi, a, t);
        }
    }

    // Computes final velocity using vf = vi + at
    private double calculateVf(double vi, double a, double t) {
        double vf = vi + (a * t);
        System.out.println("Final Velocity is: " + vf);
        return vf;
    }

    /*Computes final velocity using vf² = vi² + 2at
     Includes validation to prevent default results*/
    private double calculateVfSquared(double vi, double a, double t) {
        double expression = Math.pow(vi, 2) + 2 * a * t;

        if (expression < 0) {
            System.out.println("Error: calculation results in an imaginary velocity.");
            return Double.NaN;
        }

        double vf = Math.sqrt(expression);
        System.out.println("Final Velocity is: " + vf);
        return vf;
    }

    public static void main(String[] args) {
        new VelocityCalculator().velocityDataEntry();
    }
}
