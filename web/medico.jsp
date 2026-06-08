<%-- 
    Document   : medico
    Created on : Jun 4, 2026, 3:28:10 PM
    Author     : freddyramirez
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Medico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Medicos | Proyecto</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Progra 3</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="/Proyecto">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cita">Citas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="medico">Medicos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="paciente">Pacientes</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div class="container">
            <div class="row">
              <div class="col">
                <table class="table table-hover">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">Especialidad</th>
                      </tr>
                    </thead>
                    <tbody>
                        <%
                            if (request.getAttribute("medicos") != null) {
                                List<Medico> medicos = (List<Medico>) request.getAttribute("medicos");
                                for (Medico m : medicos) {
                        %>
                        <tr data-href="medico/citas?id=<%= m.getMedico_id() %>">
                            <th scope="row"><%= m.getMedico_id() %></th>
                            <td><%= m.getNombre() %></td>
                            <td><%= m.getApellido() %></td>
                            <td><%= m.getEspecialidad() %></td>
                        </tr>
                        <%
                                    
                                    }
                            } else {
                        %>
                      <tr>
                          <th class="text-center" scope="row" colspan="4">- No se encontraron medicos registrados -</th>
                      </tr>
                      <% } %>
                    </tbody>
                </table>
              </div>
            </div>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
        <script>
            document.addEventListener("DOMContentLoaded", () => {
            const rows = document.querySelectorAll(".table-hover tbody tr");

            rows.forEach(row => {
              row.addEventListener("click", () => {
                const href = row.getAttribute("data-href");
                if (href) {
                  window.location.href = href; // Navigates to the URL
                }
              });
            });
          });
        </script>
    </body>
</html>
