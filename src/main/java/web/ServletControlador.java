package web;

import data.PagosDAO;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String accion = req.getParameter("accion");
        
        if(accion!=null){
            switch(accion){
                case "eliminar":
                     eliminarPago (req, res);
                    break;
                case "editar":
                    editarPago (req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        }else{
            accionDefault(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String queryParam = req.getParameter("accion");
        if(queryParam!=null){
            switch(queryParam){
                case "insertar":
                    insertarPago (req,res);
                    break;
                case "modificar":
                    modificarPago(req,res);
                    break;
                default:
                    accionDefault(req,res);
                    break;
            }
        }
    }
    
    private void accionDefault(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        List <Apagar> pagos = new PagosDAO().findAll();
        pagos.forEach(System.out::println);
        HttpSession sesion = req.getSession();
        sesion.setAttribute("pagos", pagos);
        sesion.setAttribute("cantidadPagos",calcularPago(pagos));
        sesion.setAttribute("precioTotal", calcularTotal(pagos));
        res.sendRedirect("pagos.jsp");
    }

    private void insertarPago(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String pago = req.getParameter("pago");
        double monto = Double.parseDouble(req.getParameter("monto"));
        int vencimiento = Integer.parseInt(req.getParameter("vencimiento"));
        int pagado = Integer.parseInt(req.getParameter("pagado"));
        Apagar Pago = new Apagar (pago, monto, vencimiento, pagado);
        int registrosMod = new PagosDAO().insert(Pago);
        System.out.println("insertados = " + registrosMod);
        accionDefault(req, res);
    }
    
        private void eliminarPago(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idpagos = Integer.parseInt(req.getParameter("idPago"));
        int regMod = new PagosDAO().deletePago(idpagos);
        System.out.println("SE ELIMINARON: "+ regMod +" REGISTROS");
        accionDefault(req, res);
    }

        private void editarPago (HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idPago = Integer.parseInt(req.getParameter("idpagos"));
        Apagar pagos = new PagosDAO().findById(idPago);
        req.setAttribute("pagos", pagos);
        String jspEditar = "/WEB-INF/paginas/operaciones/editar.jsp";
        req.getRequestDispatcher(jspEditar).forward(req, res);
    }

        private void modificarPago(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String pago = req.getParameter("pago");
        double monto = Double.parseDouble(req.getParameter("monto"));
        int vencimiento = Integer.parseInt(req.getParameter("vencimiento"));
        int pagado = Integer.parseInt(req.getParameter("pagado"));
        int idPago = Integer.parseInt(req.getParameter("idPago"));
        Apagar lib = new Apagar(idPago,pago,monto,vencimiento,pagado);
        int regMod = new PagosDAO().update(lib);
        System.out.println("SE ACTUALIZARON: "+ regMod +" REGISTROS");
        accionDefault(req, res);
    }
    
    private int calcularPago (List<Apagar> lib){
        int cant=0;
        for (int i = 0; i < lib.size(); i++) {
            cant += lib.get(i).getPagado();
        }
        return cant;
    }
    
    private double calcularTotal(List<Apagar> lib){
        double Total = 0;
        for (int i = 0; i < lib.size(); i++) {
            Total += (lib.get(i).getMonto() * lib.get(i).getPagado());
        }
        return Total;
    }
}