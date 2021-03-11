package com.ecommerce.controllers.usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.controllers.login.LoginController;
import com.ecommerce.dao.UsuarioDAO;
import com.ecommerce.model.Usuario;
import com.ecommerce.utils.BaseServlet;

public class UsuarioController extends BaseServlet {

	private static final long serialVersionUID = 1641797075609891499L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		super.doGet(req, res);
		handleGetRequest(req, res);
	}

	void handleGetRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getParameterValues("action")[0];

		if (!LoginController.logged || !LoginController.flagAdmin) {
			redirectToLogin(request, response);
			return;
		}

		switch (action) {
		case "addUser":
			addUser(request, response);
			break;

		case "updateUser":
			updateUser(request, response);
			break;

		}
	}

	void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setLogin(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setNome(request.getParameter("nome"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setTelefone(request.getParameter("telefone"));
		usuario.setEndereco(request.getParameter("endereco"));

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

		request.setAttribute("usuario", usuario);
		HttpSession ses = request.getSession();
		ses.setAttribute("usuario", usuario);

		response.sendRedirect("listarprodutos");
	}

	void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setLogin(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setNome(request.getParameter("nome"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setTelefone(request.getParameter("telefone"));
		usuario.setEndereco(request.getParameter("endereco"));
		String page = "index.jsp";

		UsuarioDAO dao = new UsuarioDAO();
		dao.update(usuario);

		page = "/views/home/home.jsp";
		request.setAttribute("usuario", usuario);

		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	void redirectToLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/login/login.jsp");
		dispatcher.forward(request, response);
	}
}
