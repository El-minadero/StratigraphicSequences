package net.kevinmendoza.sedimentarysequences.configuration;

import com.google.inject.AbstractModule;

public class ConfigBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGlobalDefaults.class).to(GlobalDefaults.class);
		bind(IFormationSequenceDefaults.class).to(FormationSequenceDefaults.class);
		bind(IOffsetMapDefaults.class).to(OffsetMapDefaults.class);
	}

}
