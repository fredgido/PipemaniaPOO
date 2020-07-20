package pipe;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {
	
	@Test
	public void pipquetest() {
		PipeQueue a = new PipeQueue(5, 5, 5, 5, null);
		assertEquals(a.n,5);
		assertEquals(a.x,5);
		assertEquals(a.y,5);
		assertEquals(a.cellSize,5);
		//assertEquals(a.nextCells.peek().toString(),new Pipe(5, 5 + 5 * 5, 5, null).toString());
		
	}

}
