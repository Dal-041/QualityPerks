package dal.plugins;

import java.io.IOException;
import java.util.ArrayList;

import com.fs.starfarer.api.characters.FleetStatsSkillEffect;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;

import dal.impl.campaign.skills.milestones.CaptainsAcademician;
import dal.impl.campaign.skills.milestones.CaptainsBabylon;
import dal.impl.campaign.skills.milestones.CaptainsCombatListener;
import dal.impl.campaign.skills.milestones.CaptainsKnight;
import dal.impl.campaign.skills.milestones.CaptainsLucifer;
import dal.impl.campaign.skills.milestones.CaptainsOmega;
import dal.impl.campaign.skills.milestones.CaptainsPrince;
import dal.impl.campaign.skills.milestones.CaptainsStarfarer;
import dal.impl.campaign.skills.milestones.CaptainsAscendance;
import dal.impl.campaign.skills.milestones.CaptainsUnbound;
import dal.impl.campaign.skills.milestones.CaptainsUnderworld;
import dal.impl.campaign.skills.milestones.CaptainsUsurper;
import dal.impl.intel.IntelQCAchievedMilestone;


public class Perks_Utils {
    private static final Logger LOG = Global.getLogger(Perks_Utils.class);
    private static final String PATH = "data/config/LunaSettings.csv";
    public static boolean initLoaded = false;
    public static JSONArray csv;
    public static ArrayList<ArrayList<String>> entryList = new ArrayList<ArrayList<String>>();

	private static final String[] milestoneSkills = { CaptainsAcademician.skillID, CaptainsBabylon.skillID, CaptainsUnbound.skillID, CaptainsUsurper.skillID,
			CaptainsPrince.skillID, CaptainsKnight.skillID, CaptainsLucifer.skillID, CaptainsOmega.skillID, CaptainsUnderworld.skillID, CaptainsAscendance.skillID };
	
    protected static void parseModSettings() throws JSONException {
    	if (initLoaded) return;
        try {
        	csv = Global.getSettings().loadCSV(PATH, Perks_ModPlugin.ModID);
        } catch (IOException | JSONException ex) {
            LOG.fatal("QC: unable to read LunaSettings.csv", ex);
        }

        if (csv == null) return;
        
        for (int i = 0; i < csv.length(); i++) {
        	
        	JSONObject row = csv.getJSONObject(i);

			String id = row.getString("fieldID");
			if (id == "") continue;
			String type = row.getString("fieldType");
			
			String value = row.getString("defaultValue");
			//String valueSec = row.getString("secondaryValue");
			String min = row.getString("minValue");
			String max = row.getString("maxValue");
			//String tab = row.getString("tab");
			
			ArrayList<String> entry = new ArrayList<String>();
			entry.add(id); entry.add(type); entry.add(value); entry.add(min); entry.add(max);
			entryList.add(entry);
			
        }
        initLoaded = true;
    }
    
    public static int getIndex(String id) {
		for (int i = 0; i < entryList.size(); i++) {
			if (entryList.get(i).get(0).equals(id)) {
				return i;
			}
		}
		LOG.warn("QC: Did not find entry for " + id + "!");
    	return 0;
    }
    
    public static boolean getBool(String id) {
    	return getBoolean(id);
    }
    
    public static boolean getBoolean(String id) {
    	int i = getIndex(id);
    	return Boolean.parseBoolean(entryList.get(i).get(2));
    }
    
    public static int getInt(String id) {
    	int i = getIndex(id);
    	int result = Integer.parseInt(entryList.get(i).get(2));
    	if (result < Integer.parseInt(entryList.get(i).get(3))) result = Integer.parseInt(entryList.get(i).get(3));
    	if (result > Integer.parseInt(entryList.get(i).get(4))) result = Integer.parseInt(entryList.get(i).get(4));
    	return result; 
    }
    
    public static float getFloat(String id) {
    	int i = getIndex(id);
    	float result = Float.parseFloat(entryList.get(i).get(2));
    	if (result < Float.parseFloat(entryList.get(i).get(3))) result = Float.parseFloat(entryList.get(i).get(3));
    	if (result > Float.parseFloat(entryList.get(i).get(4))) result = Float.parseFloat(entryList.get(i).get(4));
    	return result;
    }
    
