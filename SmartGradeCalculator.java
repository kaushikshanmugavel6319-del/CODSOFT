import java.util.*;

public class SmartGradeCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("      SMART GRADE CALCULATOR");
        System.out.println("====================================");

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Number of Subjects: ");
        int subjects = sc.nextInt();

        int total = 0;
        int highest = 0;
        int lowest = 100;

        int[] marks = new int[subjects];

        for (int i = 0; i < subjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (0-100): ");
            marks[i] = sc.nextInt();

            total += marks[i];

            if (marks[i] > highest)
                highest = marks[i];

            if (marks[i] < lowest)
                lowest = marks[i];
        }

        double average = (double) total / subjects;

        String grade;
        String performance;

        if (average >= 90) {
            grade = "A+";
            performance = "Outstanding";
        } else if (average >= 80) {
            grade = "A";
            performance = "Excellent";
        } else if (average >= 70) {
            grade = "B";
            performance = "Very Good";
        } else if (average >= 60) {
            grade = "C";
            performance = "Good";
        } else if (average >= 50) {
            grade = "D";
            performance = "Average";
        } else {
            grade = "F";
            performance = "Fail";
        }

        System.out.println("\n========== REPORT CARD ==========");
        System.out.println("Student Name : " + name);
        System.out.println("Total Marks  : " + total);
        System.out.println("Average      : " + String.format("%.2f", average) + "%");
        System.out.println("Highest Mark : " + highest);
        System.out.println("Lowest Mark  : " + lowest);
        System.out.println("Grade        : " + grade);
        System.out.println("Performance  : " + performance);

        if (average >= 90)
            System.out.println("Achievement  : Gold Medal");
        else if (average >= 75)
            System.out.println("Achievement  : Merit Certificate");
        else if (average >= 60)
            System.out.println("Achievement  : Participation Award");
        else
            System.out.println("Achievement  : Keep Improving");

        System.out.println("=================================");

        sc.close();
    }
}
