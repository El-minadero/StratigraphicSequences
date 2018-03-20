package net.kevinmendoza.sedimentarysequences.configuration.rockdata;

import java.util.Random;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.BulkComposition;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class CompositionDefaults {

	@Setting
	private double alternateValueCutoff;
	@Setting
	private BulkComposition baseComposition;
	@Setting
	private BulkComposition alternateComposition;
	
	public CompositionDefaults(double cutoff, BulkComposition main, BulkComposition alt) {
		this.alternateValueCutoff=cutoff;
		this.baseComposition=main;
		this.alternateComposition=alt;
	}
	
	
	public BulkComposition getComposition(long seed) {
		if(getAlternate(seed)) {
			return alternateComposition;
		}
		else {
			return baseComposition;
		}
	}
	
	private boolean getAlternate(long seed) {
		Random random = new Random(seed);
		random.nextDouble();
		double v = random.nextDouble();
		return (v > alternateValueCutoff);
	}
}
