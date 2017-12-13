package net.kevinmendoza.sedimentarysequences.stratigraphy;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

class Stratigraphy implements IGeology {

	private final Order order;
	private final NoiseMap offsetMap;
	private final FormationSequence formationSequence;
	
	private Stratigraphy(StratigraphyBuilder builder) {
		order 	 = builder.getOrder();
		offsetMap= builder.getOffsetMap();
		formationSequence= builder.getFormations();
	}
	@Override
	public int getRGBDebugAtCoordinates(Vector3i query) {
		return 0;
	}
	
	public Order getOrder() 				{ return order;								}
	public final ISingularGeologyData getStartingData(ISingularGeologyData dataType) {
		return EmptyDataFactory.getEmptyDataObject(dataType.getID());
	}
	public ISingularGeologyData get2DGeologyData(ISingularGeologyData testDat,
			Vector2i query) { return EmptyDataFactory.getEmptyDataObject(testDat.getID()); }
	public void loadNearbyNodes(GenerationData metaData) { }
	
	public void primeLoadedObjects(GenerationData metaData) { 
		Vector2i center = metaData.get2DCoordinate();
		int offset = (int) offsetMap.getNoise(center);
		formationSequence.offsetInquiries(offset);
	}

	@Override
	public ISingularGeologyData get3DGeologyData(ISingularGeologyData testDat,
			Vector3i query) {
		if(testDat.getID()==3) {
			return formationSequence.getRock(query);
		}
		else {
			return EmptyDataFactory.getEmptyDataObject(testDat.getID());
		}
	}

	public static class StratigraphyBuilder {
		
		private Order order;
		private FormationSequence formation;
		private NoiseMap offsetMap;
		
		public StratigraphyBuilder() {}
		public StratigraphyBuilder setOrder(Order order) 			{ this.order = order; return this; }
		public StratigraphyBuilder setFormation(FormationSequence formation){ this.formation=formation; return this; }
		public StratigraphyBuilder setOffsetMap(NoiseMap map) 		{ this.offsetMap = map; return this;}
		
		public IGeology build() {
			return new Stratigraphy(this);
		}
		
		public Order getOrder() { return order; }
		public FormationSequence getFormations() { return formation;}
		public NoiseMap getOffsetMap() { return offsetMap; }
		
	}

	@Override
	public String getLocationData(Vector3i globalVector) {
		return "Stratigraphic Sequence at Location:\n" +
				"GeologicOffset:" + offsetMap.getNoise(globalVector) + "\n"
				+ formationSequence.toString();
	}

}
