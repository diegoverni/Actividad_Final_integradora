package model;

public class Apagar {
    private int idpagos;
    private String pago;
    private double monto;
    private int vencimiento;
    private int pagado;

    public Apagar(int idpagos, String pago, double monto, int vencimiento, int pagado) {
        this.idpagos = idpagos;
        this.pago = pago;
        this.monto = monto;
        this.vencimiento = vencimiento;
        this.pagado = pagado;
    }

    public Apagar(String pago, double monto, int vencimiento, int pagado) {
        this.pago = pago;
        this.monto = monto;
        this.vencimiento = vencimiento;
        this.pagado = pagado;
    }

    public int getIdpagos() {
        return idpagos;
    }

    public void setIdpagos(int idpagos) {
        this.idpagos = idpagos;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento (int vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado (int pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "Pago{" + "pago=" + pago + ", monto=" + monto + ", vencimiento=" + vencimiento + ", pagado=" + pagado + "}";
    }
}