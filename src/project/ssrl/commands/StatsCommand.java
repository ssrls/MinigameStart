package project.ssrl.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import project.ssrl.Stats;
import project.ssrl.utils.PlayerHandler;

import java.util.Arrays;

public class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            final Player player = (Player) sender;
            if(args.length == 0) {
                Inventory inventory = Bukkit.createInventory(null, 9, "§cYour Stats");
                inventory.setItem(10, Stats.setMeta(new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()), "§e§lPart [1]", null,
                        Arrays.asList(
                                "§f" + player.getName() + "'s Stats",
                                "§cX",
                                "§fKill Streak: " + Stats.getPlayerHandler().getStreak(player.getName()),
                                "§cX"
                        ), null));

                player.openInventory(inventory);
            } else if(args.length == 1) {
                final Player targetPlayer = Bukkit.getPlayer(args[0]);
                Inventory inventory = Bukkit.createInventory(null, 9, "§cStats GUI");
                inventory.setItem(10, Stats.setMeta(new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()), "§e§lPart [1]", null,
                        Arrays.asList(
                                "§f" + targetPlayer.getName() + "'s Stats",
                                "§cX",
                                "§fKill Streak: " + Stats.getPlayerHandler().getStreak(targetPlayer.getName()),
                                "§cX"
                        ), null));

                player.openInventory(inventory);
            }
        }
        return false;
    }
}
