package project.ssrl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import project.ssrl.commands.ReportCommand;
import project.ssrl.commands.StatsCommand;
import project.ssrl.listener.Events;
import project.ssrl.report.CustomReport;
import project.ssrl.utils.PlayerHandler;

import java.util.List;

public class Stats extends JavaPlugin {

    public static Stats instance;
    public static PlayerHandler playerHandler;

    public void onEnable() {
        instance = this;
        playerHandler = new PlayerHandler();
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("report").setExecutor(new CustomReport());
    }

    public void onDisable() {

    }

    public static Stats getInstance() {
        return instance;
    }

    public static ItemStack setMeta(ItemStack m, String name, Enchantment ench, List<String> lore, ItemFlag flag) {
        if (m.getType() != Material.AIR && m.getType() != null) {
            ItemMeta meta = m.getItemMeta();
            meta.setDisplayName(name);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            meta.setLore(lore);
            if(flag != null) meta.addItemFlags(flag);
            if(ench != null) meta.addEnchant(ench, 1, true);
            m.setItemMeta(meta);
        }
        return m;
    }

    public static PlayerHandler getPlayerHandler() {
        return playerHandler;
    }
}
