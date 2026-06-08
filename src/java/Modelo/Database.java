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

    private Database() {
        inicializarMedicosConHorarios();
    }

    public static Database getInstance() {
        return instance;
    }

    private void inicializarMedicosConHorarios() {
        Medico m1 = new Medico(1, "Freddy", "Ramirez", "Cardiologia");
        Map<String, ArrayList<String>> agenda1 = new HashMap<>();
        agenda1.put("2026-01-01", new ArrayList<>(Arrays.asList("07:00", "08:00", "09:00","10:00", "11:00", "12:00")));
        agenda1.put("2026-01-02", new ArrayList<>(Arrays.asList("07:00", "08:00", "09:00","10:00", "11:00", "12:00")));
        m1.setHorariosDisponibles(agenda1);
        medicos.put(1, m1);

       
        Medico m2 = new Medico(2, "Jose", "Martinez", "General");
        Map<String, ArrayList<String>> agenda2 = new HashMap<>();
        agenda2.put("2026-01-01", new ArrayList<>(Arrays.asList("07:00", "08:00", "09:00","10:00", "11:00", "12:00")));
        agenda2.put("2026-01-02", new ArrayList<>(Arrays.asList("07:00", "08:00", "09:00","10:00", "11:00", "12:00")));
        m2.setHorariosDisponibles(agenda2);
        medicos.put(2, m2);

        
        Medico m3 = new Medico(3, "Matthew", "Flores", "Pediatria");
        Map<String, ArrayList<String>> agenda3 = new HashMap<>();
        agenda3.put("2026-01-01", new ArrayList<>(Arrays.asList("07:00", "08:00", "09:00","10:00", "11:00", "12:00")));
        agenda3.put("2026-01-02", new ArrayList<>(Arrays.asList("07:00", "08:00", "09:00","10:00", "11:00", "12:00")));
        m3.setHorariosDisponibles(agenda3);
        medicos.put(3, m3);
    }

   
    public List<Medico> getMedicos() {
        lock.readLock().lock();
        try {
            System.out.println("Consulta de medicos iniciada en " + Thread.currentThread().getName());
            Thread.sleep(3000);
            return new ArrayList<>(medicos.values());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ArrayList<>();
        } finally {
            lock.readLock().unlock();
        }
    }

    public Medico getMedicoById(int id) {
        lock.readLock().lock();
        try {
            Thread.sleep(1000);
            return medicos.get(id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public Map<String, ArrayList<String>> getAgendaMedico(int idMedico) {
        lock.readLock().lock();
        try {
            Thread.sleep(2000);
            Medico m = medicos.get(idMedico);
            return m != null ? m.getAgenda() : null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public Paciente getPacienteById(int id) {
        lock.readLock().lock();
        try {
            Thread.sleep(1000);
            return pacientes.get(id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void agregarPaciente(Paciente p) throws Exception {
        lock.writeLock().lock();
        try {
            System.out.println("Registro de paciente " + p.getPaciente_id() +
                               " en " + Thread.currentThread().getName());
            Thread.sleep(10000); 
            if (pacientes.containsKey(p.getPaciente_id())) {
                throw new Exception("Paciente con ID " + p.getPaciente_id() + " ya existe.");
            }
            pacientes.put(p.getPaciente_id(), p);
            System.out.println("Paciente " + p.getPaciente_id() + " registrado exitosamente.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Operacion interrumpida");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<Cita> getCitasByMedico(int medicoId) {
        lock.readLock().lock();
        try {
            Thread.sleep(1500);
            List<Cita> resultado = new ArrayList<>();
            for (Cita c : citas.values()) {
                if (c.getMedico_id() == medicoId)
                    resultado.add(c);
            }
            return resultado;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ArrayList<>();
        } finally {
            lock.readLock().unlock();
        }
    }

    public Cita crearCita(int pacienteId, int medicoId, String fecha, String hora) throws Exception {
        lock.writeLock().lock();
        try {
            System.out.println("Creación de cita para paciente " + pacienteId +
                               " con medico " + medicoId + " en " + Thread.currentThread().getName());
            Thread.sleep(5000);

            Medico m = medicos.get(medicoId);
            if (m == null) throw new Exception("Medico no encontrado");
            Paciente p = pacientes.get(pacienteId);
            if (p == null) throw new Exception("Paciente no registrado");

            if (!m.getHorasDisponibles(fecha).contains(hora))
                throw new Exception("Horario no disponible");

            if (!m.reservarHora(fecha, hora))
                throw new Exception("Error al reservar hora");

            int id = nextCitaId++;
            Cita cita = new Cita(id, pacienteId, medicoId, fecha, hora);
            citas.put(id, cita);
            System.out.println("Cita " + id + " creada para paciente " + pacienteId);
            return cita;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Operacion interrumpida");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Cita actualizarEstadoCita(int citaId, String nuevoEstado) throws Exception {
        lock.writeLock().lock();
        try {
            System.out.println("Actualizacion de cita " + citaId +
                               " a estado " + nuevoEstado + " en " + Thread.currentThread().getName());
            Thread.sleep(8000);
            Cita c = citas.get(citaId);
            if (c == null) throw new Exception("Cita no encontrada");
            c.setEstado(nuevoEstado);
            System.out.println("Cita " + citaId + " actualizada a " + nuevoEstado);
            return c;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Operacion interrumpida");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<Cita> getAllCitas() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(citas.values());
        } finally {
            lock.readLock().unlock();
        }
    }
}