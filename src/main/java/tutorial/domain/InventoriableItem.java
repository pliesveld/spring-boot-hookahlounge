package tutorial.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.Data;

@Embeddable
@Data
public class InventoriableItem {
	private int quantity;
	private int name;
}
