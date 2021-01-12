package fr.julman.platform;

import com.sun.istack.internal.Nullable;

public abstract class Locales {
	public static final String USA_LANGUAGE_CODE = "USA";
	public static final String FRA_LANGUAGE_CODE = "FRA";
	
	public static enum Language {
		USA("USA"), FRA("FRA");
		
		private final String str;
		private Language(String str) {
			this.str = str;
		}
		public String toString() {
			return str;
		}
		@Nullable
		public static Language parse(String str) {
			return valueOf(str);
		}
	}
	
	public static enum LangEntry {
		PLAY_BUTTON, SETTINGS_BUTTON, QUIT_BUTTON, QUIT_CONFIRM, LOADING_TEXT, ENGINE_LOADING_TEXT, BACK_TEXT, LANGUAGE_TEXT, MUSIC_TEXT, MUSIC_ACTIVATION_TEXT
	}
	
	@Nullable
	public static String getLangEntry(LangEntry toGet) {
		return getLangEntry(toGet, Launcher.LANG);
	}
	
	@Nullable
	public static String getLangEntry(LangEntry toGet, Language translateTo) {
		switch (toGet) {
		case PLAY_BUTTON:
			if (translateTo == Language.USA) return USA_PLAY_BUTTON;
			return FRA_PLAY_BUTTON;
		case SETTINGS_BUTTON:
			if (translateTo == Language.USA) return USA_SETTINGS_BUTTON;
			return FRA_SETTINGS_BUTTON;
		case QUIT_BUTTON:
			if (translateTo == Language.USA) return USA_QUIT_BUTTON;
			return FRA_QUIT_BUTTON;
		case QUIT_CONFIRM:
			if (translateTo == Language.USA) return USA_QUIT_CONFIRM;
			return FRA_QUIT_CONFIRM;
		case BACK_TEXT:
			if (translateTo == Language.USA) return USA_BACK_TEXT;
			return FRA_BACK_TEXT;
		case ENGINE_LOADING_TEXT:
			if (translateTo == Language.USA) return USA_ENGINE_LOADING_TEXT;
			return FRA_ENGINE_LOADING_TEXT;
		case LOADING_TEXT:
			if (translateTo == Language.USA) return USA_LOADING_TEXT;
			return FRA_LOADING_TEXT;
		case LANGUAGE_TEXT:
			if (translateTo == Language.USA) return USA_LANGUAGE_TEXT;
			return FRA_LANGUAGE_TEXT;
		case MUSIC_TEXT:
			if (translateTo == Language.USA) return USA_MUSIC_TEXT;
			return FRA_MUSIC_TEXT;
		case MUSIC_ACTIVATION_TEXT:
			if (translateTo == Language.USA) return USA_MUSIC_ACTIVATING_TEXT;
			return FRA_MUSIC_ACTIVATION_TEXT;
		default:
			return null;
		}
	}
	
	public static final String USA_MONDAY    = "monday";
	public static final String USA_TUESDAY   = "tuesday";
	public static final String USA_WEDNESDAY = "wednesday";
	public static final String USA_THURSDAY  = "thursday";
	public static final String USA_FRIDAY    = "friday";
	public static final String USA_SATURDAY  = "saturday";
	public static final String USA_SUNDAY    = "sunday";
	
	public static final String FRA_MONDAY    = "lundi";
	public static final String FRA_TUESDAY   = "mardi";
	public static final String FRA_WEDNESDAY = "mercredi";
	public static final String FRA_THURSDAY  = "jeudi";
	public static final String FRA_FRIDAY    = "vendredi";
	public static final String FRA_SATURDAY  = "samedi";
	public static final String FRA_SUNDAY    = "dimanche";
	
	public static final String USA_JANUARY   = "january";
	public static final String USA_FEBRUARY  = "february";
	public static final String USA_MARCH     = "march";
	public static final String USA_APRIL     = "april";
	public static final String USA_MAY       = "may";
	public static final String USA_JUNE      = "june";
	public static final String USA_JULY      = "july";
	public static final String USA_AUGUST    = "august";
	public static final String USA_SEPTEMBER = "september";
	public static final String USA_OCTOBER   = "october";
	public static final String USA_NOVEMBER  = "november";
	public static final String USA_DECEMBER  = "december";
	
	public static final String FRA_JANUARY   = "janvier";
	public static final String FRA_FEBRUARY  = "février";
	public static final String FRA_MARCH     = "mars";
	public static final String FRA_APRIL     = "avril";
	public static final String FRA_MAY       = "mai";
	public static final String FRA_JUNE      = "juin";
	public static final String FRA_JULY      = "juillet";
	public static final String FRA_AUGUST    = "août";
	public static final String FRA_SEPTEMBER = "septembre";
	public static final String FRA_OCTOBER   = "octobre";
	public static final String FRA_NOVEMBER  = "novembre";
	public static final String FRA_DECEMBER  = "décembre";
	
	public static final String USA_PLAY_BUTTON = "PLAY";
	public static final String FRA_PLAY_BUTTON = "JOUER";
	
	public static final String USA_SETTINGS_BUTTON = "SETTINGS";
	public static final String FRA_SETTINGS_BUTTON = "PARAMÈTRES";
	
	public static final String USA_QUIT_BUTTON = "QUIT";
	public static final String FRA_QUIT_BUTTON = "QUITTER";
	
	public static final String USA_QUIT_CONFIRM = "Are you really want to quit ?";
	public static final String FRA_QUIT_CONFIRM = "Voulez-vous vraiment quitter ?";
	
	public static final String USA_LOADING_TEXT = "Loading...";
	public static final String FRA_LOADING_TEXT = "Chargement...";
	
	public static final String USA_ENGINE_LOADING_TEXT = "Engine is loading...";
	public static final String FRA_ENGINE_LOADING_TEXT = "Le moteur charge...";	
	
	public static final String USA_BACK_TEXT = "BACK";
	public static final String FRA_BACK_TEXT = "RETOUR";
	
	public static final String USA_LANGUAGE_TEXT = "Language";
	public static final String FRA_LANGUAGE_TEXT = "Langue";
	
	public static final String USA_MUSIC_TEXT = "Music";
	public static final String FRA_MUSIC_TEXT = "Musique";
	
	public static final String USA_MUSIC_ACTIVATING_TEXT = "Music enabling";
	public static final String FRA_MUSIC_ACTIVATION_TEXT = "Activation de la musique";
}
