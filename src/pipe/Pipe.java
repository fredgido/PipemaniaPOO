package pipe;

import processing.core.PApplet;
import java.util.*;

public class Pipe extends Cell{

	protected boolean up = false, down = false, right = false, left = false;
	protected double fullness = 0;
	protected int piece = -1;
	
	public Pipe(int ix, int iy, int iside, PApplet ipa, boolean iup, boolean idown, boolean iright, boolean ileft) {
		super(ix, iy, iside, ipa);
		this.up = iup;
		this.down = idown;
		this.right = iright;
		this.left = ileft;
		
		this.color(225, 218, 218);
	}
	
	public Pipe (int ix, int iy, int iside, PApplet ipa) {
		super(ix, iy, iside, ipa);
		Random r = new Random();
		
		setPiece(r.nextInt(6));
		switch(piece) {
		case 0:
			this.up = true;
			this.down = true;
			break;
		case 1:
			this.up = true;
			this.right = true;
			break;
		case 2:
			this.up = true;
			this.left = true;
			break;
		case 3:
			this.up = true;
			this.down = true;
			this.right = true;
			this.left = true;
			break;
		case 4:
			this.right = true;
			this.down = true;
			break;
		case 5:
			this.left = true;
			this.down = true;
			break;
		case 6:
			this.left = true;
			this.right = true;
			break;
		}
		this.color(225, 218, 218);
	}
	
	/**
	 * Desenhar a peça
	*/	
	@Override
	public void draw() {
		pa.stroke(255);
		pa.fill(this.color);
		pa.square(x, y, side);
		
		pa.fill((color((int)(192-192*fullness),(int)(192-192*fullness),(int) (255*fullness))));//* (1-a[0]) + color(0, 0,255)));
		pa.noStroke();
		pa.circle(x + (side / 2), y + (side / 2), side/5);
		
		if (this.up)
			pa.rect(x + (side/2) - (side / 10), y /*+ (side/2) - (side / 10)*/, side/5, side/2);
		if (this.down)
			pa.rect(x + (side/2) - (side / 10), y + (side/2), side/5, side/2);
		if (this.left)
			pa.rect(x, y + (side/2) - (side / 10), side/2, side/5);
		if (this.right)
			pa.rect(x + (side/2), y + (side/2) - (side / 10), side/2, side/5);
	}
	
	/**
	 * Função para encher a peça com agua, se já cheio retorna que está cheio.
	 * @param fillRate percentagem de agua que a peça vai encher. 
	 * @return bollean se está cheia.
	 */
	public int fill(double fillRate) {
		if (fullness < 1)
			fullness += fillRate;
		else
			return 1;
		return 0;
	}
	

	/**
	 * Função para descodificar para que direcção a agua vai sair ao 
	 * entrar por outra em cada peça. retorna -1 se for a entrada for incompativel.
	 * @param direction direcção, em relação a peça anterior, para qual a agua vai.
	 * @return int representa o sentido, se -1 significa que não existe.
	 */
	public int outFilling(int direction) {
		switch(piece) {
		case 0:
			if (direction == 2)	return 2;
			if (direction == 0) return 0;
			break;
		case 1:
			if (direction == 2)	return 1;
			if (direction == 1) return 2;
			break;
		case 2:
			if (direction == 1)	return 0;
			if (direction == 2) return 3;
			break;
		case 3:
			if (direction == 0)	return 0;
			if (direction == 1) return 1;
			if (direction == 2)	return 2;
			if (direction == 3) return 3;
			break;
		case 4:
			if (direction == 0)	return 1;
			if (direction == 3) return 2;
			break;
		case 5:
			if (direction == 1)	return 2;
			if (direction == 0) return 3;
			break;
		case 6:
			if (direction == 1)	return 1;
			if (direction == 3) return 3;
			break;
		}
		return -1;
	}
	
	/**
	 * Check para verificar se é possivel substituir a peça ou ela ja esta a ser enchida.
	 * @return boolean que representa se está vaziu ou não.
	 */
	public boolean isEmpty() {
		if( fullness < 0.001)
			return true;
		return false;
	}

	/**
	 * Setter para parametro que define o tipo de peça.
	 * @param piece tipo de peça.
	 */
	public void setPiece(int piece) {
		this.piece = piece;
	}
}
