package net.kevinmendoza.sedimentarysequences.stratigraphy;

import org.spongepowered.api.item.inventory.type.InventoryColumn;

import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;

public class ColumnFactory {
	
	private LayerFactory layerFactory;
	private StructuralFactory structuralFactory;

	public ColumnFactory(Injector injector) {
		layerFactory = injector.getInstance(LayerFactory.class);
		structuralFactory=injector.getInstance(StructuralFactory.class);
	}
	
	public IGeology createColumn(long seed) {
		layerFactory.setSeed(seed);
		structuralFactory.setSeed(seed);
		int layerNumber = structuralFactory.getLayerNumber();
		Column.Builder builder = new Column.Builder();
		for(	int layerNumbers=0;layerNumbers<layerNumber;layerNumbers++) {
			builder.addLayer(layerFactory.createLayer());
		}
		return builder.build();
	}
	
}
