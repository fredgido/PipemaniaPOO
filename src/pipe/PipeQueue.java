package pipe;

import processing.core.PApplet;
import java.util.*;

public class PipeQueue {
	protected int n, cellSize, x, y;
	protected LinkedList<Cell> nextCells = new LinkedList<Cell>();
	protected PApplet pa;
	
	public PipeQueue(int iN, int iCellSize, int iX, int iY, PApplet iPa) {
		this.n = iN;
		this.cellSize = iCellSize;
		this.x = iX;
		this.y = iY;
		this.pa = iPa;
		
		for (int i = n-1; i >= 0; i--) {
			nextCells.add(new Pipe(x, y + i * cellSize, cellSize, pa));
		}
	}
	
	/**
	 * Espreitar o proximo a sair.
	 * @return retorna a proxima peça a sair.
	 */
	public Cell peek() {
		return nextCells.peek();
	}

	/**
	 * Retirar o primeiro e colocar e criar um novo ultimo a sair.
	 */
	public void cycle() {
		nextCells.removeFirst();
		nextCells.forEach(Cell -> Cell.setY(Cell.getY() + 50));
		nextCells.add(new Pipe(x, y, cellSize, pa));
	}
	
	/**
	 * Desenhar todas as peças em queue peça
	 */
	public void draw() {
		nextCells.forEach(Cell -> Cell.draw());
	}
}
