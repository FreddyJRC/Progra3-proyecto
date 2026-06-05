<%-- 
    Document   : pacientes
    Created on : Jun 4, 2026, 8:44:37 PM
    Author     : freddyramirez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Proyecto</title>
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
                    <a class="nav-link" href="medico">Medicos</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="paciente">Pacientes</a>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        
        <div class="container">
            <div class="row">
              <div class="col">
                  <h2>Buscar Paciente</h2>
                  <form action="paciente/detalle" method="get">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="ID Paciente" aria-label="ID Paciente" aria-describedby="button-addon2" id="dpi" name="dpi">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Buscar</button>
                    </div>
                  </form>
              </div>
            </div>

            <div class="row">
                <div class="col">
                    <h2>Crear Paciente</h2>
                    <form>
                        <div class="mb-3">
                          <label for="paciente_id" class="form-label">DPI Paciente</label>
                          <input type="number" class="form-control" id="paciente_id">
                        </div
                        <div class="mb-3">
                          <label for="nombre" class="form-label">Nombre Paciente</label>
                          <input type="text" class="form-control" id="nombre">
                        </div>
                        <div class="mb-3">
                          <label for="apellido" class="form-label">Apellido Paciente</label>
                          <input type="text" class="form-control" id="apellido">
                        </div>
                        <div class="mb-3">
                          <label for="edad" class="form-label">Edad Paciente</label>
                          <input type="number" class="form-control" id="edad">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </body>
</html>
