package dal.impl.intel;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.*;
import com.fs.starfarer.api.fleet.MutableMarketStatsAPI;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.fleet.MutableFleetStatsAPI;

import dal.impl.campaign.skills.milestones.CaptainsUnbound;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class IntelQCAchievedMilestone extends BaseIntelPlugin {

	private String name;
	private String icon;

    public IntelQCAchievedMilestone(String skillIcon, String skillName, Object effect) {
		try {
			Global.getSettings().loadTexture(skillIcon);
		} catch (IOException e) {
			
		}
        this.name = skillName;
        this.icon = skillIcon;
        //runcode dal.plugins.Perks_Utils.debugAwardMilestones();

        /*
        //quick and dirty
        MutableFleetStatsAPI statsFleet = null;
        MutableCharacterStatsAPI statsChar = null;
        java.util.List<MarketAPI> markets = new ArrayList<>();

        if (effect instanceof FleetStatsSkillEffect) {
            if (Global.getSector() != null && Global.getSector().getPlayerStats() != null && Global.getSector().getPlayerStats().getFleet() != null && Global.getSector().getPlayerStats().getFleet().getStats() != null) {
                statsFleet = Global.getSector().getPlayerStats().getFleet().getStats();
            } else {
                return;
            }
            ((FleetStatsSkillEffect) effect).apply(statsFleet, "quality_perks", 1);
        } else if (effect instanceof CharacterStatsSkillEffect) {
            if (Global.getSector() != null && Global.getSector().getPlayerStats() != null) {
                statsChar = Global.getSector().getPlayerStats();
            } else {
                return;
            }
            ((CharacterStatsSkillEffect) effect).apply(statsChar, "quality_perks", 1);
        } else if (effect instanceof MarketSkillEffect) {
            if (Global.getSector() != null && Global.getSector().getEconomy().getMarketsCopy() != null) {
                for (MarketAPI m: Global.getSector().getEconomy().getMarketsCopy()) {
                    if (m.getFaction().isPlayerFaction()) markets.add(m);
                }
            } else {
                return;
            }
            for (MarketAPI m: markets) {
                ((MarketSkillEffect) effect).apply(m, "quality_perks", 1);
            }
        }
         */
    }

    @Override
    protected String getName() {
        return "Achieved Milestone: " + name;
    }

    @Override
    protected void addBulletPoints(TooltipMakerAPI info, ListInfoMode mode, boolean isUpdate, Color tc, float initPad) {

        Color gray = Misc.getGrayColor();

        info.addSpacer(3f);
        info.addPara("Open the character screen to view the effects", 0f, gray, gray);
    }

    protected void addBulletPoints(TooltipMakerAPI info, ListInfoMode mode, String text, boolean isUpdate, Color tc, float initPad) {

        Color gray = Misc.getGrayColor();

        info.addSpacer(3f);
        info.addPara(text, 0f, gray, gray);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
    	Color gray = Misc.getGrayColor();
        info.addSpacer(10f);
        TooltipMakerAPI imageTooltip = info.beginImageWithText(icon, 64f);
        imageTooltip.addPara("Achieving a major milestone has earned you a new, permanent skill.", 0f);
        info.addImageWithText(0f);

        info.addSpacer(10f);

        info.addPara("Perks and all other skills in QC can be configured while playing using LunaLib.", 0f);
        info.addPara("If not using LL, or if you'd like your configuration to load automatically, the same options can be found in data/config/LunaSettings.csv.", 0f, gray, gray);
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = new LinkedHashSet<String>();

        //tags.add("Milestones");

        return tags;
    }

    @Override
    public void advance(float amount) {
        super.advance(amount);
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
