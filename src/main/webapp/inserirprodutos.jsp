<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserir Produto</title>
</head>
<body>
    <center>
		<h1>Inserir Produto</h1>
		<form action="inserirprodutos" method="POST">
			<p>Nome: <input type="text" name="nome" id="nome"></p>
            <p>Preço: <input type="number" name="preco" id="preco"></p>
            <p>Categoria: <select id="categoria" name="categoria">
                <option value="0">Selecione a categoria</option>
                <c:forEach var="categoria" items="${listaCategoria}">
                    <option value='<c:out value="${categoria.codigo}" />'><c:out value="${categoria.nome}" /></option>
                </c:forEach>
            </select></p>
			<input type="submit" value="Inserir">

		</form>
		<a href="listaprodutos.jsp"> Voltar </a>
	</center>
</body>
</html>