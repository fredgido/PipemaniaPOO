package pipe;

import processing.core.PApplet;

public class PipeStart extends Pipe{
	public PipeStart(int ix, int iy, int iside, PApplet ipa, boolean iup, boolean idown, boolean iright, boolean ileft) {
		super(ix, iy, iside, ipa, iup, idown, iright, ileft);
		this.setColor(0, 150, 0);
	}
	/**
	 * Função para retornar se o objecto é vaziu, se é possivel colocar pipes lá. Não é possivel mover ou destruir esta peça.
	 * @return so retorna true quando é vaziu e permite alterações, logo neste caso é sempre falso.
	 */
	@Override
	public boolean isEmpty() {return false;}
}
