package net.kevinmendoza.sedimentarysequences.stratigraphy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.BulkComposition;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.RockData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.Texture;

class ColumnTest {
	
	static List<ILayer> layers;
	static int thickness = 12;
	static IColumn column;
	private static final IData fillerRock = new RockData.Builder()
			.setTexture(Texture.Clastic_Fine)
			.setBulkComposition(BulkComposition.Silicate)
			.build();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		layers=new ArrayList<>();
		layers.add(new Layer.Builder()
				.setThickness(3)
				.setData(new RockData.Builder()
						.setTexture(Texture.Grained_Pegmatitic)
						.build())
				.build());
		layers.add(new Layer.Builder()
				.setThickness(4)
				.setData(new RockData.Builder()
						.setTexture(Texture.Clastic_Coarse)
						.build())
				.build());
		layers.add(new Layer.Builder()
				.setThickness(5)
				.setData(new RockData.Builder()
						.setTexture(Texture.Clastic_Fine)
						.build())
				.build());
		column = new Column.Builder()
				.addLayers(layers)
				.build();
	}

	@Test
	void testGetFirstLayer3iData() {
		Vector3i vector3i = new Vector3i(0,2,0);
		IData source = column.getData(vector3i);
		IData target = layers.get(0).getData();
		assertEquals(source, target,"reference data not equal with vec3i lookup");
	}
	
	@Test
	void testGetFirstLayerDepthData() {
		IData source = column.getData(2);
		IData target = layers.get(0).getData();
		assertEquals(source, target,"reference data not equal with depth lookup");
	}
	@Test
	void testGetSecondLayer3iData() {
		Vector3i vector3i = new Vector3i(0,5,0);
		IData source = column.getData(vector3i);
		IData target = layers.get(1).getData();
		assertEquals(source, target,"reference data not equal with vec3i lookup");
	}
	
	@Test
	void testGetSecondLayerDepthData() {
		IData source = column.getData(5);
		IData target = layers.get(1).getData();
		assertEquals(source, target,"reference data not equal with depth lookup");
	}
	
	@Test
	void testGetThirdLayer3iData() {
		Vector3i vector3i = new Vector3i(0,8,0);
		IData source = column.getData(vector3i);
		IData target = layers.get(2).getData();
		assertEquals(source, target,"reference data not equal with vec3i lookup");
	}
	
	@Test
	void testGetThirdLayerDepthData() {
		IData source = column.getData(8);
		IData target = layers.get(2).getData();
		assertEquals(source, target,"reference data not equal with depth lookup");
	}
	
	@Test
	void testGetDefaultLayer2ivec() {
		Vector2i vector2i = new Vector2i(0,0);
		IData source = column.getData(vector2i);
		assertEquals(source, fillerRock,"reference data not equal with depth lookup");
	}

	@Test
	void testGetDefaultLowerBoundData() {
		IData source = column.getData(-1);
		assertEquals(source, fillerRock,"reference data not equal with depth lookup");
	}
	
	@Test
	void testGetDefaultUpperBoundData() {
		IData source = column.getData(20);
		assertEquals(source, fillerRock,"reference data not equal with depth lookup");
	}

	@Test
	void testIsInside() {
		boolean inside = column.isInside(7);
		assertTrue(inside,"not inside");
	}
	
	@Test
	void testIsOutside1() {
		boolean inside = column.isInside(-1);
		assertFalse(inside,"not inside");
	}
	
	@Test
	void testIsOutside2() {
		boolean inside = column.isInside(20);
		assertFalse(inside,"not inside");
	}
	

}
