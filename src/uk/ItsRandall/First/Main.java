package uk.ItsRandall.First;

import java.io.PrintStream;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
        extends JavaPlugin
        implements Listener
{
    public void onEnable()
    {
        System.out.println(ChatColor.GREEN + "Test plugin intiated");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable()
    {
        System.out.println(ChatColor.RED + "Test plugin terminated");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        event.setJoinMessage("§7[§a+§7] §7" + player.getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§c-§7] §7" + player.getName());
    }

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        e.setFormat(ReferenceManager.prefix);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.isCancelled()) return;

        Block b = e.getBlock();

        if (b.getType() != Material.LOG && b.getType() != Material.LOG_2) {
            return;
        }

        b = b.getRelative(BlockFace.UP);

        while (b.getType() == Material.LOG || b.getType() == Material.LOG_2) {
            b.breakNaturally();
            b = b.getRelative(BlockFace.UP);
        }
    }
}
