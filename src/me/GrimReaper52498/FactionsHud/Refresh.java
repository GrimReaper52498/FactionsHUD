package me.GrimReaper52498.FactionsHud;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by GrimReaper52498 on 1/11/2015.
 */
public class Refresh implements Runnable {
    private Main plugin;
    private final Player player;

    public Refresh(Main instance, Player player) {
        this.plugin = instance;
        this.player = player;
    }

    public void run() {
        Player p = Bukkit.getServer().getPlayer(player.getName());
        if (p == null) {
            return;
        }
        this.plugin.getBoardHandler().updateScoreboard(p);
    }

}
