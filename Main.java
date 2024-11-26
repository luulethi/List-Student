package liststudent;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Display All Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 6.");
                continue;
            }

            switch (choice) {
                case 1 -> manager.addStudent();
                case 2 -> {
                    System.out.print("Enter the student ID to be edited: ");
                    String id = scanner.nextLine().trim();
                    manager.editStudent(id);
                }
                case 3 -> {
                    System.out.print("Enter the student ID to delete: ");
                    String id = scanner.nextLine().trim();
                    manager.deleteStudent(id);
                }
                case 4 -> {
                    System.out.print("Enter Student ID to Search: ");
                    String id = scanner.nextLine().trim();
                    Optional<Student> studentOpt = manager.findStudentById(id);
                    if (studentOpt.isPresent()) {
                        System.out.println(studentOpt.get());
                    } else {
                        System.out.println("No student found with ID: " + id);
                    }
                }
                case 5 -> {
                    manager.sortByMarks();
                    System.out.println("Students sorted by marks.");
                }
                case 6 -> manager.displayAllStudents();
                case 7 -> manager.searchStudentByName();
                case 0 -> {
                    System.out.println("Exit program.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid selection. Please select again.");
            }
        }
    }
}



