package net.kevinmendoza.sedimentarysequences.stratigraphy;

import org.spongepowered.api.data.manipulator.immutable.block.ImmutableLayeredData;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

public class Layer implements ILayer {

	private final int thickness;
	private final IData data;

	private Layer(Builder builder) {
		thickness=builder.getThickness();
		data    =builder.getData();
	}
	
	public int getThickness() { return thickness; }
	
	public IData getData() {	return data; }

	public static class Builder {
		
		private int thickness;
		private IData data;
		
		public Builder() {
			thickness=2;
		}
		
		public Builder setThickness(int thick) {
			this.thickness = thick;
			return this;
		}
		public Builder setData(IData data) {
			this.data = data;
			return this;
		}
		public IData getData() 		{ return data; 		}
		public int getThickness() 	{ return thickness; 	}
		
		public ILayer build() {
			return new Layer(this);
		}

	}

}
