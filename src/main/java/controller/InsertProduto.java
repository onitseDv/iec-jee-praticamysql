package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

@WebServlet("/inserir-produto")
public class InsertProduto extends HttpServlet{

    @EJB
    private ProdutoFuncionalidade service;

    @EJB
    private CategoriaFuncionalidade categoriaService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        try {
            listaCategoria(request, response);					
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listaCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Categoria> listaCategoria = categoriaService.listar();
        request.setAttribute("listaCategoria", listaCategoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("inserir-produto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
        double preco = Double.valueOf(request.getParameter("preco"));
        int codigoCategoria = Integer.valueOf(request.getParameter("categoria"));
		
		Categoria categoria;
        try {
            categoria = categoriaService.porCodigo(codigoCategoria);

            if (Objects.isNull(categoria)) {
                PrintWriter out = response.getWriter();
                out.print("<html>");
                out.print("<h2> Nao foi possivel inserir o produto, a categoria selecionada invalida!</h2>");
                out.print("<br/>");
                out.print("<a href = 'inserir-produto'> Voltar </a>");
                out.print("</html>");
            } else {
    
                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setCategoria(categoria);
    
                try {
                    service.inserir(produto);
            
                    response.sendRedirect(request.getContextPath() + "/listar-produtos.jsp");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    PrintWriter out = response.getWriter();
                    out.print("<html>");
                    out.print("<h2> Nao foi possivel inserir o produto!</h2>");
                    out.print("<br/>");
                    out.print("<a href = 'listar-produtos.jsp'> Voltar </a>");
                    out.print("</html>");
                }
    
            }

        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<html>");
            out.print("<h2> Nao foi possivel inserir o produto!</h2>");
            out.print("<br/>");
            out.print("<a href = 'listar-produtos.jsp'> Voltar </a>");
            out.print("</html>");
        }
	}
    
}
