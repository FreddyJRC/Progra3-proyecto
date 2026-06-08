/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package View;

import Modelo.Paciente;
import Modelo.Database;
import Modelo.RequestsProcessor;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.concurrent.Future;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author freddyramirez
 */
public class paciente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
          "paciente.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pacienteId = Integer.parseInt(request.getParameter("paciente_id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        
        Paciente p = new Paciente(pacienteId, nombre, apellido, edad);
        
        Future<?> future = RequestsProcessor.getInstance().submit(() -> {
            try {
                Database.getInstance().agregarPaciente(p);
                request.setAttribute("mensaje", "Paciente registrado");
                request.setAttribute("paciente", p);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        });
        
        try {
            future.get();
        } catch (Exception e) {
            throw new ServletException("Error al registrar paciente", e);
        }
        
        // Redirige de vuelta al formulario con mensaje
        processRequest(request, response);
    }
}

   