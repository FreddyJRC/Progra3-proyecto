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
    private Map<String, List<String>> agenda;

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
    public Map<String, List<String>> getAgenda() {
        return new HashMap<>(agenda);
    }

    public void setHorariosDisponibles(Map<String, List<String>> horarios) {
        agenda.clear();
        agenda.putAll(horarios);
    }

    public List<String> getHorasDisponibles(String fecha) {
        return agenda.getOrDefault(fecha, new ArrayList<>());
    }

    public boolean reservarHora(String fecha, String hora) {
        List<String> horas = agenda.get(fecha);
        if (horas != null && horas.contains(hora)) {
            horas.remove(hora);
            if (horas.isEmpty()) {
                agenda.remove(fecha);
            }
            return true;
        }
        return false;
    }
}