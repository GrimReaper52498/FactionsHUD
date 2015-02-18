package me.GrimReaper52498.FactionsHud;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by GrimReaper52498 on 1/11/2015.
 */
public class Interval implements Runnable
{
    private Main plugin;

    public Interval(Main instance)
    {
	this.plugin = instance;
    }

    public void run()
    {
	for (Player player : Bukkit.getOnlinePlayers())
	{
	    this.plugin.getServer().getScheduler().runTask(this.plugin,
			    new Refresh(this.plugin, player));
	}
    }
}
