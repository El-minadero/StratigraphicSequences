package net.kevinmendoza.sedimentarysequences.configuration.rockdata;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.ActivityModifiers;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.DoubleMinMax;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ActivityDefaults {
	@Setting
	private DoubleMinMax 	sulfur;	
	@Setting// assume 0 - 1 M/assume saturated hydrocarbon.
	private DoubleMinMax		water;
	@Setting// 0% to 100% water
	private DoubleMinMax		temperature;
	@Setting// in deg C
	private DoubleMinMax		pressure_transients;
	@Setting// in ∆kPa
	private DoubleMinMax		pressure;	// in ∆kPa
	
	public ActivityDefaults(Builder builder) {
		sulfur =builder.getSulfur();
		water=builder.getWater();
		temperature=builder.getTemperature();
		pressure_transients=builder.getPressureTransients();
		pressure =builder.getPressure();
	}
	public double[] getActivities(long seed) {
		double[] activities = new double[ActivityModifiers.values().length];
		activities[ActivityModifiers.Pressure.ordinal()] 		= pressure.getValue(seed);
		activities[ActivityModifiers.Temperature.ordinal()] 	= temperature.getValue(seed);
		activities[ActivityModifiers.Pressure_transients.ordinal()] = pressure_transients.getValue(seed);
		activities[ActivityModifiers.Sulfur.ordinal()] 		= sulfur.getValue(seed);
		activities[ActivityModifiers.Water.ordinal()] 		= water.getValue(seed);
		return activities;
	}
	
	public static class Builder {
		DoubleMinMax		water;
		DoubleMinMax		temperature;
		DoubleMinMax		pressure_transients;
		DoubleMinMax		pressure;	// in ∆kPa
		DoubleMinMax 	sulfur;	
		private DoubleMinMax getSulfur() { return sulfur; }
		
		public Builder setSulfur(DoubleMinMax sulfur) {
			this.sulfur = sulfur; return this;
		}
		private DoubleMinMax getWater() {
			return water;
		}
		public Builder setWater(DoubleMinMax water) {
			this.water = water;return this;
		}
		private DoubleMinMax getTemperature() {
			return temperature;
		}
		public Builder setTemperature(DoubleMinMax temperature) {
			this.temperature = temperature;return this;
		}
		private DoubleMinMax getPressureTransients() {
			return pressure_transients;
		}
		public Builder setPressureTransients(DoubleMinMax pressure_transients) {
			this.pressure_transients = pressure_transients;return this;
		}
		private DoubleMinMax getPressure() {
			return pressure;
		}
		public Builder setPressure(DoubleMinMax pressure) {
			this.pressure = pressure;return this;
		}
		
		public ActivityDefaults build() {
			return new ActivityDefaults(this);
		}
		
	}

}
