package project.ssrl.report;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CustomReport implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            if(args.length == 2) {
                final Player targetPlayer = Bukkit.getPlayer(args[0]);
                if(targetPlayer != null) {
                    Bukkit.getOnlinePlayers().forEach(x -> {
                        if(x.hasPermission("report.see")) {
                            x.sendMessage("§b*-- §cNEW REPORT! §b*--");
                            x.sendMessage("§aThe player §f" + player.getName() + " §areported §f" + targetPlayer.getName());
                            x.sendMessage("§aReason: " + ReportType.getType(args[1]));
                        }
                    });
                } else player.sendMessage("§cPlayer not found");
            } else player.sendMessage("§c/report [player] [reasons]");
        }
        return false;
    }
}
