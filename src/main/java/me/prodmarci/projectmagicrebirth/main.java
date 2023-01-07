package me.prodmarci.projectmagicrebirth;

import me.prodmarci.projectmagicrebirth.souls.soulsHandler;
import me.prodmarci.projectmagicrebirth.spellbook.spellbookHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class main extends JavaPlugin implements Listener {

    soulsHandler soulsHandler;
    public HashMap<String, Integer> soulsCount = new HashMap<String, Integer>();

    spellbookHandler spellbookHandler;

    public main() {
        soulsHandler = new soulsHandler(this);
        spellbookHandler = new spellbookHandler(this);
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

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new soulsHandler(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new spellbookHandler(this), this);
    }
}
