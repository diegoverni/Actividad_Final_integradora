<div class="modal fade" id="agregarPagoModal" tabindex="-1" aria-labelledby="agregarPagoModal" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Pago</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${pageContext.request.contextPath}/servletControlador?accion=insertar" method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="pago">Pago</label>
                        <input type="text" class="form-control" name="pago" required="">
                    </div>
                    <div class="form-group">
                        <label for="monto">Monto</label>
                        <input type="currency" class="form-control" name="monto">
                    </div>
                    <div class="form-group">
                        <label for="vencimiento">Vencimiento</label>
                        <input type="number" class="form-control" name="vencimiento" required="">
                    </div>
                    <div class="form-group">
                        <label for="pagado">Pagado</label>
                        <input type="number" class="form-control" name="pagado" required="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">
                        Guardar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>