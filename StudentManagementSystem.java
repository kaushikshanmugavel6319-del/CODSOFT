import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private String department;
    private double percentage;

    public Student(String name, int rollNumber, String grade,
                   String department, double percentage) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.department = department;
        this.percentage = percentage;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return String.format(
                "Roll No: %d | Name: %s | Grade: %s | Dept: %s | Percentage: %.2f%%",
                rollNumber, name, grade, department, percentage);
    }
}

public class StudentManagementSystem {

    private static final String FILE_NAME = "students.dat";
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadStudents();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println(" STUDENT MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.print("Enter Choice: ");

            choice = getIntInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    saveStudents();
                    break;
                case 7:
                    saveStudents();
                    System.out.println("Data Saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);
    }

    private static void addStudent() {

        System.out.print("Enter Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("Enter Roll Number: ");
        int roll = getIntInput();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("Roll Number already exists!");
                return;
            }
        }

        System.out.print("Enter Grade: ");
        String grade = scanner.next();

        System.out.print("Enter Department: ");
        String dept = scanner.next();

        System.out.print("Enter Percentage: ");
        double percentage = getDoubleInput();

        students.add(new Student(
                name,
                roll,
                grade,
                dept,
                percentage));

        System.out.println("Student Added Successfully!");
    }

    private static void removeStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = getIntInput();

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student s = iterator.next();

            if (s.getRollNumber() == roll) {
                iterator.remove();
                System.out.println("Student Removed.");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void searchStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = getIntInput();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("\nStudent Found:");
                System.out.println(s);
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void updateStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = getIntInput();

        for (Student s : students) {

            if (s.getRollNumber() == roll) {

                scanner.nextLine();

                System.out.print("New Name: ");
                s.setName(scanner.nextLine());

                System.out.print("New Grade: ");
                s.setGrade(scanner.next());

                System.out.print("New Department: ");
                s.setDepartment(scanner.next());

                System.out.print("New Percentage: ");
                s.setPercentage(getDoubleInput());

                System.out.println("Student Updated Successfully!");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Available.");
            return;
        }

        System.out.println("\n===== STUDENT RECORDS =====");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void saveStudents() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            out.writeObject(students);
            System.out.println("Data Saved Successfully.");

        } catch (IOException e) {
            System.out.println("Error Saving Data.");
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists())
            return;

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(FILE_NAME))) {

            students = (ArrayList<Student>) in.readObject();

        } catch (Exception e) {
            System.out.println("Could not load previous records.");
        }
    }

    private static int getIntInput() {

        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }

        return scanner.nextInt();
    }

    private static double getDoubleInput() {

        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid decimal number: ");
            scanner.next();
        }

        return scanner.nextDouble();
    }
}