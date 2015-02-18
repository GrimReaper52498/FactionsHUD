package me.GrimReaper52498.FactionsHud;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Lisa on 2/13/2015.
 */
public class Main extends JavaPlugin
{
    public BukkitTask sbTask = null;
    private Scoreboard boardHandler = null;
    public int refresh = 1;

    @Override
    public void onEnable()
    {
        startScoreboardTask();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    @Override
    public void onDisable()
    {
        stopScoreboardTask();
        saveDefaultConfig();
    }

    public void startScoreboardTask()
    {
        if (sbTask == null)
        {
            sbTask = getServer().getScheduler().runTaskTimerAsynchronously(this, new Interval(this), 0L, 20L * refresh);
        }
        else
        {
            sbTask.cancel();
            sbTask = null;
            sbTask = getServer().getScheduler().runTaskTimerAsynchronously(this, new Interval(this), 0L, 20L * refresh);
        }
    }
    public void stopScoreboardTask()
    {
        if (sbTask != null)
        {
            sbTask.cancel();
            sbTask = null;
        }
    }
    public Scoreboard getBoardHandler()
    {
        if (boardHandler == null)
        {
            boardHandler = new Scoreboard(this);
            return boardHandler;

        }
        return boardHandler;
    }

    public boolean isInFactionTerritory(Player player)
    {

        Location loc = player.getLocation();
        Faction fac = BoardColl.get().getFactionAt(PS.valueOf(loc));

        if((fac.isNone() || (fac.getId().equalsIgnoreCase("safezone")) || (fac.getId().equalsIgnoreCase("warzone")))) {
        return false;
        }
      return true;
    }

}
