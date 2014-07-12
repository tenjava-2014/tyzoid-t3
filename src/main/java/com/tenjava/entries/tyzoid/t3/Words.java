package com.tenjava.entries.tyzoid.t3;

import java.util.Random;

class Words {
	private final String[] s = {
			"Berenice", "Clinton", "Ethiopians", "Hertzsprung",
			"Kristie", "Melody", "Paramount", "Sandburg",
			"Timmy", "abetter", "afterwards", "antennae",
			"astrologer", "barometers", "binned", "bounciest",
			"busier", "castoff", "chitterlings", "cogitate",
			"confrontations", "cosponsoring", "cumquat", "defined",
			"diabolically", "dispute", "drugged", "emigrating",
			"etymological", "fakers", "flambeing", "foundries",
			"gauchos", "grammatically", "handpick", "hitter",
			"ibices", "indents", "interfaith", "jonquils",
			"lateraling", "loadstars", "manipulate", "metronomes",
			"monitor", "navigate", "objectionably", "outstretch",
			"paroling", "perversions", "plowman", "preferring",
			"protractor", "raging", "redraws", "reproving",
			"river", "satiate", "selection", "shrilly",
			"sludge", "sparse", "statistics", "suborn",
			"sycamore", "terrorizing", "torpor", "tubas",
			"unfairer", "vacuums", "wakened", "winded"
	};
	
	public String getRandomWord(){
		Random r = new Random();
		return s[r.nextInt(s.length)];
	}
}
