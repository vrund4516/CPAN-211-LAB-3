/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3_io;

/**
 *
 * @author vrund
 */
public class Professor {
    private final String firstName;
    private final String lastName;
    private final String department;

    public Professor(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public String toRecordLine() {
        return "Professor\t" + firstName + "\t" + lastName + "\t" + department;
    }

    @Override
    public String toString() {
        return toRecordLine();
    }
}
