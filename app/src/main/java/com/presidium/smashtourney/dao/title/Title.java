package com.presidium.smashtourney.dao.title;

public enum Title {

	MELEE("Melee", 1, "Super Smash Bros. Melee", "Melee"),
	PM("pm", 2, "Project M", "PM");
	public String abbrev;
	public Integer id;
	public String name;
	public String displayName;

	Title(String abbrev, Integer id, String name, String displayName) {
		this.abbrev = abbrev;
		this.id = id;
		this.name = name;
		this.displayName = displayName;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}
}
