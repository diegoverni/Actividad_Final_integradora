package data;

import static data.Conexion.*;
import java.sql.*;
import java.util.*;
import model.Apagar;

public class PagosDAO {
    private static final String SQL_CREATE="INSERT INTO pagos (pago, monto, vencimiento, pagado) VALUES(?, ?, ?, ?)";
    private static final String SQL_READ="SELECT * FROM pagos";
    private static final String SQL_READ_BY_ID= "SELECT * FROM pagos WHERE idpagos = ?";
    private static final String SQL_UPDATE="UPDATE pagos SET pago = ?, monto = ?, vencimiento = ?, pagado = ? WHERE idpagos = ?";
    private static final String SQL_DELETE="DELETE FROM pagos WHERE idpagos = ?";
    
    
    public List<Apagar> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Apagar Pago;
        List<Apagar> Pagos = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int idpagos = rs.getInt(1);
                String pago = rs.getString(2);
                double monto = rs.getDouble(3);
                int vencimiento = rs.getInt(4);
                int pagado = rs.getInt(5);

                Pago = new Apagar(idpagos, pago, monto, vencimiento, pagado);

                Pagos.add(Pago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return Pagos;
    }
    
    public Apagar findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Apagar pagos = null;
        
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                int idpagos = rs.getInt(1);
                String pago = rs.getString(2);
                double monto = rs.getDouble(3);
                int vencimiento = rs.getInt(4);
                int pagado = rs.getInt(5);

                pagos = new Apagar(idpagos, pago, monto, vencimiento, pagado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return pagos;
    }
    
    public int insert(Apagar pago){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_CREATE);
            stmt.setString(1, pago.getPago());
            stmt.setDouble(2, pago.getMonto());
            stmt.setInt(3, pago.getVencimiento());
            stmt.setInt(4, pago.getPagado());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int update(Apagar pago){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, pago.getPago());
            stmt.setDouble(2, pago.getMonto());
            stmt.setInt(3, pago.getVencimiento());
            stmt.setInt(4, pago.getPagado());
            stmt.setInt(5, pago.getIdpagos());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
        public int deletePago (int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}