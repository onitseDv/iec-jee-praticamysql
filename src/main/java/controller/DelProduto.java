package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import funcionalidades.ProdutoFuncionalidade;

@WebServlet(urlPatterns = "/deletar-produto")
public class DelProduto  extends HttpServlet {
	@EJB
    private ProdutoFuncionalidade service;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer codigo = Integer.parseInt(request.getParameter("id"));
            service.deletar(codigo);
            response.sendRedirect(request.getContextPath() + "/listar-produtos.jsp");
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
    
}
