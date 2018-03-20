package net.kevinmendoza.sedimentarysequences.configuration.rockdata;

import java.util.Random;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.BulkComposition;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.Texture;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class TextureDefaults {

	@Setting
	private double alternateValueCutoff;
	@Setting
	private Texture baseTexture;
	@Setting
	private Texture alternateTexture;
	
	public TextureDefaults(double cutoff, Texture main, Texture alt) {
		this.alternateValueCutoff=cutoff;
		this.baseTexture=main;
		this.alternateTexture=alt;
	}
	
	public Texture getTexture(long seed) {
		if(getAlternate(seed)) {
			return alternateTexture;
		}
		else {
			return baseTexture;
		}
	}
	private boolean getAlternate(long seed) {
		Random random = new Random(seed);
		random.nextDouble();
		double v = random.nextDouble();
		return (v > alternateValueCutoff);
	}
}
