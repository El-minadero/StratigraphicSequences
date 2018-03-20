package net.kevinmendoza.sedimentarysequences.configuration;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.sedimentarysequences.configuration.rockdata.Petrology;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class SedimentaryUnit {

	@Setting
	private IntMinMax thicknessRange;
	@Setting
	private Petrology petrology;
	@Setting
	private String name;
	@Setting
	private double occurrenceWeight;
	private double bottomRange;
	
	public SedimentaryUnit(double weight, String name, Petrology petrology,
			IntMinMax thicknessRange) {
		this.thicknessRange=thicknessRange;
		this.name=name;
		this.petrology=petrology;
		this.occurrenceWeight=weight;
	}

	public double getOccurenceWeight() { return occurrenceWeight; }

	public void normalize(double totalWeight) {
		occurrenceWeight/=totalWeight;
	}

	public void setBottomRange(double bottomRange) {
		this.bottomRange=bottomRange;
	}
	public boolean isInRange(double val) {
		return (val < bottomRange + occurrenceWeight && val > bottomRange);
	}

	public IData getData(long seed) {
		return petrology.getIData(seed);
	}

	public int getThickness(long seed) {
		return thicknessRange.getValue(seed);
	}
}
