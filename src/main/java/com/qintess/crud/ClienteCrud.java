package com.qintess.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qintess.dbaconnection.ConnectionFactory;
import com.qintess.model.Cliente;

public class ClienteCrud {
	
	public static void createClienteTable() {
		
		String sql = "CREATE TABLE CLIENTE(id int primary key auto_increment, name varchar(255))";
		
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement()){
			
			stmt.execute(sql);
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Você já criou a tabela");
		}
	}

	public static void insert(String nome) {
		
		String sql = "INSERT INTO CLIENTE(name) VALUES(?)";
	
		try(Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, nome);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Se você está executando pela primeira vez, primeiro precione 9 para criar a tabela");
		}
	}
	
	public static List<Cliente> selectAll() {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		String sql = "Select * from CLIENTE";
		
		try(Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()	) {
				Cliente cliente = new Cliente(rs.getString("name"), rs.getInt("id"));
				listaCliente.add(cliente);
			}
			return listaCliente;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Se você está executando pela primeira vez, primeiro precione 9 para criar a tabela");

		}
		return null;
	}

	public static void delete(int id) {
		
		String sql = "delete from cliente where id = ?";
	
		try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Se você está executando pela primeira vez, primeiro precione 9 para criar a tabela");

		}
	}
	
	public static List<Cliente> selectByName(String nome) {
		String sql = "select * from cliente where  lower(name) like ?";
		List<Cliente> lista = new ArrayList<Cliente>();
		try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, "%"+nome+"%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				lista.add(new Cliente(rs.getString("name"), rs.getInt("id")));
			}
			rs.close();
			return lista;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Se você está executando pela primeira vez, primeiro precione 9 para criar a tabela");

		}
		return null;
	}
}
