/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author Estudio
 */
public class Medico {
    private int medico_id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private Map<String, ArrayList<String>> agenda;

    public Medico(int medico_id, String nombre, String apellido, String especialidad) {
        this.medico_id = medico_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.agenda = new ConcurrentHashMap<>();
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
    public Map<String, ArrayList<String>> getAgenda() {
        return new HashMap<>(agenda);
    }

    public void setHorariosDisponibles(Map<String, ArrayList<String>> horarios) {
        agenda.clear();
        agenda.putAll(horarios);
    }

    public ArrayList<String> getHorasDisponibles(String fecha) {
        return this.agenda.getOrDefault(fecha, new ArrayList<>());
    }

    public boolean reservarHora(String fecha, String hora) {
        ArrayList<String> horas = this.agenda.get(fecha);
        System.out.println(horas);
        if (horas != null && horas.contains(hora)) {
            horas.remove(hora);
            System.out.print(horas);
            this.agenda.put(fecha, horas);
            if (horas.isEmpty()) {
                this.agenda.remove(fecha);
            }
            return true;
        }
        return false;
    }
}