package net.kevinmendoza.sedimentarysequences.stratigraphy;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;

class FormationSequence {

	private int verticalOffset;
	private Formation aboveCenterFormation;
	private Formation belowCenterFormation; 
	
	private FormationSequence(FormationSequenceBuilder builder) {
		aboveCenterFormation = builder.getPositiveFormation();
		belowCenterFormation = builder.getNegativeFormation();
	}
	public void offsetInquiries(int offset) { 
		verticalOffset = offset; 
		if(offset<0) {
			aboveCenterFormation.prepare(256-offset);
		}
		else if(offset<256) {
			aboveCenterFormation.prepare(256-offset);
			belowCenterFormation.prepare(offset);
		}
		else {
			belowCenterFormation.prepare(offset);
		}
	}

	public ISingularGeologyData getRock(Vector3i query) {
		int y = query.getY()-verticalOffset;
		Vector3i newQuery;
		if(y<0) {
			newQuery = new Vector3i(query.getX(),-y,query.getZ());
			return belowCenterFormation.getRock(newQuery);
		}
		else {
			newQuery = new Vector3i(query.getX(),y,query.getZ());
			return aboveCenterFormation.getRock(newQuery);
		}
	}
	
	public String toString(Vector3i globalVector) {
		return "";
	}

	public static class FormationSequenceBuilder {
		
		private Formation aboveCenterFormation;
		private Formation belowCenterFormation;
		
		public FormationSequenceBuilder() { }

		public FormationSequenceBuilder setAboveFormation(Formation formation) { this.aboveCenterFormation=formation; return this;}
		public FormationSequenceBuilder setBelowFormation(Formation formation) { this.belowCenterFormation=formation; return this;}
		
		public FormationSequence build() {
			return new FormationSequence(this);
		}
		public Formation getNegativeFormation() { return this.belowCenterFormation; }
		public Formation getPositiveFormation() { return this.aboveCenterFormation; }
	}

	
}