    public static double getDouble(String id) {
    	int i = getIndex(id);
    	double result = Double.parseDouble(entryList.get(i).get(2));
    	if (result < Double.parseDouble(entryList.get(i).get(3))) result = Double.parseDouble(entryList.get(i).get(3));
    	if (result > Double.parseDouble(entryList.get(i).get(4))) result = Double.parseDouble(entryList.get(i).get(4));
    	return result;
    }
    
    public static String getString(String id) {
    	int i = getIndex(id);
    	return entryList.get(i).get(2);
    }
    
	public static void loadQualityConfig() {
		LOG.info("QP: Loading milestone configs");
		loadQualityMilestoneEffectsV1();
	}
	
	public static void loadQualityMilestoneEffectsV1 () {
		CaptainsAcademician.SENSOR_PERC = getFloat("PERK_SENSOR_RANGE");
		CaptainsBabylon.USE_UNNERF = getBoolean("PERK_PHASE_UNNERF");
		CaptainsBabylon.SPEED_BONUS = getFloat("PERK_PHASE_SPD_BONUS");
		CaptainsUnbound.range = getFloat("PERK_GATE_RANGE");
		CaptainsUsurper.USE_AMBUSH = getBoolean("PERK_USE_AMBUSH");
		CaptainsUsurper.BONUS_CONTACTS = getInt("PERK_BONUS_CONTACTS");
		CaptainsKnight.MIN_OPPONENTS = getInt("PERK_KNT_MIN_OPPONENTS");
		CaptainsKnight.PPT_PERC = getFloat("PERK_KNT_PPT_PERC");
		CaptainsKnight.SECOND_WIND = getBoolean("PERK_KNT_SCDWIND");
		CaptainsLucifer.STABILITY_BONUS = getFloat("PERK_STABILITY");
		CaptainsPrince.BONUS_PTS = getInt("PERK_REP_BONUS");
		CaptainsPrince.MIN_PTS = getInt("PERK_REPCHANGE_MIN");
		CaptainsUnderworld.MAX_REP = getInt("PERK_PIRATES_REP_MAX");
		CaptainsUnderworld.REP_RATE = getInt("PERK_PIRATES_REP_MAG");
		CaptainsOmega.MAG = getFloat("PERK_ECM_FLAT");
		CaptainsAscendance.MAG = getFloat("PERK_NAV_FLAT");
		//CaptainsStarfarer.SP_BONUS = getInt("PERK_SP_BONUS");;
	}
	
	public static void blankMilestones() {
		try {
			Global.getSettings().loadTexture("graphics/icons/skills/blank.png");
		} catch (IOException e) {
			
		}
		for (String ID : milestoneSkills) {
			Global.getSettings().getSkillSpec(ID).setName("");
			Global.getSettings().getSkillSpec(ID).setAuthor("");
			Global.getSettings().getSkillSpec(ID).setDescription("");
			Global.getSettings().getSkillSpec(ID).setSpriteName("graphics/icons/skills/blank.png");
		}
		Global.getSettings().getSkillSpec("captains_starfarer").setName("");
		Global.getSettings().getSkillSpec("captains_starfarer").setAuthor("");
		Global.getSettings().getSkillSpec("captains_starfarer").setDescription("");
		Global.getSettings().getSkillSpec("captains_starfarer").setSpriteName("graphics/icons/skills/blank.png");
	}
	
	public static void refreshMilestones() {
		CaptainsAcademician.enableDisable();
		CaptainsBabylon.enableDisable();
		CaptainsUnbound.enableDisable();
		CaptainsUsurper.enableDisable();
		CaptainsUnderworld.enableDisable();
		CaptainsPrince.enableDisable();
		CaptainsKnight.enableDisable();
		CaptainsLucifer.enableDisable();
		CaptainsOmega.enableDisable();
		CaptainsAscendance.enableDisable();
		CaptainsStarfarer.enableDisable();
	}
	
