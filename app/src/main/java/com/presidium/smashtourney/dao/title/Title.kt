package com.presidium.smashtourney.dao.title

enum class Title(
    var abbrev: String,
    var id: Int,
    var longName: String,
    var displayName: String) {
    MELEE("Melee", 1, "Super Smash Bros. Melee", "Melee"), PM("pm", 2, "Project M", "PM");
    
}