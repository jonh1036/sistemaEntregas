package com.ecommerce.controllers.login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.dao.ProdutoDAO;
import com.ecommerce.model.Produto;

public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 556037066353419678L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginController.logged = false;
		LoginController.flagAdmin = false;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<Produto> listaDeProdutos = (ArrayList<Produto>) produtoDAO.findAll();
		
		request.setAttribute("listaDeProdutos", listaDeProdutos);
		RequestDispatcher rd = request.getRequestDispatcher("/views/produtos/loja.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
