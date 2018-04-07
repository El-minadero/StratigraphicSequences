package net.kevinmendoza.sedimentarysequences.stratigraphy;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.RockData;

class LayerTest {
	
	private static ILayer layer;
	private static IData defaultData;
	private static int thickness = 5;
	
	@BeforeAll
	static void createLayer() {
		defaultData = new RockData.Builder().build();
		layer = new Layer.Builder()
				.setData(defaultData)
				.setThickness(thickness)
				.build();
	}

	@Test
	void testGetThickness() {
		int th = layer.getThickness();
		assertEquals(thickness, th);
	}

	@Test
	void testGetData() {
		IData data = layer.getData();
		assertEquals(data, defaultData);
	}

}
