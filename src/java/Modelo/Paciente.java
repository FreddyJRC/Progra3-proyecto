/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Estudio
 */
public class Paciente {
    private int paciente_id;
    private String nombre;
    private String apellido;
    private int edad;

    public Paciente(int paciente_id, String nombre, String apellido, int edad) {
        this.paciente_id = paciente_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public int getPaciente_id() {
        return paciente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }
}