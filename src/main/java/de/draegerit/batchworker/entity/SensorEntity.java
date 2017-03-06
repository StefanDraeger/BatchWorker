package de.draegerit.batchworker.entity;

/**
 * Klasse für einen Eintrag in einer CSV Datei.
 * @author Stefan Draeger
 *
 */
public class SensorEntity {

	//Vorlaufende Nummer
	private long id;

	//Vorlaufende Nummer
	private long id1;


	//Beschreibung
	private String description;

	//Wert
	private double value;

	//Zeitstempel des Wertes
	private long timestamp;

	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public double getValue() { return value; }

	public void setValue(double value) { this.value = value; }

	public long getTimestamp() { return timestamp; }

	public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

	public long getId1() {
		return id1;
	}

	public void setId1(long id1) {
		this.id1 = id1;
	}

}
