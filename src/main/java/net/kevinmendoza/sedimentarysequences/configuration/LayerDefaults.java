package net.kevinmendoza.sedimentarysequences.configuration;

import java.util.Random;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class LayerDefaults {
	public static final TypeToken<LayerDefaults> type = TypeToken.of(LayerDefaults.class);
	
	@Setting
	private String rockName;
	@Setting
	private IntMinMax intMinMax;
	@Setting
	private double probability;
	@Setting
	private int variants;

	public LayerDefaults(String rockName, int variants,int minThickness, int maxThickness, double probability) {
		this.rockName = rockName;
		this.variants = variants;
		this.intMinMax = new IntMinMax(minThickness,maxThickness);
		this.probability = probability;
	}

	public double getProbability() { return probability; }

	public ILayer getLayer(Random rand) {
		int variant = rand.nextInt(variants);
		int width   = intMinMax.getValue(rand);
		return new SimpleLayer(rockName + ":"+ variant,width);
	}

}
