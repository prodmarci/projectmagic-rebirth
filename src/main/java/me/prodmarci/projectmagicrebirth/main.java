package me.prodmarci.projectmagicrebirth;

import me.prodmarci.projectmagicrebirth.souls.soulsHandler;
import me.prodmarci.projectmagicrebirth.spellbook.spellbookHandler;
import me.prodmarci.projectmagicrebirth.spellbook.spellsHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class main extends JavaPlugin implements Listener {

    // All spellNames with costs, used in spell handling!
    public List<String> spellNamesWithCost = Arrays.asList(
            "TP", "10");

    soulsHandler soulsHandler;
    public HashMap<String, Integer> soulsCount = new HashMap<String, Integer>();

    spellbookHandler spellbookHandler;
    spellsHandler spellsHandler;
    public HashMap<String, String> spellSelected = new HashMap<String, String>();
    public HashMap<String, String> spellEquipped = new HashMap<String, String>();

    public main() {
        soulsHandler = new soulsHandler(this);
        spellbookHandler = new spellbookHandler(this);
        spellsHandler = new spellsHandler(this);
    }

    // -> Souls count classes

    // Used when joining, adds player with certain UUID to HashMap
    public void initSoulsCount(String UUID,Integer soulsAmount) {
        soulsCount.put(UUID, soulsAmount);
    }
    // Used when leaving, removes player with certain UUID from HashMap
    public void delSoulsCount(String UUID,Integer soulsAmount) {
        soulsCount.remove(UUID, soulsAmount);
    }
    // Used to change certain value assigned to player with certain UUID in HashMap
    public void changeSoulsCount(String UUID,Integer soulsAmount) {
        soulsCount.replace(UUID, soulsAmount);
    }

    // -> Spellbook choose spell and use spell

    // Used when selecting spell in spellbook, adds player with certain UUID to HashMap
    public void selectSpell(String UUID,String spellName) {
        spellSelected.put(UUID, spellName);
    }

    // Used when selected spell was used, removes player with certain UUID from HashMap
    public void deselectSpell(String UUID,String spellName) {
        spellSelected.remove(UUID, spellName);
    }

    // -> Spell handling

    // Used with spell handling, after verifying spell selection
    public void equipSpell(String UUID,String spellName) {
        spellEquipped.put(UUID, spellName);
    }

    // Used with spell handling, after verifying spell selection
    public void unequipSpell(String UUID,String spellName) {
        spellEquipped.remove(UUID, spellName);
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new soulsHandler(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new spellbookHandler(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new spellsHandler(this), this);
    }
}
