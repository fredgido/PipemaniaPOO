package pipe;

import processing.core.*;
import java.util.*;

public class Board extends PApplet{
	private int nWidth, nHeight, cellSize, x, y; // parametros da board
	private HashMap<Integer, Cell> actCells = new HashMap<Integer, Cell>(); //estrutura que contem as peças
	PApplet pa; 
	Pipe currentFillingPipe; //Pipe actualmente a ser enchido
	int currentFillingDirection; //Direcção do enchimento seguinte
	int winloss=0; //variavel de check de conclusão do board
	
	/**
	 * Construtor da board, todas as caracteristicas necessárias são satisfeitas. 
	 * @param  nWidth Numero peças na horizontal.
	 * @param  nHeight Numero peças na vertical.
	 * @param  cellSize Largura das peças.
	 * @param  x posição no ecra do canto superior esquerdo da board.
	 * @param  y posição no ecra do canto superior esquerdo da board.
	 * @param  ipa Instancia necessária para desenhar.
	 * @param  level nivel actual do jogo.
	 * @param  sc Scanner para definir as peças em si.
	 */
	public Board(int nWidth, int nHeight, int cellSize, int x, int y, PApplet ipa,int level, Scanner sc) {
		super();
		this.nWidth = nWidth;
		this.nHeight = nHeight;
		this.cellSize = cellSize;
		this.x = x;
		this.y = y;
		this.pa = ipa;
		
		//duplo for necessário para prencher a board toda de celulas.
		for (int i = 0; i < nWidth; i++) {
			for(int j = 0; j < nHeight; j++) {
				pa.square(x + i * cellSize, y + j * cellSize, cellSize);
				actCells.put(i + j * nWidth, new Cell(x + i * cellSize, y + j * cellSize, cellSize, pa));
			}
		}
		
		while (sc.hasNext()) {
			if(sc.nextInt() == level) { 
				String inputobject = sc.next();
		        if(inputobject.equals("start")){// Colocar start
		        	int idx = sc.nextInt() + sc.nextInt() * nWidth;
		        	replaceInPlace(actCells.get(idx), new PipeStart(0, 0, cellSize, ipa, sc.nextBoolean(), sc.nextBoolean(), sc.nextBoolean(), sc.nextBoolean()));
		        	currentFillingPipe = (Pipe)  actCells.get(idx);
		        	currentFillingPipe.setPiece(sc.nextInt());
		        	currentFillingDirection = sc.nextInt();
		        }
		        
		        if(inputobject.equals("end")) // Colocar pipeend
		        	replaceInPlace(actCells.get(sc.nextInt() + sc.nextInt()*nWidth), new PipeEnd(0, 0, cellSize, ipa, sc.nextBoolean(), sc.nextBoolean(), sc.nextBoolean(), sc.nextBoolean()));

		        if(inputobject.equals("obstacle")) // Colocar obstaculos
		        		this.replaceInPlace(actCells.get(sc.nextInt() + sc.nextInt()*nWidth), new Obstacle(0, 0, cellSize, ipa));
		      }else sc.nextLine();
		 }
			
	}
	
	
	/**
	 * Função para avançar no jogo, enche a peça activa ou quando cheia escolhe e troca para a proxima.
	 * quando cheia escolhe e troca para a proxima.
	 * @param  waterRise  Quantidade de agua a encher.
	 * @return retorna true quando o pipe actual encheu.
	 */
	public Boolean fill(double waterRise) {
		boolean wasfilled = false;
		if (currentFillingPipe == null) {
			return false;
		}
		if (currentFillingPipe instanceof Pipe) {
			if (currentFillingPipe.fill(waterRise) == 1) {
				wasfilled = true;
				if (getNeighbour(currentFillingPipe, currentFillingDirection) == null) { winloss = -1; return false;}
				if (getNeighbour(currentFillingPipe, currentFillingDirection).getClass().getSimpleName().contains("Pipe")) {
					currentFillingPipe = fillNext(currentFillingDirection);
					currentFillingDirection = currentFillingPipe.outFilling(currentFillingDirection);
				} else {
					currentFillingPipe = null;
					winloss = -1;
					return false;
				}
				if (currentFillingDirection < 0) {
					if (currentFillingPipe.getClass().getSimpleName().equals("PipeEnd")) {
						winloss = 1;
						currentFillingPipe.fill(1.0);
					} else
						winloss = -1;
				}
			}
		}
		return wasfilled;
	}
	

	/**
	 * Função para subsituir uma peça nova na mesma posição de uma conhecida.
	 * @param  oldCell Peça anterior a ser substituida.
	 * @param  newCell Nova peça que fica no sitio da oldCell.
	 */
	public void replaceInPlace(Cell oldCell, Cell newCell) {
		newCell.setX(oldCell.getX());
		newCell.setY(oldCell.getY());
		newCell.setSide(oldCell.getSide());;
		int index = getCellIdx(newCell.getX() , newCell.getY());
		actCells.remove(index);
		actCells.put(index, newCell);
	}
	
	/**
	 * Desenhar a board.
	 */
	public void draw() {
		actCells.forEach((index,cell) -> cell.draw());
	}
	
	/**
	 * Função para determinar se o rato está dentro da board.
	 * @return retorna true quando o rato está dentro da board..
	 */
	public boolean isInside() {
		if (pa.mouseX > x && pa.mouseX < x+cellSize * nWidth && 
			pa.mouseY > y && pa.mouseY < y + cellSize * nHeight) {
			return true;
		}
		return false;	
	}
	
	/**
	 * Função para determinaso indice de uma peça apartir de uma localização na janela.
	 * @param ix posição x na janela
	 * @param iy posição y na janela
	 * @return indice da peça
	 */
	public int getCellIdx(int ix, int iy) {
		int nX = (ix - x) / cellSize;
		int nY = (iy - y) / cellSize;
		
		return nX + nY * nWidth;
	}
	
	/**
	 * Getter para as peças da board.
	 * @param ix posição x na janela
	 * @param iy posição y na janela
	 * @return Peça Cell na posição.
	 */
	public Cell getCellObj(int ix, int iy) {
		return actCells.get(getCellIdx( ix,  iy));
	}
	
	/**
	 * Função para determinar a peça vizinha de uma outra em uma determinada direção.
	 * @param c celula a qual queremos determinar a vizinha.
	 * @param direction para onde se encontra a vizinha.
	 * @return Peça Cell vizinha.
	 */
	public Cell getNeighbour(Cell c, int direction) {
		int nX = (c.getX() - x)/cellSize;
		int nY = (c.getY() - y)/cellSize;
		if(direction == 0) nY -= 1;
		if(direction == 1) nX += 1;
		if(direction == 2) nY += 1;
		if(direction == 3) nX -= 1;
	return actCells.get(nX + (nY * nWidth));
	}
	
	/**
	 * Função para determinar se a peça vizinha é válida. e tratar se não for.
	 * @param c 
	 * @param direction para onde se encontra a vizinha.
	 * @return Peça Cell vizinha.
	 */
	public Pipe fillNext(int currentFillinDirArg) {
		Cell returncell = this.getNeighbour(currentFillingPipe, currentFillinDirArg);
		if(returncell.getClass().getSimpleName().equals("Pipe")) 
			return (Pipe) returncell;
		
		if(returncell.getClass().getSimpleName().equals("PipeStart")) {
			winloss = -1;
			return (Pipe) returncell;
		}
		if(returncell.getClass().getSimpleName().equals("PipeEnd")) {
			winloss = 1;
			return (Pipe) returncell;
		}	
		return null;
	}
}
