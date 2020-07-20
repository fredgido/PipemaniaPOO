package pipe;

import processing.core.PApplet;

public class TimeLeft {
	private  int x, y, width , height,timeout = 5,timeongoing = 0  ;
	PApplet pa;
	private int color;
	
	public TimeLeft(int x, int y, int width, int height, PApplet pa, int color,int timeout) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.pa = pa;
		this.color = color;
		this.timeout = timeout;
	}
	
	public void draw() {
		pa.stroke(255);
		pa.fill(0);
		pa.rect(x, y, width,height);
		pa.fill(color);
		pa.noStroke();
		pa.rect(x+1,    (int)(y+ height* (((double) timeongoing)        /(double) timeout)),
				width-1,(int)(   height*((((double) timeout-timeongoing)/(double) timeout))));
	}
	
	public int getTimeout() {						return timeout;	}

	public int getTimeongoing() {					return timeongoing;	}

	public void setTimeongoing(int timeongoing) {	this.timeongoing = timeongoing;	}
}
