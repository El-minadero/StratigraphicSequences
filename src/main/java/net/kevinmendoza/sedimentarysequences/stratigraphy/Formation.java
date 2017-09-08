package net.kevinmendoza.sedimentarysequences.stratigraphy;

import java.util.LinkedList;
import java.util.Random;

import com.flowpowered.math.vector.Vector3i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.sedimentarysequences.configuration.IFormationSequenceDefaults;
import net.kevinmendoza.sedimentarysequences.configuration.ILayer;

public class Formation {

	@Inject
	private IFormationSequenceDefaults formationDefaults;

	private int totalLayerThickness;
	
	private LinkedList<String> layers;

	private Random rand;
	
	public Formation() {
		this.layers = new LinkedList<>();
	}
	
	void setSeed(long seed) {
		this.rand = new Random(seed);
	}
	
	ISingularGeologyData getRock(Vector3i newQuery) {
		String rockName = layers.get(newQuery.getY());
		return DefaultDataFactory.getRock(rockName, 0);
	}

	void prepare(int end) {
		while(totalLayerThickness<end+1) {
			ILayer layer = formationDefaults.getNewLayer(rand);
			int layerThickness = layer.getThickness();
			totalLayerThickness+=layerThickness;
			for(int i = 0; i<layerThickness;i++) {
				layers.add(layer.getRock());
			}
		}
	}
}
