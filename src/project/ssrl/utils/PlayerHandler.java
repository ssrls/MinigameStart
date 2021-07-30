package project.ssrl.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlayerHandler {

    protected HashMap<String, Integer> object = new HashMap<>();
    protected File file;
    protected FileConfiguration fileConfiguration;
    protected int deaths;

    public void initStart() {
        this.file = new File("plugins/Stats", "players.yml");;
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void add(String string, Integer integer) {
        this.object.put(string, integer);
    }

    public void remove(String string) {
        this.object.remove(string);
    }

    public HashMap<String, Integer> getObject() {
        return this.object;
    }

    public void setData(String string, Object object) {
        this.fileConfiguration.set(string, object);
        try {
            this.fileConfiguration.save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public FileConfiguration getFileConfiguration() {
        return this.fileConfiguration;
    }

    public int getStreak(String username) {
        if (!this.object.containsKey(username)) add(username, 0);
        return this.object.get(username);
    }

    public void sendTitle(Player player) {
        ((CraftPlayer) player).sendTitle("§cKillstreak", "§aYour killstreak is: " + this.object.get(player.getName()));
    }

}
