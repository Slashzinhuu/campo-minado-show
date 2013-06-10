package campo;

/**
 * Classe interna que implementa um par de números
 * que vai ser usado para a nao repeticao de
 * posicoes na geracao da matriz de minas
 * @author Anderson Rodrigues
 * 
 */
public class ParDeNumeros implements Comparable<ParDeNumeros>{
	
	private int num1;
	private int num2;
	
	public ParDeNumeros(int n1, int n2) {
		this.num1 = n1;
		this.num2 = n2;
	}
	
	public int getNum1() {
		return num1;
	}
	public int getNum2() {
		return num2;
	}
	
	/**
	 * Metodo que compara dois pares.
	 * Nota: ESSE MÉTODO NÃO DIZ SE UM PAR É MAIOR QUE OUTRO.
	 * ELE RETORNA ZERO SE FOREM IGUAIS E 1 SE FOREM DIFERENTES!!!
	 * 
	 * Method that compares two pairs.
	 * Note: THIS METHOD DOES NOT TELL IF A PAIR IS GREATER THAN OTHER.
	 * IT RETURNS 'zero' IF BOTH PAIRS ARE EQUAL OR 'one' IF THEY ARE DIFFERENT!!!
	 * 
	 * @returns 0 (zero) if both pairs are equal and 1 (one) if they are different.  
	 */
	@Override
	public int compareTo(ParDeNumeros outroPar) {
		if (((this.num1 == outroPar.num1) && (this.num2 == outroPar.num2)))
			return 0;
		else
			return 1;
	}
	
}