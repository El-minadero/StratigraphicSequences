package net.kevinmendoza.sedimentarysequences.configuration;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class LayerDefaults implements ILayerDefaults {
	
	@Setting
	AssemblageDefaults sedimentaryAssemblage;
	@Setting
	StructureDefaults structure;

	@Override
	public IData getData(long seed) {
		return sedimentaryAssemblage.getData(seed);
	}

	@Override
	public int getThickness(long seed, IData data) {
		return sedimentaryAssemblage.getThickness(seed);
	}

}
