package pipe;

import processing.core.PApplet;

public class Scoreboard {
	protected int score;
	protected int x, y, level;
	protected PApplet pa;
	protected static String message = "";
	
	public Scoreboard(int ix, int iy, int ilevel, PApplet ipa) {
		score = 0;
		x = ix;
		y = iy;
		setLevel(ilevel);
		pa = ipa;
		message= "".toString();
	}
	
	public void draw() {
		pa.fill(148,156,209);
		pa.rect(x, y - 20, x + 450, y + 10);
		pa.textSize(30);
		pa.fill(0,0,0);
		pa.text("Score: ", x - 50, y + 10);
		pa.textSize(30);
		pa.fill(0,0,0);
		pa.text(score, x + 45, y + 12);
		pa.fill(0,0,0);
		pa.text("Lvl " + getLevel(), x + 430, y + 12);

		pa.text(message, x + 160, y + 12);
	
	}
	
	public void bombUsed(){			score += -20;}

	public void pipeFilled(){		score += 100;}

	public void displayWin(){		message = "YOU WIN".toString();}
	
	public void displayLoss(){		message = "GAME OVER".toString();}
	
	public void displayClear(){ 	message = "".toString();}

	public int getLevel() {	    	return level;}

	public void setLevel(int level){this.level = level;}
}
