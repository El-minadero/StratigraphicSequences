package net.kevinmendoza.sedimentarysequences.configuration.rockdata;

import java.util.jar.Attributes.Name;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.BulkComposition;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.RockData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.Texture;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.DoubleMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class Petrology {
	@Setting
	private IntMinMax thicknessRange;
	@Setting
	private TextureDefaults textureDefaults;
	@Setting
	private CompositionDefaults compositionDefaults;
	@Setting
	private ActivityDefaults activityDefaults;
	@Setting
	private MetalDefaults metalDefaults;
	
	public Petrology(Builder builder) {
		thicknessRange=builder.getThicknessRange();
		textureDefaults=builder.getTextureDefaults();
		compositionDefaults=builder.getCompositionDefaults();
		metalDefaults=builder.getMetalDefaults();
		activityDefaults=builder.getActivityDefaults();
	}

	public int getThickness(long seed) { return thicknessRange.getValue(seed); }

	public IData getIData(long seed) {
		Texture texture = textureDefaults.getTexture(seed);
		BulkComposition composition = compositionDefaults.getComposition(seed);
		double[] metals = metalDefaults.getMetals(seed);
		double[] activity= activityDefaults.getActivities(seed);
		return new RockData.Builder()
				.setActivity(activity)
				.setBulkComposition(composition)
				.setMetals(metals)
				.setTexture(texture)
				.build();
	}
	
	public static class Builder {

		IntMinMax thicknessRange;
		TextureDefaults textureDefaults;
		CompositionDefaults compositionDefaults;
		ActivityDefaults activityDefaults;
		MetalDefaults metalDefaults;


		private ActivityDefaults getActivityDefaults() { return activityDefaults; }

		private IntMinMax getThicknessRange() { return thicknessRange; }

		private TextureDefaults getTextureDefaults() { return textureDefaults;  }

		private CompositionDefaults getCompositionDefaults() { return compositionDefaults; }

		private MetalDefaults getMetalDefaults() { return metalDefaults; }

		public Builder setThicknessRange(IntMinMax thicknessRange) {
			this.thicknessRange = thicknessRange; return this;
		}


		public Builder setTextureDefaults(TextureDefaults textureDefaults) {
			this.textureDefaults = textureDefaults;return this;
		}

		public Builder setCompositionDefaults(CompositionDefaults compositionDefaults) {
			this.compositionDefaults = compositionDefaults;return this;
		}

		public Builder setActivityDefaults(ActivityDefaults activityDefaults) {
			this.activityDefaults = activityDefaults;return this;
		}

		public Builder setMetalDefaults(MetalDefaults metalDefaults) {
			this.metalDefaults = metalDefaults;return this;
		}
		
		public Petrology Build() {
			return new Petrology(this);
		}
	}

}
