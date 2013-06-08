package campo;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Campo extends JPanel {
	
	JButton campoBotoes[][];
	private static final int row = 20; //quantidade de filas
	private static final int column = 20; //quantidade de colunas
	
	public Campo() {
		
		super(new GridLayout(row, column, 2, 2));
		campoBotoes = new Botao[row][column];
		
		for (int i = 0; i < row; i++){
			for ( int j = 0; j < column; j++){
				campoBotoes[i][j] = new Botao();
				campoBotoes[i][j].setSize(10, 10);
				campoBotoes[i][j].setVisible(true);
				this.add(campoBotoes[i][j]);
			}
		}}
}
