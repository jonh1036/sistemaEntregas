package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ecommerce.model.Usuario;

public class UsuarioDAO extends GenericDao<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public boolean validar(Usuario u) {
		try {
			Connection con = DbConnect.getConexao();
			String sql = "select * from usuario where login = ? and senha = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getSenha());

			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
	
	public Usuario procurarUsuarioPeloID(String login) {
		try {
			String sql = "select * from usuario where login = ? ;";
			PreparedStatement con = DbConnect.getConexao().prepareStatement(sql);

			con.setString(1, login);
			ResultSet rs = con.executeQuery();
			Usuario user = new Usuario();

			if (rs.next()) {
				user.setCpf(rs.getString("cpf"));
				user.setEndereco(rs.getString("endereco"));
				user.setLogin(rs.getString("login"));
				user.setNome(rs.getString("nome"));
				user.setSenha(rs.getString("senha"));
				user.setTelefone(rs.getString("telefone"));
				user.setFlagAdmin(rs.getBoolean("flagADmin"));
			}
			rs.close();
			con.close();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
