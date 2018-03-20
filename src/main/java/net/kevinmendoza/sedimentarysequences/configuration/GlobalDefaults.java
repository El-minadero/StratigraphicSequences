package net.kevinmendoza.sedimentarysequences.configuration;

import java.util.List;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class GlobalDefaults  {
	public static final TypeToken<GlobalDefaults> type = TypeToken.of(GlobalDefaults.class);
	@Setting
	StructureDefaults stratigraphicDefaults;
	@Setting
	LayerDefaults layerDefaults;
	
	public GlobalDefaults() {
		stratigraphicDefaults=new StructureDefaults();
		layerDefaults=new LayerDefaults();
	}
	
}
