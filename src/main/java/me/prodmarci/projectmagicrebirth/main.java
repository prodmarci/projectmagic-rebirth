package me.prodmarci.projectmagicrebirth;

import me.prodmarci.projectmagicrebirth.souls.soulsBar;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class main extends JavaPlugin implements Listener {

    soulsBar soulsBar;
    public HashMap<String, Integer> soulsCount = new HashMap<String, Integer>();

    public main() {
        soulsBar = new soulsBar(this);
    }

    public void initSoulsCount(String UUID,Integer soulsAmount) {
        soulsCount.put(UUID, soulsAmount);
    }

    public void delSoulsCount(String UUID,Integer soulsAmount) {
        soulsCount.remove(UUID, soulsAmount);
    }

    public void changeSoulsCount(String UUID,Integer soulsAmount) {
        soulsCount.replace(UUID, soulsAmount);
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new soulsBar(this), this);
    }
}
