package com.ecommerce.controllers.produtos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.controllers.login.LoginController;
import com.ecommerce.dao.ProdutoDAO;
import com.ecommerce.model.Produto;
import com.ecommerce.utils.BaseServlet;

public class ProdutoController extends BaseServlet {
	
	private static final long serialVersionUID = -1292019799822811843L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        super.doGet(req, res);
        handleGetRequest(req, res);
    }

    void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameterValues("action")[0];

        if (!LoginController.logged || !LoginController.flagAdmin) {
			redirectToLogin(request, response);
			return;
		}
        
        switch (action) {
            case "addItem":
            	addItem(request, response);
                break;
            
            case "updateItem":
            	updateItem(request, response);
                break;

            case "removeItem":
            	removeItem(request, response);
                break;
            
            case "listItem":
            	listItem(request, response);
                break;
        }
    }
    
    void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	request.setCharacterEncoding("UTF-8");

		Produto produto = new Produto();
		produto.setNome(request.getParameter("nome"));
		produto.setValor(Double.parseDouble(request.getParameter("valor")));
		produto.setDescricao(request.getParameter("descricao"));
		produto.setUrlImg("resources/images/" + request.getParameter("urlImg"));

		String page = "home.jsp";

		page = "listarprodutos";
		response.sendRedirect(page);
    }
    
    void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Long idProduto = Long.parseLong(request.getParameter("idProduto"));
        
        request.setCharacterEncoding("UTF-8");
        
        Produto produto = new Produto();
        produto.setCodigo(idProduto);
        produto.setNome(request.getParameter("nome"));
        produto.setValor(Double.parseDouble(request.getParameter("valor")));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setUrlImg(request.getParameter("urlImg"));

        response.sendRedirect("listarprodutos");
    }
    
    void removeItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Produto produto = new Produto();
    	produto.setCodigo(Long.parseLong(request.getParameter("idProduto")));

		ProdutoDAO produtoDAO = new ProdutoDAO();

		produtoDAO.excluir(produto.getCodigo());
		response.sendRedirect("listarprodutos");
    }
    
    void listItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<Produto> listaDeProdutos = (ArrayList<Produto>) produtoDAO.findAll();
		
		request.setAttribute("listaDeProdutos", listaDeProdutos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/produtos/listarProdutos.jsp");
		rd.forward(request, response);
    }
    
    void redirectToLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/login/login.jsp");
		dispatcher.forward(request, response);
	}

}
