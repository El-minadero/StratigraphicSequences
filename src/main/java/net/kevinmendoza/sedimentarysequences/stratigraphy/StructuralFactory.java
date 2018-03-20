package net.kevinmendoza.sedimentarysequences.stratigraphy;

import com.google.inject.Inject;

import net.kevinmendoza.sedimentarysequences.configuration.IStratigraphicDefaults;

class StructuralFactory {
	
	@Inject
	IStratigraphicDefaults stratigraphicDefaults;
	long seed; 
	
	public void setSeed(long seed) {
		this.seed=seed;
	}

	public int getLayerNumber() {
		return stratigraphicDefaults.getNumberOfLayers(seed);
	}

}
