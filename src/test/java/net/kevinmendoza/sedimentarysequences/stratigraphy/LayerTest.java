package net.kevinmendoza.sedimentarysequences.stratigraphy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.RockData;

class LayerTest {
	
	private ILayer layer;
	private IData defaultData;
	private static int thickness = 5;
	
	@BeforeAll
	void createLayer() {
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
