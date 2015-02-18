package me.GrimReaper52498.FactionsHud;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * Created by GrimReaper52498 on 1/11/2015.
 */
public class Scoreboard {
    private Main plugin;

    public Scoreboard(Main instance) {
        this.plugin = instance;
    }

    public void removeScoreboard(Player p) {
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public void updateScoreboard(OfflinePlayer p) {
        if (plugin.isInFactionTerritory((Player) p)) {
            createScoreboard(p);
        }else{
            removeScoreboard((Player) p);
        }

    }

    public void createScoreboard(OfflinePlayer p) {
        org.bukkit.scoreboard.Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("dummy", "begin");
        MPlayer mp = MPlayer.get(p);
        Location loc = ((Player) p).getLocation();
        Faction fac = BoardColl.get().getFactionAt(PS.valueOf(loc));

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        String facName = fac.getName().toString();
        String facLeader = fac.getLeader().getName().toString();
        int powerLvl = fac.getPowerRounded();
        int maxPower = fac.getPowerMaxRounded();
        int onlinePlayers = fac.getOnlinePlayers().size();
        int facMembers = fac.getMPlayers().size();

        String color1 = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MainColor"));
        String color2 = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("SecondaryColor"));
        String nameColor = ChatColor.translateAlternateColorCodes('&',  plugin.getConfig().getString("FactionNameColor"));

        obj.setDisplayName(nameColor + facName);

        Score bl1 = obj.getScore(" ");
        bl1.setScore(9);
        //LEADER
        Score leaderTxt = obj.getScore(Bukkit.getOfflinePlayer(color1 + "Leader"));
        leaderTxt.setScore(8);
        Score leader = obj.getScore(Bukkit.getOfflinePlayer(facLeader));
        leader.setScore(7);

        //
        Score bl2 = obj.getScore("  ");
        bl2.setScore(6);
        //POWER

        Score powerTxt = obj.getScore(Bukkit.getOfflinePlayer(color1 + "Power"));
        powerTxt.setScore(5);
        Score power = obj.getScore(color2 + powerLvl + color1 + "/" + color2 + maxPower);
        power.setScore(4);

        //
        Score bl3 = obj.getScore("   ");
        bl3.setScore(3);

        //MEMBERS


        Score memTxt = obj.getScore(color1 + "Members Online");
        memTxt.setScore(2);
        Score mem = obj.getScore(color2 + onlinePlayers + color1 + "/" + color2 + facMembers);
        mem.setScore(1);

        ((Player) p).setScoreboard(sb);
    }
}
