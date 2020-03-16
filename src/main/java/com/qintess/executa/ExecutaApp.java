package com.qintess.executa;

import java.util.List;
import java.util.Scanner;

import com.qintess.crud.ClienteCrud;
import com.qintess.model.Cliente;

public class ExecutaApp {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		while(1>0) {
			menu();
			String op = sc.nextLine();
			if(op.equals("0")) {
				sc.close();
				break;
			}
			operacao(op);
		}
	}
	
	public static void menu() {
		
		System.out.println("Digite a opção desejada: ");
		System.out.println("1) Inserir novo nome");
		System.out.println("2) Deletar nome");
		System.out.println("3) Selecionar tudo");
		System.out.println("4) Buscar por nome");
		System.out.println("9) Criar tabela Cliente (Apenas primeira Vez)");
		System.out.println("0) Sair");
		
		
		
	}
	
	public static void operacao(String op) {
		switch (op) {
		case "1":
			inserir();
			break;
		case "2":
			deletar();
			break;
		case "3":
			System.out.println("ID    | NOME");
			for(Cliente cliente : ClienteCrud.selectAll()) {
				System.out.println("ID: "+ cliente.getId()+ " | " + cliente.getNome());
			}
			System.out.println("");
			break;
		case "4":
			buscarPorNome();
			break;
		case "9":
			ClienteCrud.createClienteTable();
			System.out.println("\n \n \n");
			break;
		default:
			System.out.println("Essa operação não existe");
			break;
		}
	}
	
	public static void inserir() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome para inserir: ");
		String nome = sc.nextLine();
		ClienteCrud.insert(nome);
		System.out.println("Nome inserido com sucesso!");
		System.out.println("");
		
	}
	
	public static void deletar() {
		Scanner sc = new Scanner(System.in);
		List<Cliente> lista = ClienteCrud.selectAll();
		for(Cliente cliente : lista) {
			System.out.println("ID: " + cliente.getId() + " | " + cliente.getNome());
		}
		System.out.println("Digite o Id que deseja excluir");
		boolean verificar = false;
		boolean verificarId = false;
		while(!verificar) {
			try {
				int id = Integer.parseInt(sc.nextLine());
				for(Cliente cliente : lista) {
					if(cliente.getId() == id) {
						ClienteCrud.delete(cliente.getId());
						verificarId = true;
						verificar = true;
						System.out.println(cliente.getNome() + " Deletado");
						break;
					}
				}
				if(!verificarId) {
					new Exception();
				}
			}catch (Exception e) {
				System.out.println("Digite um id válido");
			}
		}
	}

	public static void buscarPorNome() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome que deseja buscar");
		String nome = sc.nextLine();
		for(Cliente cliente : ClienteCrud.selectByName(nome)) {
			System.out.println("ID: " + cliente.getId() + " | " + cliente.getNome());
		}
		System.out.println("");
	}
}
