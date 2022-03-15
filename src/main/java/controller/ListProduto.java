package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funcionalidades.CategoriaFuncionalidade;
import funcionalidades.ProdutoFuncionalidade;
import model.Categoria;
import model.Produto;

@WebServlet("/listar-produtos.jsp")
public class ListProduto extends HttpServlet{

    @EJB
    private ProdutoFuncionalidade service;

    @EJB
    private CategoriaFuncionalidade categoriaService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        try {
            this.listaCategoria(request, response);

            String codCategoria = request.getParameter("categoria");
            String nomeParaBusca = request.getParameter("qnome");

            if (codCategoria != null) {
                Integer codigo = Integer.parseInt(codCategoria);
                this.listarProdutosPorCategoria(codigo, request, response);
            } else if (nomeParaBusca != null) {
                this.listarProdutosPorNome(nomeParaBusca, request, response);
            } else {
                this.listarProdutos(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException {
		List<Produto> listaProduto = this.service.listar();
		request.setAttribute("listaProduto", listaProduto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-produtos.jsp");
		dispatcher.forward(request, response);
	}

    private void listarProdutosPorCategoria(Integer codigoCategoria, HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException {
		List<Produto> listaProduto = this.service.listarPorCategoria(codigoCategoria);
		request.setAttribute("listaProduto", listaProduto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-produtos.jsp");
		dispatcher.forward(request, response);
	}

    private void listarProdutosPorNome(String nomeParaBusca, HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException {
        List<Produto> listaProduto = this.service.listarPorNomeComecandoCom(nomeParaBusca);
		request.setAttribute("listaProduto", listaProduto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista-produtos.jsp");
		dispatcher.forward(request, response);
    }

    private void listaCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Categoria> listaCategoria = categoriaService.listar();
        request.setAttribute("listaCategoria", listaCategoria);
    }
    
}
