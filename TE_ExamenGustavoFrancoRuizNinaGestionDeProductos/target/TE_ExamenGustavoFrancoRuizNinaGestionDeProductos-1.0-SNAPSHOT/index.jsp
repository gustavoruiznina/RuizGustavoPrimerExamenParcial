<%@page import="com.emergentes.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%
	if(session.getAttribute("listaprod")==null){
		ArrayList<Producto>lisaux=new ArrayList<Producto>();
		session.setAttribute("listaprod",lisaux);
	}
	ArrayList<Producto>lista=(ArrayList<Producto>)session.getAttribute("listaprod");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h4>PRIMER PARCIAL TEM-742</h4>
        <h4>NOMBRE: GUSTAVO FRANCO RUIZ NINA</h4>
        <h4>CARNET: 5472745 L.P.</h4>

                <h1>GESTION DE PRODUCTOS</h1>
        <a href="MainServlet?op=nuevo">Nuevo Producto</a>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>DESCRIPCION</th>
				<th>CANTIDAD</th>
				<th>PRECIO</th>
                                <th>CATEGORIA</th>
				<th>       </th>
				<th>       </th>
			</tr>

<%
            if(lista!=null){
            for(Producto item:lista){
%>

                        <tr>
				<td><%= item.getId() %></td>
				<td><%= item.getDescripcion() %></td>
				<td><%= item.getCantidad() %></td>
				<td><%= item.getPrecio() %></td>
                                <td><%= item.getCategoria() %></td>
				<td>
					<a href="MainServlet?op=editar&id=<%= item.getId() %>">Editar</a>

				</td>
				<td>
					<a href="MainServlet?op=eliminar&id=<%= item.getId() %>" onclick="return(confirm('Esta seguro de eliminar??'))">Eliminar</a>
				</td>
			</tr>
			<%
					}
				}
			%>
		</table>
    </body>
</html>
