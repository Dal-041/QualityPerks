package dal.plugins;

import lunalib.lunaSettings.LunaSettings;

public class Perks_Lunasettings {
	public static void init() {
		LunaSettings.addSettingsListener(new qpDynamicSettings());
	}
}
