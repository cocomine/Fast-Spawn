package come.cocopixelmc.FastSpawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import come.cocopixelmc.FastSpawn.team.Teams;
import net.md_5.bungee.api.ChatColor;


public class BlockCmd implements Listener{

	@SuppressWarnings("unused")
	private final main plugin;

	public BlockCmd(main plugin){
	    this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event)  {
	 	String[] args = event.getMessage().split(" ");
	 	if(Teams.getPlayerInWhereArea().containsKey(event.getPlayer())){
	 		if(!args[0].equalsIgnoreCase("/fastspawn")) {
	 			if(!args[0].equalsIgnoreCase("/fs")){
	 				event.setCancelled(true);
	 				event.getPlayer().sendMessage(ChatColor.RED + "你不能在遊戲中輸入任何指令,除了 /fastspawn");
	 			}
	 		}
	 	}
	} 	
}
