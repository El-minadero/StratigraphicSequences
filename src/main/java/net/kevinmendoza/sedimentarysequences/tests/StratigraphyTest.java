package net.kevinmendoza.sedimentarysequences.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.PrimeData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.sedimentarysequences.configuration.StratigraphicDefaults;
import net.kevinmendoza.sedimentarysequences.stratigraphy.StratigraphyHub;

class StratigraphyTest {

	public static void main(String[] args) {
		long seed = 5;
		StratigraphicDefaults defaults = new StratigraphicDefaults();
		IGeology stratigraphicGeology = StratigraphyHub.GetSedimentarySequence(seed);
		BufferedImage img = new  BufferedImage(250,250,BufferedImage.TYPE_INT_RGB);
		AbstractRock rock = (AbstractRock) stratigraphicGeology.getStartingData(EmptyDataFactory.getEmptyDataObject(3));
		for(int x = 0;x<250;x++) {
			Vector2i vec = new Vector2i(x,0);
			PrimeData data = new PrimeData(vec);
			stratigraphicGeology.primeLoadedObjects(data);
			for(int y = 0;y<128;y++) {
				AbstractRock rock2 = (AbstractRock) stratigraphicGeology.get3DGeologyData(rock, new Vector3i(3,y,5));
				String s = rock2.toString();
				img.setRGB(x, y, s.hashCode());
			}
		}
		File outputfile = new File("StratigraphyTest.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
