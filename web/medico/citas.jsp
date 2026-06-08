<%-- 
    Document   : citas
    Created on : Jun 4, 2026, 4:11:50 PM
    Author     : freddyramirez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Cita"%>
<%@page import="java.util.Map"%>
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
                    <a class="nav-link" href="../cita">Citas</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="../medico">Medicos</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="../paciente">Pacientes</a>
                  </li>
                </ul>
              </div>
            </div>
        </nav>
        
        <div class="container">
            <div class="row">
                <div class="col">
                  <h2>Horario</h2>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Fecha</th>
                        <th scope="col">Hora</th>
                      </tr>
                    </thead>
                    <tbody>
                      <%
                            if (request.getAttribute("medico") != null) {
                                Medico medico = (Medico) request.getAttribute("medico");
                                Map<String, ArrayList<String>> citas = medico.getAgenda();
                                for (Map.Entry<String, ArrayList<String>> m : citas.entrySet()) {
                        %>
                        <tr>
                            <td class="align-middle"
                                rowspan="<%= m.getValue().size() + 1 %>">
                                <%= m.getKey() %>
                            </td>
                        </tr>
                        <%
                                    for (String hora : m.getValue()) {
                        %>
                        <tr>
                            <td><%= hora %></td>
                        </tr>
                        <% }}} %>
                    </tbody>
                </table>
              </div>
            </div>
            <div class="row">
              <div class="col">
                  <h2>Citas</h2>
                <table class="table table-hover">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Paciente</th>
                        <th scope="col">Medico</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Hora</th>
                        <th scope="col">Estado</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                        <%
                            if (request.getAttribute("citas") != null) {
                                ArrayList<Cita> citas = (ArrayList<Cita>) request.getAttribute("citas");
                                for (Cita cita : citas) {
                        %>
                      <tr>
                          <th scope="row"><%= cita.getCita_id() %></th>
                          <td><%= cita.getPaciente_id() %></td>
                          <td><%= cita.getMedico_id() %></td>
                          <td><%= cita.getFecha() %></td>
                          <td><%= cita.getHora() %></td>
                          <td><%= cita.getEstado() %></td>
                        <td>
                            <form method="POST">
                                <input type="hidden" name="cita_id" value="<%= cita.getCita_id() %>">
                                <input type="hidden" name="estado" value="ATENDIDA">
                                <button type="submit" class="btn btn-outline-success btn-sm">Atender</button>
                            </form>
                        </td>
                      </tr>
                      <% }} %>
                    </tbody>
                </table>
              </div>
            </div>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </body>
</html>
