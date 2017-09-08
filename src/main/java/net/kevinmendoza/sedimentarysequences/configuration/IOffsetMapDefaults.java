package net.kevinmendoza.sedimentarysequences.configuration;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public interface IOffsetMapDefaults {

	NoiseMap getOffsetMap(long seed);

}
