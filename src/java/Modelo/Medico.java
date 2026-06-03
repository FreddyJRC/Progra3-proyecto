/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Estudio
 */
public class Medico {
    private int medico_id;
    private String nombre;
    private String apellido;
    private String especialidad;

    public Medico(int medico_id, String nombre, String apellido, String especialidad) {
        this.medico_id = medico_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public int getMedico_id() {
        return medico_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}