	public static void checkMilestones() {
		MutableCharacterStatsAPI pc = Global.getSector().getPlayerStats();
		boolean allMilestones = true;
        if (Global.getSector().getMemoryWithoutUpdate().contains("$gaKA_missionCompleted")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsAcademician.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsAcademician.skillIcon, CaptainsAcademician.skillName, new CaptainsAcademician.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsAcademician.skillID, 1);
            }
        } else {
            allMilestones = false;
        }
        if (Global.getSector().getMemoryWithoutUpdate().contains("$defeatedZiggurat")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsBabylon.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsBabylon.skillIcon, CaptainsBabylon.skillName, new CaptainsBabylon.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsBabylon.skillID, 1);
                }
        } else { allMilestones = false; }
        if (Global.getSector().getMemoryWithoutUpdate().contains("$gaATG_missionCompleted")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsUnbound.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsUnbound.skillIcon, CaptainsUnbound.skillName, new CaptainsUnbound.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsUnbound.skillID, 1);
                }
        } else { allMilestones = false; }
        if (Global.getSector().getMemoryWithoutUpdate().contains("$sdtu_missionCompleted")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsUsurper.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsUsurper.skillIcon, CaptainsUsurper.skillName, new CaptainsUsurper.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsUsurper.skillID, 1);
            }
        } else { allMilestones = false; }
        if (Global.getSector().getCharacterData().getMemoryWithoutUpdate().contains("$soe_wonDuel")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsPrince.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsPrince.skillIcon, CaptainsPrince.skillName, new CaptainsPrince.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsPrince.skillID, 1);
                }
        } else { allMilestones = false; }
        if (Global.getSector().getMemoryWithoutUpdate().contains("$lke_missionCompleted")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsKnight.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsKnight.skillIcon, CaptainsKnight.skillName, new CaptainsKnight.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsKnight.skillID, 1);
            }
        } else { allMilestones = false; }
        if (Global.getSector().getMemoryWithoutUpdate().contains("$pk_recovered")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsLucifer.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsLucifer.skillIcon, CaptainsLucifer.skillName, new CaptainsLucifer.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsLucifer.skillID, 1);
            }
        } else { allMilestones = false; }
        if (Global.getSector().getMemoryWithoutUpdate().contains(CaptainsCombatListener.QC_DEFEATED_OMEGA)) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsOmega.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsOmega.skillIcon, CaptainsOmega.skillName, new CaptainsOmega.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsOmega.skillID, 1);
            }
        } else { allMilestones = false; }
        if (Global.getSector().getCharacterData().getMemoryWithoutUpdate().contains("$everHadKantaProtection")) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsUnderworld.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsUnderworld.skillIcon, CaptainsUnderworld.skillName, new CaptainsUnderworld.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsUnderworld.skillID, 1);
            }
        } else { allMilestones = false; }
        if (Global.getSector().getPlayerStats().getLevel() >= 15) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsAscendance.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsAscendance.skillIcon, CaptainsAscendance.skillName, new CaptainsAscendance.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsAscendance.skillID, 1);
            }
        } else { allMilestones = false; }
        //if (Global.getSector().getCharacterData().getMemoryWithoutUpdate().contains("$IndEvo_HamsterMurderer") && (Global.getSector().getPlayerStats().getSkillLevel(CaptainsAcademician.skillID) == 0) {pc.setSkillLevel(CaptainsIndEvoHamsterHitler.skillID, 1);
        if (allMilestones) {
            if (Global.getSector().getPlayerStats().getSkillLevel(CaptainsStarfarer.skillID) == 0) {
                IntelQCAchievedMilestone notif = new IntelQCAchievedMilestone(CaptainsStarfarer.skillIcon, CaptainsStarfarer.skillName, new CaptainsStarfarer.Level1());
                Global.getSector().getIntelManager().addIntel(notif);
                pc.setSkillLevel(CaptainsStarfarer.skillID, 1);
            }
        }
        refreshMilestones();
	}

	//runcode dal.plugins.Perk_Utils.debugAwardMilestones();
	public static void debugAwardMilestones() {
		Global.getSector().getMemoryWithoutUpdate().set("$gaKA_missionCompleted", true);
		Global.getSector().getMemoryWithoutUpdate().set("$defeatedZiggurat", true);
		Global.getSector().getMemoryWithoutUpdate().set("$gaATG_missionCompleted", true);
		Global.getSector().getMemoryWithoutUpdate().set("$sdtu_missionCompleted", true);
		Global.getSector().getMemoryWithoutUpdate().set("$soe_WonDuel", true);
		Global.getSector().getMemoryWithoutUpdate().set("$lke_completed", true);
		Global.getSector().getMemoryWithoutUpdate().set("$pk_recovered", true);
		Global.getSector().getMemoryWithoutUpdate().set(CaptainsCombatListener.QC_DEFEATED_OMEGA, true);
		Global.getSector().getCharacterData().getMemoryWithoutUpdate().set("$everHadKantaProtection",true);
		//Global.getSector().getCharacterData().getMemoryWithoutUpdate().set("$IndEvo_HamsterMurderer",true);
	}
}
