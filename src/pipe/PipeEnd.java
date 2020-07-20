package pipe;

import processing.core.PApplet;

public class PipeEnd extends Pipe{
	public PipeEnd(int ix, int iy, int iside, PApplet ipa, boolean iup, boolean idown, boolean iright, boolean ileft) {
		super(ix, iy, iside, ipa, iup, idown, iright, ileft);
		this.setColor(150, 0, 0);
	}
	/**
	 * Fun��o para retornar se o objecto � vaziu, se � possivel colocar pipes l�. N�o � possivel mover ou destruir esta pe�a.
	 * @return so retorna true quando � vaziu e permite altera��es, logo neste caso � sempre falso.
	 */
	@Override
	public boolean isEmpty() {return false;}
}
