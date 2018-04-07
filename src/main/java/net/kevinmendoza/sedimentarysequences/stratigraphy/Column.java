package net.kevinmendoza.sedimentarysequences.stratigraphy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.inject.spi.PrivateElements;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.BulkComposition;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.RockData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.Texture;

public class Column implements IColumn {
	private final int thickness;
	private final List<IData> layers;
	private static final IData fillerRock = new RockData.Builder()
									.setTexture(Texture.Clastic_Fine)
									.setBulkComposition(BulkComposition.Silicate)
									.build();
	
	private Column(Builder builder) {
		layers    		= populateLayers(builder.getLayers());
		thickness 		= layers.size();
	}
	
	@Override
	public IData getData(Vector2i vector2i) {
		return fillerRock;
	}

	@Override
	public IData getData(Vector3i vector3i) {
		return getData(vector3i.getY());
	}

	
	private List<IData> populateLayers(List<ILayer> layerList) {
		List<IData> dataList = new ArrayList<>();
		for(ILayer layer : layerList) {
			int dh = layer.getThickness();
			IData data = layer.getData();
			for(int i =0;i<dh;i++) {
				dataList.add(data);
			}
		}
		return dataList;
	}

	public final IData getData(int depth) {
		int queryElevation = depth;
		if(!isInside(depth))
			return fillerRock;
		return layers.get(queryElevation);
	}
	
	public final boolean isInside(int depth) {
		return (depth >0 && depth <=thickness);
	}

	
	
	public static class Builder {

		private ArrayList<ILayer> layers;
		
		public Builder() {
			layers = new ArrayList<>();
		}

		public List<ILayer> getLayers() { return layers; }
		
		public Builder addLayer(ILayer layer) {
			layers.add(layer); return this;
		}
		
		public Builder addLayers(List<ILayer> layers) {
			this.layers.addAll(layers); return this;
		}
		
		public IColumn build() {
			return new Column(this);
		}
		
	}

}
