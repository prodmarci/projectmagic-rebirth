package me.prodmarci.projectmagicrebirth.spellbook;

import me.prodmarci.projectmagicrebirth.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.List;

public class spellsHandler implements Listener {
    main mainClass;
    public HashMap<String, Integer> soulsCount;
    public HashMap<String, String> spellSelected;
    public HashMap<String, String> spellEquipped;
    public List<String> spellNamesWithCost;

    // Allows accessing variables from main class
    public spellsHandler(main m) {
        this.mainClass = m;
        this.soulsCount = m.soulsCount;
        this.spellSelected = m.spellSelected;
        this.spellEquipped = m.spellEquipped;
        this.spellNamesWithCost = m.spellNamesWithCost;
    }

    // Verifies if player has enough souls to equip chosen spell if so adds player to spells equipped
    @EventHandler
    public void verifySpellSelect(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        // Create a timed runnable executing every 40 ticks
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(mainClass, new Runnable() {
            public void run() {

                // If player has selected spell but not equipped it yet
                if ((spellSelected.containsKey(playerUUID)) && !spellEquipped.containsKey(playerUUID)) {
                    String spellName = spellSelected.get(playerUUID);
                    Integer playerSoulsCount = soulsCount.get(playerUUID);
                    int spellCostIndex = spellNamesWithCost.indexOf(spellName) + 1;
                    Integer spellCost = Integer.parseInt(spellNamesWithCost.get(spellCostIndex));

                    // If player has enough souls to equip spell
                    if (playerSoulsCount >= spellCost) {

                        // Decrease spell cost from player cost
                        mainClass.changeSoulsCount(playerUUID, playerSoulsCount-spellCost);

                        // Adds player to HashMap with equipped spells
                        mainClass.equipSpell(playerUUID, spellName);

                        // Deselect spell because player already equipped it
                        mainClass.deselectSpell(playerUUID, spellName);

                        // Debug message
                        player.sendMessage("wow you can afford, spell equipped!");
                    }
                }
            }
        },0, 20);
    }
}
