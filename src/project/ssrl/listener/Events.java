package project.ssrl.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import project.ssrl.Stats;

public class Events implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player attacker = e.getEntity().getKiller();
        Player killed = e.getEntity();
        if (attacker != null && attacker.getName() != killed.getName()) {
            final int deaths = Stats.getPlayerHandler().getFileConfiguration().getInt("players." + attacker.getName().toLowerCase() + ".deaths");
            final int kills = Stats.getPlayerHandler().getFileConfiguration().getInt("players." + attacker.getName().toLowerCase() + ".totalkills");
            String division = String.format("%02.2f", (double) kills / deaths);
            Stats.getPlayerHandler().add(attacker.getName(), Stats.getPlayerHandler().getStreak(attacker.getName()) + 1);
            Stats.getPlayerHandler().setData("players." + attacker.getName().toLowerCase() + ".name", attacker.getName());
            Stats.getPlayerHandler().setData("players." + attacker.getName().toLowerCase() + ".killstreak", Stats.getPlayerHandler().getStreak(attacker.getName()));
            Stats.getPlayerHandler().setData("players." + attacker.getName().toLowerCase() + ".totalkills", + 1);
            Stats.getPlayerHandler().setData("players." + attacker.getName().toLowerCase() + ".kdratio", division);
            Stats.getPlayerHandler().add(killed.getName(), 0);
            Stats.getPlayerHandler().setData("players." + killed.getName().toLowerCase() + ".deaths", + 1);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if(Stats.getPlayerHandler().getObject().containsKey(player.getName())) Stats.getPlayerHandler().remove(player.getName());
    }
}
