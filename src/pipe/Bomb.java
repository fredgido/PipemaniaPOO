package pipe;

import processing.core.*;

public class Bomb extends Cell{
	protected long creationTime = System.currentTimeMillis();
	protected int bombTimer = 3000;
	
	public Bomb(int ix, int iy, int iside, PApplet ipa) {
		super(ix, iy, iside, ipa);
	}
	
	/**
	 * Verificar se a peça é removivel.
	 * @return boolean que simboliza se a peça é editavel.
	 */
	public boolean isEmpty() {
		if (System.currentTimeMillis() - creationTime > bombTimer) 
			return true;
		return false;
	}
	
	/**
	 * Desenhar a peça
	 */
	@Override
	public void draw() {
		pa.fill(0,0,0);
		pa.stroke(255);
		pa.square(x, y, side);
		
		if (System.currentTimeMillis() - creationTime < bombTimer / 2) {
			pa.strokeWeight(2);
			pa.fill(0,0,0);
			pa.circle(x + side / 2, y + side /2, 2*side / 3);
			

			pa.stroke(255,255,50);
			pa.strokeWeight(4);
			pa.line(x + side*2/3, y+ side*1/3, x + side*7/8, y + side*1/8);
			pa.strokeWeight(1);
		} else if(System.currentTimeMillis() - creationTime > bombTimer / 2 && System.currentTimeMillis() - creationTime < bombTimer) {
			pa.fill(255,0,0);
			pa.stroke(255);
			pa.square(x, y, side);
		}
	}
}
