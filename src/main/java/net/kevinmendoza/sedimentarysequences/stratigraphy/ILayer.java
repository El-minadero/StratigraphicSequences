package net.kevinmendoza.sedimentarysequences.stratigraphy;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

interface ILayer {

	IData getData();
	int getThickness();
}
