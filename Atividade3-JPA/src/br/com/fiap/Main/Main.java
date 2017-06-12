package br.com.fiap.Main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.Dao;
import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Clientes;
import br.com.fiap.entity.Pedidos;

public class Main {

	public static void main(String[] args) {
		Clientes cliente = criarCliente("Rafael", "rafael@gmail.com", 3);
		Clientes cliente2 = criarCliente("Michel", "michel@fiap.com", 6);

		Dao<Clientes> base= new GenericDao<Clientes>(Clientes.class);		
		System.out.println("-- Adicionando clientes");
		base.adicionar(cliente);
		base.adicionar(cliente2);
		
		System.out.println("-- Listando registros");
		base.listar().forEach(System.out::println);
		
		System.out.println("-- Atualizando Registro");
		Clientes clienteBase = base.buscar(cliente.getIdCliente());
		clienteBase.setNome("Pedro");
		base.atualizar(clienteBase);
		
		System.out.println("-- Listando registros");
		base.listar().forEach(System.out::println);
		
		System.out.println("-- Deletando registros");
		base.listar().forEach(c -> base.remover(c));
	}

	private static Clientes criarCliente(String nome, String email, int quantidadeDePedidos) {
		Clientes clientes = new Clientes();
		clientes.setNome(nome);
		clientes.setEmail(email);
		clientes.setPedidos(criarListaDePedidos(clientes, quantidadeDePedidos));
		return clientes;
	}

	private static List<Pedidos> criarListaDePedidos(Clientes cliente, int quantidadeDePedidos) {
		List<Pedidos> pedidos = new ArrayList<>();
		String [] produtos = {"Monitor","Video game", "Radio", "Televisao", "Celular"};
		
		for (int i = 0; i < quantidadeDePedidos; i++) {
			Pedidos pedido = new Pedidos();			
			pedido.setData(LocalDateTime.now());
			pedido.setDescricao(produtos[(int)(Math.random() * produtos.length)]);
			pedido.setValor(Math.random()*500);
			pedido.setIdCliente(cliente);
			pedidos.add(pedido);
		}

		return pedidos;
	}
}
