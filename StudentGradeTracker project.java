import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

public class StudentGradeTracker {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Student Grade Tracker =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Grades Summary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> viewSummary();
                case 4 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        double grade;
        while (true) {
            System.out.print("Enter grade for " + name + " (0-100): ");
            grade = scanner.nextDouble();
            if (grade >= 0 && grade <= 100) break;
            else System.out.println("Invalid grade! Please enter between 0 and 100.");
        }
        scanner.nextLine(); // consume newline
        students.add(new Student(name, grade));
        System.out.println("✅ Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠ No students added yet!");
            return;
        }
        System.out.println("\n----- Student List -----");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println((i + 1) + ". " + s.getName() + " → " + s.getGrade());
        }
    }

    private static void viewSummary() {
        if (students.isEmpty()) {
            System.out.println("⚠ No students available to calculate summary!");
            return;
        }

        double total = 0, highest = -1, lowest = 101;
        for (Student s : students) {
            double grade = s.getGrade();
            total += grade;
            if (grade > highest) highest = grade;
            if (grade < lowest) lowest = grade;
        }
        double average = total / students.size();

        System.out.println("\n----- Grades Summary -----");
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
    }
}
