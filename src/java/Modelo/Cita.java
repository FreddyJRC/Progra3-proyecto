/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Estudio
 */
public class Cita {
    private int cita_id;
    private int paciente_id;
    private int medico_id;
    private String fecha;
    private String hora;
    private String estado;

    public Cita(int cita_id, int paciente_id, int medico_id, String fecha, String hora) {
        this.cita_id = cita_id;
        this.paciente_id = paciente_id;
        this.medico_id = medico_id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "PENDIENTE";
    }

    public int getCita_id() { return cita_id; }
    public int getPaciente_id() { return paciente_id; }
    public int getMedico_id() { return medico_id; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getEstado() { return estado; }

    public void setEstado(String estado)  {
    if (estado.equals("PENDIENTE") || estado.equals("ATENDIDA")) {
        this.estado = estado;
    } else {
        throw new IllegalArgumentException("Valor Invalido");
    }
    }   
}