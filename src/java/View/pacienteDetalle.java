/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package View;

import Modelo.Paciente;
import Modelo.Database;
import Modelo.Medico;
import Modelo.RequestsProcessor;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.concurrent.Future;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author freddyramirez
 */
public class pacienteDetalle extends HttpServlet {

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
        
        String dpiParam = request.getParameter("dpi");
        if (dpiParam == null || dpiParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error DPI");
            return;
        }
        int dpi = Integer.parseInt(dpiParam);
        
        Future<?> future = RequestsProcessor.getInstance().submit(() -> {
            Paciente p = Database.getInstance().getPacienteById(dpi);
            if (p != null) {
                request.setAttribute("paciente", p);
            } else {
                request.setAttribute("error", "Paciente no encontrado");
            }
        });
        
        try {
            future.get();
        } catch (Exception e) {
            throw new ServletException("Error al buscar paciente", e);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("detalle.jsp");
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
