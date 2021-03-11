package com.ecommerce.controllers.compras;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.controllers.login.LoginController;
import com.ecommerce.dao.ProdutoDAO;
import com.ecommerce.dao.VendasDAO;
import com.ecommerce.model.Produto;
import com.ecommerce.model.Venda;

public class ComprarController extends HttpServlet {

	private static final long serialVersionUID = -712397674459957959L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdutoDAO produtoDAO = new ProdutoDAO();

		if (!LoginController.logged) {
			redirectToLogin(request, response);
			return;
		}

		Produto produto = produtoDAO.getById(Long.parseLong(request.getParameter("idProduto")));
		
		request.setAttribute("produto", produto);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/compras/comprar.jsp");
		dispatcher.forward(request, response);
	}

	public void redirectToLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/login/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Venda venda = new Venda();
		venda.setData(Calendar.getInstance().getTime().toString());
		venda.setNomeComprador(request.getParameter("nomeComprador"));
		venda.setCartaoComprador(request.getParameter("cartaoComprador"));
		venda.setCodSegurancaComprador(request.getParameter("codSegurancaComprador"));
		venda.setValor(Double.parseDouble(request.getParameter("valor")));
		venda.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));

		VendasDAO vendasDAO = new VendasDAO();
		vendasDAO.salvar(venda);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/compras/compraFeitaComSucesso.jsp");
		dispatcher.forward(request, response);
	}

}
