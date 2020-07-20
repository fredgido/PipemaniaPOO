package pipe;

import processing.core.PApplet;

public class Cell extends PApplet{
	protected int x, y, side;
	protected PApplet pa;
	protected int color;
	
	public Cell(int ix, int iy, int iside, PApplet ipa) {
		this.x = ix;
		this.y = iy;
		this.side = iside;
		this.pa = ipa;
	}

	/**
	 * Fun��o para retornar se o objecto � vaziu, se � possivel colocar pipes l�.
	 * @return retorna true quando � vaziu e permite altera��es.
	 */
	public boolean isEmpty() {	return true;}

	public void draw() {
		pa.fill(color);
		pa.stroke(255);
		pa.square(x, y, side);
	}
	
	public int getX() {	return x;}

	public void setX(int x) { this.x = x;}

	public int getY() {	return y;}

	public void setY(int y) { this.y = y;}

	public int getColor() {	return color;}

	public void setColor(int r, int g, int b) {	this.color = color(r,g,b);}
	
	public int getSide() {	return side;}

	public void setSide(int side) {	this.side = side;}
}
