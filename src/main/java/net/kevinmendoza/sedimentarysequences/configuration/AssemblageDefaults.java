package net.kevinmendoza.sedimentarysequences.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.BulkComposition;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.Texture;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.DoubleMinMax;
import net.kevinmendoza.sedimentarysequences.configuration.rockdata.ActivityDefaults;
import net.kevinmendoza.sedimentarysequences.configuration.rockdata.CompositionDefaults;
import net.kevinmendoza.sedimentarysequences.configuration.rockdata.MetalDefaults;
import net.kevinmendoza.sedimentarysequences.configuration.rockdata.Petrology;
import net.kevinmendoza.sedimentarysequences.configuration.rockdata.TextureDefaults;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class AssemblageDefaults {
	
	@Setting
	List<SedimentaryUnit> sedimentryUnits;
	
	private boolean isInitialized=false;
	
	
	AssemblageDefaults(){
		sedimentryUnits=new ArrayList<>();
		addMudstones();
		addSandstones();
		addGravels();
		addCoal();
		addBIFs();
		addAlluvialGold();
		addAlluvialDiamonds();
		addAlluvialEmeralds();
	}

	public IData getData(long seed) {
		checkAndDistributeWeights();
		Random random = new Random(seed);
		random.nextDouble();
		double val = random.nextDouble();
		for(SedimentaryUnit unit : sedimentryUnits) {
			if(unit.isInRange(val))
				return unit.getData(seed);
		}
		return null;
	}
	
	int getThickness(long seed) {
		checkAndDistributeWeights();
		Random random = new Random(seed);
		random.nextDouble();
		double val = random.nextDouble();
		for(SedimentaryUnit unit : sedimentryUnits) {
			if(unit.isInRange(val))
				return unit.getThickness(seed);
		}
		return 0;
	}


	private void checkAndDistributeWeights() {
		if(!isInitialized) {
			double totalWeight=0;
			for(SedimentaryUnit unit : sedimentryUnits) {
				totalWeight+=unit.getOccurenceWeight();
			}
			for(SedimentaryUnit unit : sedimentryUnits) {
				unit.normalize(totalWeight);
			}
			double bottomRange = 0;
			for(SedimentaryUnit unit : sedimentryUnits) {
				unit.setBottomRange(bottomRange);
				bottomRange+=unit.getOccurenceWeight();
			}
			isInitialized=true;
		}
	}


	private void addGravels() {
		sedimentryUnits.add(
				new SedimentaryUnit(15.0,
						"Conglomerate",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setSulfur(new DoubleMinMax(0.01, 0.1))
									.setWater( new DoubleMinMax(0.1, 0.4))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.build())
							.setTextureDefaults(new TextureDefaults(0.9, 
									Texture.Clastic_Coarse,
									Texture.Clastic_Coarse))
							.setCompositionDefaults(new CompositionDefaults(0.95, 
									BulkComposition.Silicate, 
									BulkComposition.Silicate))
							.Build(),
						new IntMinMax(2,5)));
	}

	private void addSandstones() {
		sedimentryUnits.add(
				new SedimentaryUnit(40.0,
						"Sandstones",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setWater( new DoubleMinMax(0.1, 0.9))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.setIron(new DoubleMinMax(0.01, 0.05))
									.setGold(new DoubleMinMax(0.01, 0.05))
									.build())
							.setTextureDefaults(new TextureDefaults(0.9, 
									Texture.Clastic_Medium,
									Texture.Clastic_Fine))
							.setCompositionDefaults(new CompositionDefaults(0.95, 
									BulkComposition.Silicate, 
									BulkComposition.Silicate))
							.Build(),
						new IntMinMax(2,20)));
	}

	private void addMudstones() {
		sedimentryUnits.add(
				new SedimentaryUnit(70.0,
						"Mudstones",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setSulfur(new DoubleMinMax(0.01, 0.2))
									.setWater( new DoubleMinMax(0.1, 0.5))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.setCarbon(new DoubleMinMax(0, 0.2))
									.setIron(new DoubleMinMax(0, 0.2))
									.build())
							.setTextureDefaults(new TextureDefaults(0.5, 
									Texture.Clastic_Fine,
									Texture.Clastic_Precipitate))
							.setCompositionDefaults(new CompositionDefaults(0.6, 
									BulkComposition.Clay, 
									BulkComposition.Carbonate))
							.Build(),
						new IntMinMax(4,20)));
	}
	
	private void addAlluvialEmeralds() {
		sedimentryUnits.add(
				new SedimentaryUnit(0.5,
						"Alluvial_Emeralds",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setWater( new DoubleMinMax(0.1, 0.2))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.setGold(new DoubleMinMax(0.01, 0.1))
									.setBeryllium(new DoubleMinMax(0.01, 0.1))
									.build())
							.setTextureDefaults(new TextureDefaults(0.9, 
									Texture.Clastic_Coarse,
									Texture.Clastic_Medium))
							.setCompositionDefaults(new CompositionDefaults(0.95, 
									BulkComposition.Silicate, 
									BulkComposition.Carbonate))
							.Build(),
						new IntMinMax(1,3)));
	}

	private void addAlluvialDiamonds() {
		sedimentryUnits.add(
				new SedimentaryUnit(1.0,
						"Alluvial_Diamonds",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setWater( new DoubleMinMax(0.1, 0.2))
									.setPressure(new DoubleMinMax(0.6, 1))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.setGold(new DoubleMinMax(0.01, 0.1))
									.setCarbon(new DoubleMinMax(0.3, 1))
									.setBeryllium(new DoubleMinMax(0.01, 0.1))
									.build())
							.setTextureDefaults(new TextureDefaults(0.9, 
									Texture.Clastic_Coarse,
									Texture.Clastic_Medium))
							.setCompositionDefaults(new CompositionDefaults(0.95, 
									BulkComposition.Silicate, 
									BulkComposition.Carbonate))
							.Build(),
						new IntMinMax(1,3)));
	}

	private void addAlluvialGold() {
		sedimentryUnits.add(
				new SedimentaryUnit(2.0,
						"Alluvial_Gold",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setSulfur(new DoubleMinMax(0, 0.1))
									.setWater( new DoubleMinMax(0.1, 0.2))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.setGold(new DoubleMinMax(0.3, 1))
									.build())
							.setTextureDefaults(new TextureDefaults(0.9, 
									Texture.Clastic_Coarse,
									Texture.Clastic_Medium))
							.setCompositionDefaults(new CompositionDefaults(0.95, 
									BulkComposition.Silicate, 
									BulkComposition.Carbonate))
							.Build(),
						new IntMinMax(1,3)));
	}


	private void addBIFs() {
		sedimentryUnits.add(
				new SedimentaryUnit(6.0,
						"Banded_Iron_Formation",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setSulfur(new DoubleMinMax(0, 0.1))
									.setWater( new DoubleMinMax(0.1, 0.2))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.setCarbon(new DoubleMinMax(0.01, 0.2))
									.setIron(new DoubleMinMax(0.3, 0.7))
									.build())
							.setTextureDefaults(new TextureDefaults(0.9, 
									Texture.Clastic_Fine,
									Texture.Clastic_Precipitate))
							.setCompositionDefaults(new CompositionDefaults(0.95, 
									BulkComposition.Clay, 
									BulkComposition.Silicate))
							.Build(),
						new IntMinMax(2,5)));
	}


	private void addCoal() {
		sedimentryUnits.add(
				new SedimentaryUnit(10.0,
						"Coal_Seam",
						new Petrology.Builder()
							.setActivityDefaults(new ActivityDefaults.Builder()
									.setSulfur(new DoubleMinMax(0.2, 0.3))
									.setWater( new DoubleMinMax(0.1, 0.2))
									.build()
									)
							.setMetalDefaults(new MetalDefaults.Builder()
									.setCarbon(new DoubleMinMax(0.3, 0.7))
									.setIron(new DoubleMinMax(0.01, 0.1))
									.build())
							.setTextureDefaults(new TextureDefaults(0.9, 
									Texture.Clastic_Medium,
									Texture.Clastic_Fine))
							.setCompositionDefaults(new CompositionDefaults(0.95, 
									BulkComposition.Silicate, 
									BulkComposition.Clay))
							.Build(),
						new IntMinMax(2,5)));
	}

}
