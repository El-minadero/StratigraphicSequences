package net.kevinmendoza.sedimentarysequences.stratigraphy;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.sedimentarysequences.configuration.ConfigBind;
import net.kevinmendoza.sedimentarysequences.stratigraphy.FormationSequence.FormationSequenceBuilder;

public class StratigraphyHub {

	private static HashMap<Long,IGeology> stratigraphicMap;
	
	private static Injector injector;

	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ConfigBind());
		}
		return injector;
	}
	
	public static IGeology GetSedimentarySequence(long seed) {
		if(stratigraphicMap==null)
			stratigraphicMap = new HashMap<>();
		if(!stratigraphicMap.containsKey(seed)) {
			StratigraphyFactory stratigraphyFactory = GetInjector().getInstance(StratigraphyFactory.class);
			stratigraphicMap.put(seed,stratigraphyFactory.buildStratigraphy(seed));
		}
		return stratigraphicMap.get(seed);
	}

	static FormationSequence GetFormationSequence(long seed) {
		FormationSequenceBuilder builder = new FormationSequenceBuilder();
		Formation formation1 = GetInjector().getInstance(Formation.class);
		Formation formation2 = GetInjector().getInstance(Formation.class);
		formation1.setSeed(seed);
		formation2.setSeed(seed+1);
		return builder
				.setAboveFormation(formation1)
				.setBelowFormation(formation2)
				.build();
	}

}
