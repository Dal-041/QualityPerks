package dal.plugins;

import dal.impl.campaign.skills.milestones.CaptainsCombatListener;
import dal.impl.campaign.skills.milestones.CaptainsMilestoneEFScript;
import org.json.JSONException;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class Perks_ModPlugin extends BaseModPlugin {
	public static final String ModID = "QualityPerks";
	
	@Override
	public void onApplicationLoad() {
		try {
			Perks_Utils.parseModSettings();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Perks_Utils.loadQualityConfig();
		Perks_Utils.blankMilestones();
		if (Global.getSettings().getModManager().isModEnabled("lunalib")) {
			qpDynamicSettings temp = new qpDynamicSettings();
			Perks_Lunasettings.init();
			temp.reloadQualityConfig();
		}
	}
	
	@Override
	public void onGameLoad(boolean newGame) {
		Perks_Utils.blankMilestones();
		Perks_Utils.refreshMilestones();
		if (!Global.getSector().hasScript(CaptainsMilestoneEFScript.class)) {
			Global.getSector().addScript(new CaptainsMilestoneEFScript());
		}
		if (Global.getSector().getPlayerFleet() != null) Global.getSector().getPlayerFleet().addEventListener(new CaptainsCombatListener());
	}

	@Override
	public void onNewGameAfterEconomyLoad() {
		if (!Global.getSector().hasScript(CaptainsMilestoneEFScript.class)) {
			Global.getSector().addScript(new CaptainsMilestoneEFScript());
		}
		if (Global.getSector().getPlayerFleet() != null) Global.getSector().getPlayerFleet().addEventListener(new CaptainsCombatListener());
	}
}