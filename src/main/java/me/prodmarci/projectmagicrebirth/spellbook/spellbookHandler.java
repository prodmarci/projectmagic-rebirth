package me.prodmarci.projectmagicrebirth.spellbook;

import me.prodmarci.projectmagicrebirth.main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import java.util.HashMap;
import java.util.Objects;

public class spellbookHandler implements Listener {
    main mainClass;
    public HashMap<String, Integer> soulsCount;
    public HashMap<String, String> spellSelected;

    // Allows accessing variables from main class
    public spellbookHandler(main m) {
        this.mainClass = m;
        this.soulsCount = m.soulsCount;
        this.spellSelected = m.spellSelected;
    }

    // Checks book for spells written then equips them if player had enough mana
    @EventHandler
    public void onSpellbookWrite(PlayerEditBookEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        // Take meta info of book
        BookMeta bookMeta = event.getNewBookMeta();

        // Sets required spellbook name
        String spellbookDisplayName = "Bond of Blood";

        // If spell is not chosen lets player choose spell
        if (!spellSelected.containsKey(playerUUID)) {

            // Checks if player's book is named the same as spellbookDisplayName variable and if the spellbook has loyalty enchantment when opening
            if (Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(spellbookDisplayName)
                    && player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOYALTY)) {

                // Get 1 st page spellbookContent
                String spellbookContent = bookMeta.getPage(1);

                // Depending on spellbookContent select certain spell
                switch (spellbookContent) {
                    case "TP": {
                        // Spell name variable assign for code clarity
                        String spellName = "TP";

                        // Adds player with certain spell to spellSelected hashMap
                        mainClass.selectSpell(playerUUID, spellName);
                        break;
                    }
                    case "ICE": {
                        // Spell name variable assign for code clarity
                        String spellName = "ICE";

                        // Adds player with certain spell to spellSelected hashMap
                        mainClass.selectSpell(playerUUID, spellName);
                        break;
                    }
                }
            }
        }
    }
}