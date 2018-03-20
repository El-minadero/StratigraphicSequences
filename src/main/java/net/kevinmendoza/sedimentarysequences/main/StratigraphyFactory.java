package net.kevinmendoza.sedimentarysequences.main;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.IGeologyFactory;
import net.kevinmendoza.sedimentarysequences.configuration.ConfigBind;
import net.kevinmendoza.sedimentarysequences.stratigraphy.ColumnFactory;

class StratigraphyFactory implements IGeologyFactory {
	
	private ColumnFactory factory;
	private HashMap<Long, IGeology> stratigrapyMap;
	
	public StratigraphyFactory() {
		Injector injector = Guice.createInjector(new ConfigBind());
		factory = new ColumnFactory(injector);
		stratigrapyMap=new HashMap<>();
	}

	public IGeology getIGeology(long seed,boolean newInstance) {
		if(newInstance) {
			return factory.createColumn(seed);
		}
		else {
			return returnMapValue(seed);
		}
	}


	private IGeology returnMapValue(long seed) {
		if(stratigrapyMap.containsKey(seed)) {
			return stratigrapyMap.get(seed);
		}
		else {
			return createNewInstance(seed);
		}
	}

	private IGeology createNewInstance(long seed) {
		IGeology geology = factory.createColumn(seed);
		stratigrapyMap.put(seed, geology);
		return geology;
	}

}
