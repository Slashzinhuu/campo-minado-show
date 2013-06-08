package campo;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Botao extends JButton{

	private String valor;
	private boolean flag;
	private boolean discovered;
	
	public Botao() {
		super("");
		valor = "5";
		flag = false;
		
		ConfiguraClique();
	}
	
	private void ConfiguraClique(){
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)){
					
					switch (valor){
					case "empty": setIcon(new ImageIcon(Botao.class.getResource("/icones/blank.png")));
						break;
						
					case "1": setIcon(new ImageIcon(Botao.class.getResource("/icones/1.png")));
						break;
						
					case "2": setIcon(new ImageIcon(Botao.class.getResource("/icones/2.png")));
						break;
						
					case "3": setIcon(new ImageIcon(Botao.class.getResource("/icones/3.png")));
						break;
						
					case "4": setIcon(new ImageIcon(Botao.class.getResource("/icones/4.png")));
						break;
						
					case "5": setIcon(new ImageIcon(Botao.class.getResource("/icones/5.png")));
						break;
						
					case "6": setIcon(new ImageIcon(Botao.class.getResource("/icones/6.png")));
						break;
						
					case "7": setIcon(new ImageIcon(Botao.class.getResource("/icones/7.png")));
						break;
						
					case "8": setIcon(new ImageIcon(Botao.class.getResource("/icones/8.png")));
						break;
						
					case "mine":
						break;
					}
				} else if (SwingUtilities.isRightMouseButton(e)){
					if (!flag){
						setIcon(new ImageIcon(Botao.class.getResource("/icones/flag.png")));
						flag = true;
					} else{
						setIcon(null);
						flag = false;
					}
				}
			}
		});
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
