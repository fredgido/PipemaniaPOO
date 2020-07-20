package pipe;

import processing.core.PApplet;

public class Obstacle extends Cell{

	public Obstacle(int ix, int iy, int iside, PApplet ipa){
		super(ix, iy, iside, ipa);
	}
	
	@Override
	public void draw() {
		pa.fill(132,132,132);
		pa.stroke(255);
		pa.strokeWeight(2);
		pa.square(x, y, side);
		pa.line(x, y, x + side, y + side);
		pa.line(x, y + side, x + side, y);
		pa.strokeWeight(1);
	}
	/**
	 * Fun��o para retornar se o objecto � vaziu, se � possivel colocar pipes l�.
	 * @return so retorna true quando � vaziu e permite altera��es, logo neste caso � sempre falso.
	 */
	public boolean isEmpty() {return false;}
}
