package net.kevinmendoza.sedimentarysequences.configuration;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.MultiplierMapConfiguration;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapConfiguration;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class OffsetMapDefaults implements IOffsetMapDefaults {
	public static final TypeToken<OffsetMapDefaults> type = TypeToken.of(OffsetMapDefaults.class);

	@Setting
	private MultiplierMapConfiguration multiplierMap;

	public OffsetMapDefaults() {
		double[] frequencies = { 15000,1200, 500};
		double[] weights 	 = { 3,1,2};
		NoiseMapConfiguration noiseMap = new NoiseMapConfiguration(frequencies,weights);
		int maximumOffset = 700;
		multiplierMap = new MultiplierMapConfiguration(noiseMap,maximumOffset);
	}
	
	@Override
	public NoiseMap getOffsetMap(long seed) {
		return multiplierMap.getMultiplierMap(seed);
	}

}
