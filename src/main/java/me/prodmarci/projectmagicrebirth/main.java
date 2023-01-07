package me.prodmarci.projectmagicrebirth;

import me.prodmarci.projectmagicrebirth.souls.soulsHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class main extends JavaPlugin implements Listener {

    soulsHandler soulsHandler;
    public HashMap<String, Integer> soulsCount = new HashMap<String, Integer>();

    public main() {
        soulsHandler = new soulsHandler(this);
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
        Bukkit.getServer().getPluginManager().registerEvents(new soulsHandler(this), this);
    }
}
