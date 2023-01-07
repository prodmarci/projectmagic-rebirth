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

    // Allows accessing variables from main class
    public spellbookHandler(main m) {
        this.mainClass = m;
        this.soulsCount = m.soulsCount;
    }

    // Checks book for spells written then equips them if player had enough mana
    @EventHandler
    public void onSpellbookWrite(PlayerEditBookEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        // Take meta info of book
        BookMeta bookMeta = event.getNewBookMeta();

        // Sets required spellbook name
        String spellbookDisplayName = "Bound of Blood";

        // Checks if player's book is named the same as spellbookDisplayName variable and if the spellbook has loyalty enchantment
        if (Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(spellbookDisplayName)
            && player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOYALTY)) {
            String spellbookContent = bookMeta.getPage(1);

            switch (spellbookContent) {
                case "COF": {
                    player.sendMessage("COF");
                    break;
                }
                case "ICEC": {
                    player.sendMessage("ICEC");
                    break;
                }
            }
        }
    }
}
