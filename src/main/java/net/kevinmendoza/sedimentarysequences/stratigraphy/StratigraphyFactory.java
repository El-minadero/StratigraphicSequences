package net.kevinmendoza.sedimentarysequences.stratigraphy;

import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.sedimentarysequences.configuration.IGlobalDefaults;
import net.kevinmendoza.sedimentarysequences.configuration.IOffsetMapDefaults;
import net.kevinmendoza.sedimentarysequences.stratigraphy.Stratigraphy.StratigraphyBuilder;
class StratigraphyFactory {

	@Inject
	private IGlobalDefaults globalDefaults;
	@Inject
	private IOffsetMapDefaults mapDefaults;

	public IGeology buildStratigraphy(long seed) {
		StratigraphyBuilder builder = new StratigraphyBuilder();
		FormationSequence sequence = StratigraphyHub.GetFormationSequence(seed);
		builder.setOrder(globalDefaults.getOrder())
				.setOffsetMap(mapDefaults.getOffsetMap(seed))
				.setFormation(sequence);
		return builder.build();
	}

}
