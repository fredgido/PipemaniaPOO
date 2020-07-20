package pipe;

import processing.core.PApplet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PipeMania extends PApplet {
	//Elementos globais do jogo.
	Board b1;
	PipeQueue q1;
	TimeLeft tl1;
	Scoreboard s1;

	public static void main(String[] args) {
		PApplet.main("pipe.PipeMania");
	}

	public void settings() {
		// janela 680*480
		size(680, 480);
	}

	public void setup() {
		background(148, 156, 209);
		fill(0);
		stroke(255);
		fill(150);
		
		//contructor da board recebe um scannner de ficheiro para saber das peças.
		try {b1 = new Board(10, 7, 50, 100, 80, this, 1, new Scanner(new File("levels.txt")));
		} catch (FileNotFoundException e1) {	System.out.println("File not found");exit();}
		b1.draw();

		q1 = new PipeQueue(5, 50, 50, 80, this);
		q1.draw();

		tl1 = new TimeLeft(100 + 50 * 10, 80 , 30, 250,	this, color(0, 0, 255), 35);
		tl1.draw();

		s1 = new Scoreboard(100, 80 - 50, 1, this);
		s1.draw();

		//Thread que vai controlar os eventos do jogo todo. Toda a logica temporal e de niveis.
		Thread timer = new Thread() {
			public void run() {
				while (s1.getLevel() < 4) {
					while (tl1.getTimeongoing() < tl1.getTimeout()) {
						try {Thread.sleep(1000);} catch (InterruptedException e) {}
						tl1.setTimeongoing(tl1.getTimeongoing() + 1);
					}

					while (true) {
						try {Thread.sleep(500);} catch (InterruptedException e) {}
						if (b1.fill(0.26))
							s1.pipeFilled();
						if (b1.winloss != 0) {
							b1.pa.textSize(32);
							b1.pa.fill(0, 0, 0);
							if (b1.winloss < 0)
								s1.displayLoss();
							if (b1.winloss > 0)
								s1.displayWin();
							break;
						};
					}
					try {Thread.sleep(5000);} catch (InterruptedException e) {}
					s1.displayClear();
					s1.setLevel(s1.getLevel() + 1);
					tl1 = new TimeLeft(100 + 50 * 10, 80 , 30, 250,	tl1.pa, color(0, 0, 255), 35);
					try {b1 = new Board(10, 7, 50, 100, 80, b1.pa, s1.getLevel(), new Scanner(new File("levels.txt")));
					} catch (FileNotFoundException e1) {System.out.println("File not found");exit();}
				}
			}
		};
		timer.start();
	}

	public void draw() {
		b1.draw();
		q1.draw();
		s1.draw();
		tl1.draw();
	}

	 //
	/**
	 * Esta função é iniciado por evento quando existe um click.
	 */
	public void mouseClicked() {
		if (b1.isInside()) { // verificar se o rato esta dentro da board
			if (b1.getCellObj(mouseX, mouseY).getClass().getSimpleName().equals("Pipe") 
					&& ((Pipe) b1.getCellObj(mouseX, mouseY)).isEmpty()) {//verificar se da para por bomba, ou seja é um pipe não vaziu
				b1.replaceInPlace(b1.getCellObj(mouseX, mouseY),
						new Bomb(b1.getCellObj(mouseX, mouseY).getX(), b1.getCellObj(mouseX, mouseY).getY(), 50, this)); //substituir por bomba
				s1.bombUsed(); // actualizar a pontuação pois foi usada uma bomba
			} else if (b1.getCellObj(mouseX, mouseY).isEmpty()) {
				b1.replaceInPlace(b1.getCellObj(mouseX, mouseY), q1.peek()); // se for uma celula vazia então trocar por um pipe da queue
				q1.cycle();
			}
		}
	}
}