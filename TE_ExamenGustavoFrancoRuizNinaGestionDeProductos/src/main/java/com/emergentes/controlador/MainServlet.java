package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String op=request.getParameter("op");
            Producto objper=new Producto();
            int id, pos;
            HttpSession ses=request.getSession();
            ArrayList<Producto>lista =(ArrayList<Producto>) ses.getAttribute("listaprod");

            switch(op){
            case "nuevo":
                    //enviar un objeto vacio sin editar
                    request.setAttribute("miobjprod",objper);
                    request.getRequestDispatcher("editar.jsp").forward(request,response);
                    break;
            case "editar":
                    //enviar un objeto a editar pero con contenido
                    id=Integer.parseInt(request.getParameter("id"));
                    //averiguar la posicion del elemento en la lista
                    pos=buscarPorIndice(request,id);
                    //obtener el objeto
                    objper=lista.get(pos);
                    request.setAttribute("miobjprod",objper);
                    request.getRequestDispatcher("editar.jsp").forward(request,response);
                    break;
            case "eliminar":
                    //eliminar el registro de la coleccion segun el id
                    id=Integer.parseInt(request.getParameter("id"));
                    pos=buscarPorIndice(request,id);
                    if(pos>=0){
                            lista.remove(pos);
                    }
                    request.setAttribute("listaprod",lista);
                    response.sendRedirect("index.jsp");
                    break;
            default:

            }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            int id=Integer.parseInt(request.getParameter("id"));
            HttpSession ses=request.getSession();
            ArrayList<Producto> lista=(ArrayList<Producto>)ses.getAttribute("listaprod");
            Producto objprod=new Producto();
            objprod.setId(id);
            objprod.setDescripcion(request.getParameter("descripcion"));
            objprod.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            objprod.setPrecio(Float.parseFloat(request.getParameter("precio")));
            objprod.setCategoria(request.getParameter("categoria"));
            if (id==0){
                    //nuevo registro
                    int idNuevo=obtenerId(request);
                    objprod.setId(idNuevo);
                    lista.add(objprod);
            }
            else{
                    //edicion de registro
                    int pos=buscarPorIndice(request,id);
                    lista.set(pos,objprod);
            }
            request.setAttribute("listaprod",lista);
            response.sendRedirect("index.jsp");
        
        
    }
    public int buscarPorIndice(HttpServletRequest request, int id){
	HttpSession ses=request.getSession();
	ArrayList<Producto> lista=(ArrayList<Producto>)ses.getAttribute("listaprod");

	int pos=-1;
	if (lista!=null){
		for(Producto ele:lista){
			++pos;
			if(ele.getId()==id){
				break;
			}
		}
	}
	return pos;
}
    public int obtenerId(HttpServletRequest request){
	HttpSession ses=request.getSession();
	ArrayList<Producto> lista=(ArrayList<Producto>) ses.getAttribute("listaprod");
	//buscar el ultimo id
	int idn=0;
	for (Producto ele:lista){
		idn=ele.getId();
	}
	return idn+1;	
}

}
