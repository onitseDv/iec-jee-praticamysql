package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import model.Categoria;

@WebServlet(urlPatterns = "/edit")
public class EditarCategoria extends HttpServlet {
    @EJB
    private CategoriaDAO dao = new CategoriaDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer codigo = Integer.parseInt(request.getParameter("id"));
            Categoria categoria = dao.porCodigo(codigo);
            if(categoria == null) {
                PrintWriter out = response.getWriter();
                out.print("<html>");
                out.print("<h2> Nao foi possivel localizar a categoria de codigo " + codigo + "</h2>");
                out.print("<br");
                out.print("<a href = 'index.jsp'> Voltar </a>");
                out.print("</html>");
            }else {
                request.setAttribute("categoria", categoria);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
                dispatcher.forward(request, response);
            }
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer codigo = Integer.parseInt(request.getParameter("codigo"));
        String nome = request.getParameter("nome");
        try {
            Categoria categoria = new Categoria();
            categoria.setCodigo(codigo);
            categoria.setNome(nome);
            dao.editar(categoria);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    } 

}
