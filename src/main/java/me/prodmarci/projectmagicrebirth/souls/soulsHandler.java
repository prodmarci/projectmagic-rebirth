package me.prodmarci.projectmagicrebirth.souls;

import me.prodmarci.projectmagicrebirth.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class soulsHandler implements Listener {
    main mainClass;
    public HashMap<String, Integer> soulsCount;

    // Allows accessing variables from main class
    public soulsHandler(main m) {
        this.mainClass = m;
        this.soulsCount = m.soulsCount;
    }

    // Set maximum amount of souls that player can have
    public int maxSouls = 100;

    // Adds player to soulsCount HashMap and sets player souls to 0 when player joins
    @EventHandler
    public void setSoulsOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        if (!soulsCount.containsKey(playerUUID)) {
            mainClass.initSoulsCount(playerUUID, 0);
        }
    }

    // Removes player from soulsCount HashMap when player quits
    @EventHandler
    public void delSoulsOnQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        if (soulsCount.containsKey(playerUUID)) {
            Integer soulsAmount = soulsCount.get(playerUUID);
            mainClass.delSoulsCount(playerUUID, soulsAmount);
        }
    }

    // Add souls for certain entities killed
    @EventHandler
    public void rechargeSouls(EntityDeathEvent event) {
        LivingEntity entity  = event.getEntity();
        EntityType entityType = entity.getType();

        // Checks if entity exists
        if (entity.getKiller() != null) {
            Player player = entity.getKiller();
            String playerUUID = player.getUniqueId().toString();
            Integer playerSoulsCount = soulsCount.get(playerUUID);

            // Depending on entity killed by player add souls
            switch (entityType) {
                case CREEPER: {
                    // Sets amount of souls player will get when killing entity above ^^^
                    int soulsForKill = 20;

                    // If playerSoulsCount after a kill will be bigger than maxSouls sets player soulsCount to maxSouls
                    if (playerSoulsCount + soulsForKill > maxSouls) {
                        soulsForKill = maxSouls - playerSoulsCount;
                    }

                    //Replaces old soulCount value with new soulCount value
                    mainClass.changeSoulsCount(playerUUID, playerSoulsCount + soulsForKill);
                    break;
                }
                case SKELETON:
                case ZOMBIE: {
                    // Sets amount of souls player will get when killing entity above ^^^
                    int soulsForKill = 10;

                    // If playerSoulsCount after a kill will be bigger than maxSouls sets player soulsCount to maxSouls
                    if (playerSoulsCount + soulsForKill > maxSouls) {
                        soulsForKill = maxSouls - playerSoulsCount;
                    }

                    //Replaces old soulCount value with new soulCount value
                    mainClass.changeSoulsCount(playerUUID, playerSoulsCount + soulsForKill);
                    break;
                }
                case SPIDER: {
                    // Sets amount of souls player will get when killing entity above ^^^
                    int soulsForKill = 5;

                    // If playerSoulsCount after a kill will be bigger than maxSouls sets player soulsCount to maxSouls
                    if (playerSoulsCount + soulsForKill > maxSouls) {
                        soulsForKill = maxSouls - playerSoulsCount;
                    }

                    //Replaces old soulCount value with new soulCount value
                    mainClass.changeSoulsCount(playerUUID, playerSoulsCount + soulsForKill);
                    break;
                }
            }
        }
    }

    // Displays souls on exp bar and updates each 2 seconds
    @EventHandler
    public void soulsDisplayAtExpBar(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        // Create a timed runnable executing every 40 ticks
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(mainClass, new Runnable() {
            public void run() {
                // If player is on the soulsCount list
                if(soulsCount.containsKey(playerUUID)) {
                    // Converts int to percentage.
                    float barCompletion = (float) soulsCount.get(playerUUID) / 100;
                    // Display player soulsCount as XP Level
                    player.setLevel(soulsCount.get(playerUUID));

                    // Display player soulsCount as percentage on XP Bar
                    player.setExp(barCompletion);
                }
            }
        },0, 20);
    }

    // Cancels exp drops from entity deaths
    @EventHandler
    public void EntityDeathEvent(EntityDeathEvent event) {
        event.setDroppedExp(0);
    }
}