package net.kevinmendoza.sedimentarysequences.configuration;

public class SimpleLayer implements ILayer {
	
	private int width;
	private String rockName;
	
	public SimpleLayer(String rockName, int width) {
		this.rockName = rockName;
		this.width = width;
	}

	public int getThickness() { return width;    }
	public String getRock()   { return rockName; }

}
