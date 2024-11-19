package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Productos;
import servicios.ProductosServiceImplement;
import servicios.ProductosServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//Anotación

@WebServlet({"/producto.xls","/productohtml"})
public class ProductoXls extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductosServices servicios = new ProductosServiceImplement();
        List<Productos> productos = servicios.listar();
        //OBTENER EL SERVLETPATH
        String servletPath = req.getServletPath();
        //CREO UNA VARIBLE DE TIPO BOLEANO PARA VERIFICAR QUE PATH
        //ESTOY OBTENIENDO
        boolean xls = servletPath.equals(".xls");
        if(xls){
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition", "attachment; filename=productos.xls");
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Lista de productos</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Listado de productos</h1>");
        out.println("<p><a href=\"" + req.getContextPath() + "/producto.xls"+"\">Descargar xls" + "</a></p>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID PRODUCTO</th>");
        out.println("<th>NOMBRE</th>");
        out.println("<th>CATEGORIA</th>");
        out.println("<th>PRECIO</th>");
        out.println("</tr>");
        productos.forEach(p -> {
            out.println("<tr>");
            out.println("<td>" + p.getIdProducto() + "</td>");
            out.println("<td>" + p.getNombre() + "</td>");
            out.println("<td>" + p.getCategoria() + "</td>");
            out.println("<td>" + p.getPrecio() + "</td>");
            out.println("</tr>");
        });
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

}


