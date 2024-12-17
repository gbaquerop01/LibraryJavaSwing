package controlador;

import java.awt.EventQueue;

public class Principal {
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Libreria libreria = new Libreria();
					libreria.addRandomBooks();
					new ParaUI(libreria);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
