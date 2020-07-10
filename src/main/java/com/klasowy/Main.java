package com.klasowy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public final class Main extends JavaPlugin {

    final int build = 16;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getOnlinePlayers().forEach(player -> {
            player.sendMessage("[Plugin] " + ChatColor.GREEN + "KlasowyPlugin v1.2.1 (" + build + ") is enabled!");
        });

        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        sb.registerNewTeam("wild");
        sb.registerNewTeam("pergamon");
        sb.registerNewTeam("molkograd");
        sb.registerNewTeam("vallis");
        sb.registerNewTeam("nether");


        Objects.requireNonNull(sb.getTeam("wild")).setDisplayName("Dzicz");
        Objects.requireNonNull(sb.getTeam("pergamon")).setDisplayName("Pergamon");
        Objects.requireNonNull(sb.getTeam("molkograd")).setDisplayName("Molkograd");
        Objects.requireNonNull(sb.getTeam("vallis")).setDisplayName("Vallis Civitatis");
        Objects.requireNonNull(sb.getTeam("nether")).setDisplayName("Nether");


        Bukkit.getPluginManager().registerEvents(new KlasowyListener(this, sb), this);

        final boolean[] timeShown = {false};

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> {

            long time = getServer().getWorld("world").getTime();

            if(!timeShown[0]) {
                if (time > 50 && time < 400) {
                    timeShown[0] = true;
                    int day = (int) (Math.floor(getServer().getWorld("world").getFullTime()/24000)+1);
                    int year = (int) Math.floor(day/360);
                    day -= year*360;
                    year++;
                    getServer().broadcastMessage(ChatColor.GOLD + "Jest " + ChatColor.GREEN + day + ChatColor.GOLD + " dzień, roku " + ChatColor.GREEN + year + ChatColor.GOLD + ".");
                }
            }

            if(time > 500) timeShown[0] = false;

            }, 0L, 50L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
