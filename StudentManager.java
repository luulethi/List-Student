package liststudent;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentManager {
    private final List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

public void addStudent() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Add new student");

    // Nhập và kiểm tra ID
    String id;
    while (true) {
        try {
            System.out.print("Enter student ID: ");
            id = sc.nextLine();
            if (id.matches("\\d+") && Integer.parseInt(id) > 0) {
                break;
            } else {
                System.out.println("Invalid ID. ID must be a positive number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
        }
    }

    // Nhập và kiểm tra tên
    String name;
    while (true) {
        System.out.print("Enter student name: ");
        name = sc.nextLine();
        if (name != null && !name.trim().isEmpty()) {
            break;
        } else {
            System.out.println("Invalid name. Name cannot be empty or whitespace.");
        }
    }

    // Nhập và kiểm tra tuổi
    int age;
    while (true) {
        try {
            System.out.print("Enter student age: ");
            age = sc.nextInt();
            if (age > 18) {
                break;
            } else {
                System.out.println("Invalid age. Age must be greater than 18.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid age.");
            sc.next(); // Clear invalid input
        }
    }

    // Nhập và kiểm tra điểm
    double marks;
    while (true) {
        try {
            System.out.print("Enter student marks: ");
            marks = sc.nextDouble();
            if (marks >= 0.0 && marks <= 10.0) {
                break;
            } else {
                System.out.println("Invalid marks. Marks must be between 0.0 and 10.0.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid marks.");
            sc.next(); // Clear invalid input
        }
    }

    sc.nextLine(); // Clear the buffer
    students.add(new Student(id, name, marks, age));
    System.out.println("Student added successfully!");
}

public void editStudent(String id) {
    // Tìm sinh viên theo ID
    Optional<Student> studentOptional = findStudentById(id);
    if (studentOptional.isEmpty()) {
        System.out.println("Student not found.");
        return;
    }

    Student student = studentOptional.get();
    Scanner sc = new Scanner(System.in);
    System.out.println("Editing student: " + student);

    // Nhập và kiểm tra tên mới
    String name;
    while (true) {
        System.out.print("Enter new name (leave blank to keep current): ");
        name = sc.nextLine().trim(); // Loại bỏ khoảng trắng ở đầu và cuối chuỗi
        if (!name.isEmpty()) { 
            break; // Nếu nhập không trống, thoát vòng lặp
        } else {
            name = student.getName(); // Nếu để trống, giữ nguyên tên hiện tại
            break;
        }
    }

    // Nhập và kiểm tra tuổi mới
    int age;
    while (true) {
        try {
            System.out.print("Enter new age (leave blank to keep current): ");
            String ageInput = sc.nextLine().trim();
            if (ageInput.isEmpty()) {
                age = student.getAge(); // Nếu để trống, giữ nguyên tuổi hiện tại
                break;
            }
            age = Integer.parseInt(ageInput);
            if (age > 18) {
                break; // Tuổi hợp lệ, thoát vòng lặp
            } else {
                System.out.println("Invalid age. Age must be greater than 18.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid age.");
        }
    }

    // Nhập và kiểm tra điểm mới
    double marks;
    while (true) {
        try {
            System.out.print("Enter new marks (leave blank to keep current): ");
            String marksInput = sc.nextLine().trim();
            if (marksInput.isEmpty()) {
                marks = student.getMarks(); // Nếu để trống, giữ nguyên điểm hiện tại
                break;
            }
            marks = Double.parseDouble(marksInput);
            if (marks >= 0.0 && marks <= 10.0) {
                break; // Điểm hợp lệ, thoát vòng lặp
            } else {
                System.out.println("Invalid marks. Marks must be between 0.0 and 10.0.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid marks.");
        }
    }

    // Cập nhật thông tin sinh viên
    student.setName(name);
    student.setAge(age);
    student.setMarks(marks);
    System.out.println("Student updated successfully!");
}

    public void deleteStudent(String id) {
        try {
            boolean removed = students.removeIf(student -> student.getId().equals(id));
            if (removed) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public Optional<Student> findStudentById(String id) {
        try {
            return students.stream().filter(student -> student.getId().equals(id)).findFirst();
        } catch (Exception e) {
            System.out.println("Error finding student: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    public void searchStudentByName() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter name to search: ");
    String name = sc.nextLine().trim();

    List<Student> foundStudents = findStudentsByName(name);

    if (foundStudents.isEmpty()) {
        System.out.println("No students found with name containing: " + name);
    } else {
        System.out.println("Found " + foundStudents.size() + " student(s):");
        for (Student student : foundStudents) {
            System.out.println(student);
        }
    }
}


    public void sortByMarks() {
        try {
            int n = students.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (students.get(j).getMarks() < students.get(j + 1).getMarks()) {
                        // Hoán đổi nếu điểm của sinh viên trước nhỏ hơn sinh viên sau
                        Student temp = students.get(j);
                        students.set(j, students.get(j + 1));
                        students.set(j + 1, temp);
                    }
                }
            }
            System.out.println("Students sorted by marks.");
        } catch (Exception e) {
            System.out.println("Error sorting students: " + e.getMessage());
        }
    }

    public void displayAllStudents() {
        try {
            if (students.isEmpty()) {
                System.out.println("No students available.");
            } else {
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        } catch (Exception e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }

    // Kiểm tra xem ID có phải là số dương hợp lệ không
    private boolean isValidPositiveInteger(String id) {
        try {
            int idNumber = Integer.parseInt(id);
            return idNumber > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Student> findStudentsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
