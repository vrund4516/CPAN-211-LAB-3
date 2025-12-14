/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3_io;

/**
 *
 * @author vrund
 */
public class Student {
    private final String firstName;
    private final String lastName;
    private final String degree;

    public Student(String firstName, String lastName, String degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
    }

    public String toRecordLine() {
        return "Student\t" + firstName + "\t" + lastName + "\t" + degree;
    }

    @Override
    public String toString() {
        return toRecordLine();
    }
}
