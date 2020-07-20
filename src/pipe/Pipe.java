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
	 * Desenhar a pe�a
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
	 * Fun��o para encher a pe�a com agua, se j� cheio retorna que est� cheio.
	 * @param fillRate percentagem de agua que a pe�a vai encher. 
	 * @return bollean se est� cheia.
	 */
	public int fill(double fillRate) {
		if (fullness < 1)
			fullness += fillRate;
		else
			return 1;
		return 0;
	}
	

	/**
	 * Fun��o para descodificar para que direc��o a agua vai sair ao 
	 * entrar por outra em cada pe�a. retorna -1 se for a entrada for incompativel.
	 * @param direction direc��o, em rela��o a pe�a anterior, para qual a agua vai.
	 * @return int representa o sentido, se -1 significa que n�o existe.
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
	 * Check para verificar se � possivel substituir a pe�a ou ela ja esta a ser enchida.
	 * @return boolean que representa se est� vaziu ou n�o.
	 */
	public boolean isEmpty() {
		if( fullness < 0.001)
			return true;
		return false;
	}

	/**
	 * Setter para parametro que define o tipo de pe�a.
	 * @param piece tipo de pe�a.
	 */
	public void setPiece(int piece) {
		this.piece = piece;
	}
}
