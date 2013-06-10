package campo;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;

import excecoes.ValorInvalidoException;

import botao.Botao;


/**
 * Classe do tipo JPanel responsavel pela geracao do campo de jogo
 * @author Slashzinhuu
 *
 */
@SuppressWarnings("serial")
public class Campo extends JPanel {
	
	/**
	 * Matriz de botoes que compoem o campo de jogo
	 */
	Botao campoBotoes[][];
	
	/**
	 * Quantidades de linhas e colunas da matriz de jogo
	 */
	private static final int row = 20; //quantidade de filas
	private static final int column = 20; //quantidade de colunas
	
	/**
	 * Quantidade de minas a serem criadas (dificuldade do jogo)
	 */
	private final int mines = 30; //quantidade de minas.
	
	/**
	 * Construtor do objeto Campo.
	 * Gera a matriz de botoes e chama o metodo que a povoa.
	 */
	//TODO inserir quantidade de minas depois do menu estar pronto
	public Campo(/*int mines*/) {
		super(new GridLayout(row, column, 2, 2));
		campoBotoes = new Botao[row][column];
//		this.mines = mines;
		
		gerarCampo();
		gerarMinas();
		try {
			processarCampo();
		} catch (ValorInvalidoException e) {
			e.printStackTrace();
		}
	}

	public void cheat(){
		for (int i = 0; i < row; i++){
			try {
				campoBotoes[0][i].setValor(Integer.toString(i));
			} catch (ValorInvalidoException e) {
				e.printStackTrace();
			}	
			campoBotoes[0][i].mostrar();
		}
	}
	
	/**
	 * Metodo que povoa a matriz de botoes.
	 */
	private void gerarCampo() {
		for (int i = 0; i < row; i++){
			for ( int j = 0; j < column; j++){
				campoBotoes[i][j] = new Botao();
				campoBotoes[i][j].setSize(10, 10);
				campoBotoes[i][j].setVisible(true);
				this.add(campoBotoes[i][j]);
			}
		}
	}

	/**
	 * Metodo que gera as posicoes aleatorias das minas.
	 */
	private void gerarMinas() {
		/*
		 * Utiliza a implementação de Conjunto e de numeros aleatorios
		 * para gerar as posicoes das minas sem repeticao.
		 * Em seguida usa o iterador para gerar as posicoes.
		 */
		
		Set<ParDeNumeros> set = new TreeSet<ParDeNumeros>();
		Random r = new Random(); //inicializa o Random.
		
		while (set.size()< mines){ //inserir 'mines' posicoes no conjunto
			set.add(new ParDeNumeros(r.nextInt(row), r.nextInt(column))); //posicoes geradas aleatoriamente
		}
		
		Iterator<ParDeNumeros> iter = set.iterator();
		ParDeNumeros aux;
		while (iter.hasNext()){ //para todas as posicoes inseridas no conjuntos
			 aux = iter.next();
			 try {
				campoBotoes[aux.getNum1()][aux.getNum2()].setValor("mine");
				System.out.println("colocando mina na posicao i"+aux.getNum1() + " j" + aux.getNum2());
			} catch (ValorInvalidoException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	/**
	 * Metodo que gera os numeros a serem representados nos
	 * quadrados vizinhos das minas.
	 * @throws ValorInvalidoException Se houver algum erro inesperado na contagem das minas
	 */
	private void processarCampo() throws ValorInvalidoException {
		//para todos os elementos do campo
		for (int i = 0; i<row; i++){
			for (int j = 0; j<column; j++){
				processarAdjacentes(i, j);
				campoBotoes[i][j].processarValores();
			}
		}
	}

	/**
	 * Numera todos os numeros adjacentes ao botao mina das coordenadas i,j.
	 * @param i Linha correspontende ao botao mina
	 * @param j Coluna correspondente ao botao mina
	 */
	private void processarAdjacentes(int i, int j) {
		if (i==0){
			if (j==0){ //canto superior esquerdo
				processarCantoSE(i, j);
			} else if (j==(column-1)){ //canto superior direito
				processarCantoSD(i, j);
			} else {//primeira linha
				processarCantoS(i, j);
			}
			
		} else if (i==(row-1)){
			if (j==0){ //canto inferior esquerdo
				processarCantoIE(i, j);
			}else if (j==(column-1)){ //canto inferior direito
				processarCantoID(i, j);
			} else {//ultima linha
				processarCantoI(i, j);
			}
		} else if (j==0){ //primeira coluna
			processarCantoE(i, j);
		} else if (j==(column-1)){ //ultima coluna
			processarCantoD(i, j);
		} else {
			processarMeio(i, j);
		}
	}

	/*
	 * [i]	[j+1]); //D
	 * [i]	[j-1]); //E
	 * [i-1]	[j]); //S
	 * [i+1]	[j]); //I
	 * [i-1]	[j+1]); //SD
	 * [i-1]	[j-1]); //SE
	 * [i+1]	[j+1]); //ID
	 * [i+1]	[j-1]); //IE
	 */
	private void processarCantoSE(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j+1]); //D
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j+1]); //ID
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j]); //I
	}

	private void processarCantoSD(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j-1]); //E
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j-1]); //IE
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j]); //I
	}

	private void processarCantoS(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j-1]); //E
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j-1]); //IE
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j]); //I
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j+1]); //ID
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j+1]); //D
	}

	private void processarCantoIE(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j]); //S
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j+1]); //SD
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j+1]); //D
	}

	private void processarCantoID(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j]); //S
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j-1]); //SE
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j-1]); //E
	}

	private void processarCantoI(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j-1]); //E
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j-1]); //SE
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j]); //S
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j+1]); //SD
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j+1]); //D
	}

	private void processarCantoE(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j]); //S
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j+1]); //SD
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j+1]); //D
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j+1]); //ID
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j]); //I
	}

	private void processarCantoD(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j]); //S
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j-1]); //SE
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j-1]); //E
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j-1]); //IE
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j]); //I
	}
	
	private void processarMeio(int i, int j) {
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j-1]); //SE
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j]); //S
		campoBotoes[i][j].addAdjacente(campoBotoes[i-1]	[j+1]); //SD
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j-1]); //E
		campoBotoes[i][j].addAdjacente(campoBotoes[i]	[j+1]); //D
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j-1]); //IE
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j]); //I
		campoBotoes[i][j].addAdjacente(campoBotoes[i+1]	[j+1]); //ID
	}

	
}
