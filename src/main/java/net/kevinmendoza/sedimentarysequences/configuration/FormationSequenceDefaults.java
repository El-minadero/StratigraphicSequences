package net.kevinmendoza.sedimentarysequences.configuration;

import java.util.HashMap;
import java.util.Random;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class FormationSequenceDefaults implements IFormationSequenceDefaults {
	public static final TypeToken<FormationSequenceDefaults> type = TypeToken.of(FormationSequenceDefaults.class);
	
	@Setting
	private HashMap<String,LayerDefaults> layers;
	
	public FormationSequenceDefaults() {
		layers = new HashMap<>();
		
		LayerDefaults mudstone  = new LayerDefaults("MUDSTONE",15,3,10,0.6);
		LayerDefaults sandstone = new LayerDefaults("SANDSTONE",2,3,10,1-0.6-0.06);
		LayerDefaults conglomerate = new LayerDefaults("CONGLOMERATE",1,1,3,0.06);
		//LayerDefaults bandedIronFormation = new LayerDefaults("BANDED_IRON_FORMATION",1,1,3,0.0);
		//LayerDefaults coalSeam = new LayerDefaults("COAL_SEAM",1,1,3,0.06);
		
		layers.put("MUDSTONE", 	mudstone);
		layers.put("SANDSTONE", 	sandstone);
		layers.put("CONGLOMERATE",conglomerate);
	}
	
	@Override
	public ILayer getNewLayer(Random rand) {
		ILayer layer = getLayer(rand);
		return layer;
	}

	private ILayer getLayer(Random rand) {
		LayerDefaults layer = null;
		while(layer==null) {
			for(String key : layers.keySet()) {
				double val = rand.nextDouble();
				if(val<layers.get(key).getProbability()) {
					layer = layers.get(key);
					break;
				}
			}
		}
		return layer.getLayer(rand);
	}

}
