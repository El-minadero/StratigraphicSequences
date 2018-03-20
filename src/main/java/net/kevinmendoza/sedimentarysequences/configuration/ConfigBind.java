package net.kevinmendoza.sedimentarysequences.configuration;

import com.google.inject.AbstractModule;

public class ConfigBind extends AbstractModule {

	@Override
	protected void configure() {
		bind(IStratigraphicDefaults.class).to(StructureDefaults.class);
		bind(ILayerDefaults.class).to(LayerDefaults.class);
	}

}
