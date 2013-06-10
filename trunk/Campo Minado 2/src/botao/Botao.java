package botao;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import excecoes.ValorInvalidoException;

import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Classe que simula um botao do campo
 * minado com as acoes do clique do mouse.
 * @author Anderson Rodrigues
 *
 */
@SuppressWarnings("serial")
public class Botao extends JButton implements Runnable{

	private String valor;
	private boolean flag;
	private boolean discovered;
	private List<Botao> adjacentes;
	private boolean perdeu;
	
	public Botao() {
		super("");
		valor = "empty";
		flag = false;
		discovered = false;
		perdeu = false;
		adjacentes = new LinkedList<Botao>();
		
		ConfiguraClique();
	}
	
	public Botao(String valor){
		super("");
		this.valor = valor;
		flag = false;
		discovered = false;
		perdeu = false;
		adjacentes = new LinkedList<Botao>();
		
		ConfiguraClique();
	}
	
	public boolean getDiscovered() {
		return discovered;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) throws ValorInvalidoException {
		switch (valor){
		case "empty":
			this.valor = valor;
			break;
			
		case "0":
			this.valor = "empty";
			break;
			
		case "1": 
			this.valor = valor;
			System.out.println("colocando valor 1");
			break;
			
		case "2": 
			this.valor = valor;
			break;
			
		case "3": 
			this.valor = valor;
			break;
			
		case "4": 
			this.valor = valor;
			break;
			
		case "5": 
			this.valor = valor;
			break;
			
		case "6": 
			this.valor = valor;
			break;
			
		case "7": 
			this.valor = valor;
			break;
			
		case "8": 
			this.valor = valor;
			break;
			
		case "mine": 
			this.valor = valor;
			break;
			
		case "flag":
			this.valor = valor;
			break;
		default: 
			throw new ValorInvalidoException();
		}
	}

	public void mostrar() {
		switch (valor){
		case "empty":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/blank.png")));
			break;
			
		case "1":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/1.png")));
			break;

		case "2":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/2.png")));
			break;

		case "3":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/3.png")));
			break;

		case "4":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/4.png")));
			break;

		case "5":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/5.png")));
			break;

		case "6":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/6.png")));
			break;

		case "7":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/7.png")));
			break;

		case "8":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/8.png")));
			break;

		case "mine":
			discovered = true;
			setIcon(new ImageIcon(Botao.class.getResource("/icones/mine.png")));
			break;
			
		}
		
	}
	
	private void CliqueEsquerdo(){
		if (!flag){
			if (valor.equals("empty")){
				mostrar();
				clicaAdjacentes();
			} else if (valor.equals("1") || valor.equals("2") || 
						valor.equals("3") || valor.equals("4") || 
						valor.equals("5") || valor.equals("6") || 
						valor.equals("7") || valor.equals("8")){
				mostrar();
			} else if (valor.equals("mine")){
				perdeu = true;
				mostrar();
			}
		}
	}	

	private void CliqueDireito() {
		if (!discovered){
			if (!flag){
				setIcon(new ImageIcon(Botao.class.getResource("/icones/flag.png")));
				flag = true;
			} else{
				setIcon(null);
				flag = false;
			}
		}
	}
	
	public void addAdjacente(Botao adjacente){
		adjacentes.add(adjacente);
	}
	
	public void processarValores() throws ValorInvalidoException{
		if (!getValor().equals("mine")){
			ListIterator<Botao> iter = adjacentes.listIterator();
			int cont=0;
			while (iter.hasNext()){
				if (iter.next().getValor().equals("mine")){
					cont++;
				}
			}
			setValor(Integer.toString(cont));
		}
	}
	
	private void clicaAdjacentes() {
		ListIterator<Botao> iter = adjacentes.listIterator();
		Botao aux;
		while (iter.hasNext()){
			aux = iter.next();
			if (!aux.getDiscovered()){
				aux.CliqueEsquerdo();
			}
		}
	}

	private void ConfiguraClique(){
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)){
					CliqueEsquerdo();
				} else if (SwingUtilities.isRightMouseButton(e)){
					CliqueDireito();
				}
			}
		});
	}

	@Override
	public void run() {
		
	}

}

