package net.kevinmendoza.sedimentarysequences.configuration;

import java.util.List;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class StructureDefaults implements IStratigraphicDefaults {
	public static final TypeToken<StructureDefaults> type = TypeToken.of(StructureDefaults.class);

	@Setting
	private IntMinMax layerNumberRange;
	
	public StructureDefaults() {
		
		layerNumberRange	= new IntMinMax(50, 300);
	}

	@Override
	public int getNumberOfLayers(long seed) { return layerNumberRange.getValue(seed); }
	
}
