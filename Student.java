/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liststudent;

/**
 *
 * @author DT
 */
public class Student {
    private String id;
    private String name;
    private double marks;
    private int age;

    public Student(String id, String name, double marks, int age) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

     public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }
 
    
    
  
    public String getRank() {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }
    
  
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }

   
}
