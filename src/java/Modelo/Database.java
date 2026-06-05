/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Estudio
 */
public class Database {
    private final Map<Integer, Medico> medicos = new HashMap<>();
    private final Map<Integer, Paciente> pacientes = new HashMap<>();
    private final Map<Integer, Cita> citas = new HashMap<>();
    private int nextCitaId = 1;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Database instance = new Database();
    public static Database getInstance() { return instance; }

    private Database() {
        inicializarMedicosConHorarios();
    }

    private void inicializarMedicosConHorarios() {
        Medico m1 = new Medico(1, "Jose", "Martinez", "General");
        Map<String, List<String>> agenda1 = new HashMap<>();
        agenda1.put("2026-01-20", Arrays.asList("09:00", "10:00", "11:00"));
        agenda1.put("2026-01-21", Arrays.asList("09:00", "10:00"));
        m1.setHorariosDisponibles(agenda1);
        medicos.put(1, m1);

        
        Medico m2 = new Medico(2, "Freddy", "Ramirez", "Cardiologia");
        Map<String, List<String>> agenda2 = new HashMap<>();
        agenda2.put("2026-01-20", Arrays.asList("14:00", "15:00", "16:00"));
        agenda2.put("2026-01-22", Arrays.asList("08:00", "09:00"));
        m2.setHorariosDisponibles(agenda2);
        medicos.put(2, m2);

      
        Medico m3 = new Medico(3, "Matthew", "Flores", "Pediatria");
        Map<String, List<String>> agenda3 = new HashMap<>();
        agenda3.put("2026-01-21", Arrays.asList("10:00", "11:00", "12:00"));
        m3.setHorariosDisponibles(agenda3);
        medicos.put(3, m3);
    }

     
    public List<Medico> getMedicos() {
        lock.readLock().lock();
        try { return new ArrayList<>(medicos.values()); }
        finally { lock.readLock().unlock(); }
    }

    public Medico getMedicoById(int id) {
        lock.readLock().lock();
        try { return medicos.get(id); }
        finally { lock.readLock().unlock(); }
    }

    public Map<String, List<String>> getAgendaMedico(int idMedico) {
        lock.readLock().lock();
        try {
            Medico m = medicos.get(idMedico);
            return m != null ? m.getAgenda() : null;
        } finally { lock.readLock().unlock(); }
    }

    
    public Paciente getPacienteById(int id) {
        lock.readLock().lock();
        try { return pacientes.get(id); }
        finally { lock.readLock().unlock(); }
    }

    public void agregarPaciente(Paciente p) throws Exception {
        lock.writeLock().lock();
        try {
            if (pacientes.containsKey(p.getPaciente_id()))
                throw new Exception("Paciente con ID " + p.getPaciente_id() + " ya existe.");
            pacientes.put(p.getPaciente_id(), p);
        } finally { lock.writeLock().unlock(); }
    }

   
    public List<Cita> getCitasByMedico(int medicoId) {
        lock.readLock().lock();
        try {
            List<Cita> resultado = new ArrayList<>();
            for (Cita c : citas.values()) {
                if (c.getMedico_id() == medicoId)
                    resultado.add(c);
            }
            return resultado;
        } finally { lock.readLock().unlock(); }
    }

    public Cita crearCita(int pacienteId, int medicoId, LocalDate fecha, LocalTime hora) throws Exception {
        lock.writeLock().lock();
        try {
            Medico m = medicos.get(medicoId);
            if (m == null) throw new Exception("Medico no encontrado");
            Paciente p = pacientes.get(pacienteId);
            if (p == null) throw new Exception("Paciente no registrado");

            String fechaStr = fecha.toString();
            String horaStr = hora.toString();
            if (!m.getHorasDisponibles(fechaStr).contains(horaStr))
                throw new Exception("Horario no disponible");

            if (!m.reservarHora(fechaStr, horaStr))
                throw new Exception("Error al reservar hora");

            int id = nextCitaId++;
            Cita cita = new Cita(id, pacienteId, medicoId, fecha, hora);
            citas.put(id, cita);
            return cita;
        } finally { lock.writeLock().unlock(); }
    }

    public Cita actualizarEstadoCita(int citaId, String nuevoEstado) throws Exception {
        lock.writeLock().lock();
        try {
            Cita c = citas.get(citaId);
            if (c == null) throw new Exception("Cita no encontrada");
            c.setEstado(nuevoEstado);
            return c;
        } finally { lock.writeLock().unlock(); }
    }
}