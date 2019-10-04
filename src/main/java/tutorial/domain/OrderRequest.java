package tutorial.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class OrderRequest {
	@NotNull
	private String flavor;
	
	@NotNull
	private String headSize;

	@Range(min = 2, max = 4)
	private int hoses;

	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public int getHoses() {
		return hoses;
	}
	public void setHoses(int hoses) {
		this.hoses = hoses;
	}
	public String getHeadSize() {
		return headSize;
	}
	public void setHeadSize(String headSize) {
		this.headSize = headSize;
	}

}
