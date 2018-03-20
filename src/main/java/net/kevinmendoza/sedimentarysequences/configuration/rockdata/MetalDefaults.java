package net.kevinmendoza.sedimentarysequences.configuration.rockdata;


import javax.print.attribute.standard.RequestingUserName;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.Metals;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.DoubleMinMax;
import ninja.leaping.configurate.objectmapping.Setting;

public class MetalDefaults {

	@Setting
	private DoubleMinMax 	iron;	
	@Setting// assume 0 - 1 M/assume saturated hydrocarbon.
	private DoubleMinMax		carbon;
	@Setting// 0% to 100% water
	private DoubleMinMax		beryllium;
	@Setting// in deg C
	private DoubleMinMax		gold;
	@Setting// in ∆kPa
	private DoubleMinMax		silver;	// in ∆kPa
	@Setting// in ∆kPa
	private DoubleMinMax		copper;	// in ∆kPa
	
	public MetalDefaults(Builder builder) {
		iron =builder.getIron();
		carbon=builder.getCarbon();
		beryllium=builder.getBeryllium();
		gold=builder.getGold();
		silver =builder.getSilver();
		copper= builder.getCopper();
	}
	public double[] getMetals(long seed) {
		double[] metals = new double[Metals.values().length];
		metals[Metals.Iron.ordinal()] 	= iron.getValue(seed);
		metals[Metals.Carbon.ordinal()] 	= carbon.getValue(seed);
		metals[Metals.Beryllium.ordinal()] = beryllium.getValue(seed);
		metals[Metals.Silver.ordinal()] 	= silver.getValue(seed);
		metals[Metals.Gold.ordinal()] 	= gold.getValue(seed);
		return metals;
	}
	
	public static class Builder {
		DoubleMinMax		iron;
		DoubleMinMax		carbon;
		DoubleMinMax		beryllium;
		DoubleMinMax		silver;	// in ∆kPa
		DoubleMinMax 	copper;	
		DoubleMinMax 	gold;	
		private DoubleMinMax getIron() {
			return iron;
		}
		public Builder setIron(DoubleMinMax iron) {
			this.iron = iron;return this;
		}
		private DoubleMinMax getCarbon() {
			return carbon;
		}
		public Builder setCarbon(DoubleMinMax carbon) {
			this.carbon = carbon; return this;
		}
		private DoubleMinMax getBeryllium() {
			return beryllium;
		}
		public Builder setBeryllium(DoubleMinMax beryllium) {
			this.beryllium = beryllium; return this;
		}
		private DoubleMinMax getSilver() {
			return silver;
		}
		public Builder setSilver(DoubleMinMax silver) {
			this.silver = silver;return this;
		}
		private DoubleMinMax getCopper() {
			return copper;
		}
		public Builder setCopper(DoubleMinMax copper) {
			this.copper = copper;return this;
		}
		private DoubleMinMax getGold() {
			return gold;
		}
		public Builder setGold(DoubleMinMax gold) {
			this.gold = gold; return this;
		}
		
		public MetalDefaults build() {
			return new MetalDefaults(this);
		}
	}

}
