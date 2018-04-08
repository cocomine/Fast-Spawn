package come.cocopixelmc.FastSpawn;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public class Load implements Listener{
	
	private static main plugin;


	@SuppressWarnings("static-access")
	public Load(main plugin) {
		this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}


	public static void sendEnabled()
	  {
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "=========================");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "  Fast Spawn Enabled!   ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "       By: cocomine      ");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "=========================");
	  }
	
	public static void sendDisabled()
	  {
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "=========================");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "   Fast Spawn Disabled!   ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "        By: cocomine       ");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(" ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "=========================");
	  }
	
	public static void NoneTitleAPI(){
		plugin.getServer().getConsoleSender().sendMessage("[PlayerDeath] " + ChatColor.RED + "TitleAPI is not find!!");
		plugin.getServer().getConsoleSender().sendMessage("[PlayerDeath] " + ChatColor.RED + "After installing the API plugin, restart the server for reloading");
	}
	
	public static void NoneBarAPI(){
		plugin.getServer().getConsoleSender().sendMessage("[PlayerDeath] " + ChatColor.RED + "BarAPI is not find!!");
		plugin.getServer().getConsoleSender().sendMessage("[PlayerDeath] " + ChatColor.RED + "After installing the API plugin, restart the server for reloading");
	}
	
	public static void NoneActionBarAPI(){
		plugin.getServer().getConsoleSender().sendMessage("[PlayerDeath] " + ChatColor.RED + "ActionBarAPI is not find!!");
		plugin.getServer().getConsoleSender().sendMessage("[PlayerDeath] " + ChatColor.RED + "After installing the API plugin, restart the server for reloading");
	}
}
