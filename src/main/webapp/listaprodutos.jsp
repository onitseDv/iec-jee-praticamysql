<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Produtos</title>
</head>
<body>
	<center>
		<h1>Produtos</h1>
        <h2>
        	<a href="inserirprodutos">Inserir Novo Produto</a>
            <br/>
            <a href="index.jsp"> Ver Categorias </a>
        </h2>
	</center>
    <div align="center">
        <p>Filtrar por categoria: <select id="categoria" name="categoria" onChange="location.href=this.value">
            <option value="listaprodutos.jsp">Selecione Categoria</option>
            <option value="listaprodutos.jsp">Todas</option>
            <c:forEach var="categoria" items="${listaCategoria}">
                <option value='listaprodutos.jsp?categoria=<c:out value="${categoria.codigo}" />'><c:out value="${categoria.nome}" /></option>
            </c:forEach>
        </select></p>
        <form action="listaprodutos.jsp">
            <p>Pesquisar por nome: <input type="text" id="qnome" name="qnome" size="20" />&nbsp;&nbsp;&nbsp;
            <input type="submit" value="Pesquisar"/></p>
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Produtos</h2></caption>
            <tr>
                <th>Codigo</th>
                <th>Nome</th>
                <th>Preço</th>
                <th>Categoria</th>
                <th>Acoes</th>                              
            </tr>
            <c:forEach var="produto" items="${listaProduto}">
                <tr>
                    <td><c:out value="${produto.codigo}" /></td>
                    <td><c:out value="${produto.nome}" /></td>
                    <td><c:out value="${produto.preco}" /></td>
                    <td><c:out value="${produto.categoria.nome}" /></td>
                    
                    <td>
                    	<a href="deletarprodutos?id=<c:out value='${produto.codigo}' />">Deletar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>

</html>