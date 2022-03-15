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

@WebServlet("/inserirprodutos")
public class InsertProduto extends HttpServlet{

    @EJB
    private ProdutoFuncionalidade produtoFunc;

    @EJB
    private CategoriaFuncionalidade categoriaFunc;

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
        List<Categoria> listaCategoria = categoriaFunc.listar();
        request.setAttribute("listaCategoria", listaCategoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("inserirprodutos.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
        double preco = Double.valueOf(request.getParameter("preco"));
        int codigoCategoria = Integer.valueOf(request.getParameter("categoria"));
		
		Categoria categoria;
        try {
            categoria = categoriaFunc.porCodigo(codigoCategoria);

            if (Objects.isNull(categoria)) {
                PrintWriter out = response.getWriter();
                out.print("<html>");
                out.print("<h2> Categoria inválida!</h2>");
                out.print("<br/>");
                out.print("<a href = 'inserirprodutos'> Voltar </a>");
                out.print("</html>");
            } else {
    
                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setCategoria(categoria);
    
                try {
                    produtoFunc.inserir(produto);
            
                    response.sendRedirect(request.getContextPath() + "/listaprodutos.jsp");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    PrintWriter out = response.getWriter();
                    out.print("<html>");
                    out.print("<h2> Produto inválido!</h2>");
                    out.print("<br/>");
                    out.print("<a href = 'listaprodutos.jsp'> Voltar </a>");
                    out.print("</html>");
                }
    
            }

        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("<html>");
            out.print("<h2> Produto inválido!</h2>");
            out.print("<br/>");
            out.print("<a href = 'listaprodutos.jsp'> Voltar </a>");
            out.print("</html>");
        }
	}
    
}
