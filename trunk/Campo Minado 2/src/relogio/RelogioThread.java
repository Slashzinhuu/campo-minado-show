package relogio;

import java.awt.Container;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Thread que recebe uma label da interface principal
 * e a atualiza a fim de mostrar o contador do tempo
 * decorrido da partida 
 * 
 * @author Anderson Rodrigues
 * Adaptado de: https://code.google.com/p/controle-academico-2012-2/source/browse/Controle+Academico+-+Curso+Java+PD/src/controle/RelogioThread.java?r=27
 * 
 */
public class RelogioThread extends Thread{

	JLabel label;
	
	public RelogioThread(JLabel label) {// construtor
		this.label = label;
		this.label.setBounds(65, 0, 45, 85);// seta a posição do label
		iniciaRelogio();// inicia o relógio
	}
	
	public void iniciaRelogio() {
		this.start();// inicia a thread.
	}
	
	public void run() {// sobrescreve o método run()
		long inicio = System.currentTimeMillis();//armazena a hora inicial da jogada
		while (true) {// while para fazer o loop infinito
			long fim = System.currentTimeMillis() - inicio;  
		    int segundo = (int) (fim / 1000);  
		    int hora = (segundo / 3600);  
		    int minuto = (segundo - hora * 3600) / 60;  
		    int segundo2 = segundo - hora * 3600 - minuto * 60;
			String horaString;// nova string horas
			String minString;// nova string minutos
			String segundoString;// nova string segundos
			if (hora < 10) {// se hora for menor que 10 precisa colocar
							// um 0 à esquerda
				horaString = "0" + hora;
			} else {
				horaString = "" + hora;
			}
			if (minuto < 10) {// se minuto for menor que 10 precisa
								// colocar um 0 à esquerda
				minString = "0" + minuto;
			} else {
				minString = "" + minuto;
			}
			if (segundo2 < 10) {// se segundo for menor que 10 precisa
								// colocar um 0 à esquerda
				segundoString = "0" + segundo2;
			} else {
				segundoString = "" + segundo2;
			}
			this.label.setText(horaString + ":" + minString + ":"
					+ segundoString);// seta hora atual no label
			try {
				sleep(500);// faz a thread entrar em estado de espera
							// por 500 milissegundos ou meio segundo
			} catch (Exception e) {
			}
		}
	}

	

}