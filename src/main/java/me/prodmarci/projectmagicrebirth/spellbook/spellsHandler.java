package me.prodmarci.projectmagicrebirth.spellbook;

import me.prodmarci.projectmagicrebirth.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class spellsHandler implements Listener {
    main mainClass;
    public HashMap<String, String> spellSelected;

    // Allows accessing variables from main class
    public spellsHandler(main m) {
        this.mainClass = m;
        this.spellSelected = m.spellSelected;
    }

    // Casts selected spell when clicking wand
    @EventHandler
    public void castSpell(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();


    }
}
