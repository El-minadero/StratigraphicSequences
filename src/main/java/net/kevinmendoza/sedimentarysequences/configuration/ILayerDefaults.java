package net.kevinmendoza.sedimentarysequences.configuration;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

public interface ILayerDefaults {

	IData getData(long seed);

	int getThickness(long seed, IData data);

}
