package net.kevinmendoza.sedimentarysequences.stratigraphy;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

public interface IColumn extends IGeology {

	public IData getData(int depth);
	public boolean isInside(int depth);
}
