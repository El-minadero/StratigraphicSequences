package net.kevinmendoza.sedimentarysequences.configuration;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class StratigraphicDefaults {
	public static final TypeToken<StratigraphicDefaults> type = TypeToken.of(StratigraphicDefaults.class);
	
	@Setting
	private OffsetMapDefaults offsetDefaults;
	@Setting
	private GlobalDefaults globalDefaults;
	@Setting
	private FormationSequenceDefaults sequenceDefaults;
	
	public StratigraphicDefaults() {
		offsetDefaults  = new OffsetMapDefaults();
		globalDefaults = new GlobalDefaults();
		sequenceDefaults = new FormationSequenceDefaults();
	}
}
