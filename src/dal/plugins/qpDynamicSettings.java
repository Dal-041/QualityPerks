package dal.plugins;

import dal.impl.campaign.skills.milestones.CaptainsAcademician;
import dal.impl.campaign.skills.milestones.CaptainsAscendance;
import dal.impl.campaign.skills.milestones.CaptainsBabylon;
import dal.impl.campaign.skills.milestones.CaptainsKnight;
import dal.impl.campaign.skills.milestones.CaptainsLucifer;
import dal.impl.campaign.skills.milestones.CaptainsOmega;
import dal.impl.campaign.skills.milestones.CaptainsPrince;
import dal.impl.campaign.skills.milestones.CaptainsUnbound;
import dal.impl.campaign.skills.milestones.CaptainsUnderworld;
import dal.impl.campaign.skills.milestones.CaptainsUsurper;
import lunalib.lunaSettings.LunaSettings;
import lunalib.lunaSettings.LunaSettingsListener;

public class qpDynamicSettings implements LunaSettingsListener {
	
	protected void reloadQualityConfig() {
		reloadQualityMilestoneEffects();
	}

	public void reloadQualityMilestoneEffects () {
		CaptainsAcademician.SENSOR_PERC = LunaSettings.getFloat(Perks_ModPlugin.ModID, "PERK_SENSOR_RANGE");
		CaptainsBabylon.USE_UNNERF = LunaSettings.getBoolean(Perks_ModPlugin.ModID, "PERK_PHASE_UNNERF");
		CaptainsBabylon.SPEED_BONUS = LunaSettings.getFloat(Perks_ModPlugin.ModID, "PERK_PHASE_SPD_BONUS");
		CaptainsUnbound.range = LunaSettings.getFloat(Perks_ModPlugin.ModID, "PERK_GATE_RANGE");
		CaptainsUsurper.USE_AMBUSH = LunaSettings.getBoolean(Perks_ModPlugin.ModID, "PERK_USE_AMBUSH");
		CaptainsKnight.MIN_OPPONENTS = LunaSettings.getInt(Perks_ModPlugin.ModID, "PERK_KNT_MIN_OPPONENTS");
		CaptainsKnight.PPT_PERC = LunaSettings.getFloat(Perks_ModPlugin.ModID, "PERK_KNT_PPT_PERC");
		CaptainsKnight.SECOND_WIND = LunaSettings.getBoolean(Perks_ModPlugin.ModID, "PERK_KNT_SCDWIND");
		CaptainsLucifer.STABILITY_BONUS = LunaSettings.getFloat(Perks_ModPlugin.ModID, "PERK_STABILITY");
		CaptainsPrince.BONUS_PTS = LunaSettings.getInt(Perks_ModPlugin.ModID, "PERK_REP_BONUS");
		CaptainsPrince.MIN_PTS = LunaSettings.getInt(Perks_ModPlugin.ModID, "PERK_REPCHANGE_MIN");
		CaptainsUnderworld.MAX_REP = LunaSettings.getInt(Perks_ModPlugin.ModID, "PERK_PIRATES_REP_MAX");
		CaptainsUnderworld.REP_RATE = LunaSettings.getInt(Perks_ModPlugin.ModID, "PERK_PIRATES_REP_MAG");
		CaptainsOmega.MAG = LunaSettings.getFloat(Perks_ModPlugin.ModID, "PERK_ECM_FLAT");
		CaptainsAscendance.MAG = LunaSettings.getFloat(Perks_ModPlugin.ModID, "PERK_NAV_FLAT");
	}
	
	@Override
	public void settingsChanged(String modID) {
		reloadQualityConfig();
	}
}
