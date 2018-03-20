package net.kevinmendoza.sedimentarysequences.stratigraphy;

import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.RockData;
import net.kevinmendoza.sedimentarysequences.configuration.ILayerDefaults;
import net.kevinmendoza.sedimentarysequences.configuration.IStratigraphicDefaults;

class LayerFactory {

	@Inject
	private ILayerDefaults layerDefaults;

	private long seed;
	
	ILayer createLayer() {
		IData data = layerDefaults.getData(seed);
		int thickness=layerDefaults.getThickness(seed,data);
		return new Layer.Builder()
				.setData(data)
				.setThickness(thickness)
				.build();
	}
	
	public ILayer createDefaultLayer(int thickness) {
		return new Layer.Builder()
				.setData(new RockData.Builder().build())
				.setThickness(thickness)
				.build();
	}

	public void setSeed(long seed) {
		this.seed=seed;
	}

}
