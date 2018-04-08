package come.cocopixelmc.FastSpawn.Sign;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import come.cocopixelmc.FastSpawn.main;

public class Signs implements Listener{
	
	private final main plugin;
	
	public Signs(main plugin){
	    this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSignEdit(SignChangeEvent e){
		
		Player player = e.getPlayer();
		String[] text = e.getLines();
		
		if(player.hasPermission("FastSpawn.admin")){
			if(text[0].equalsIgnoreCase("[FS]")){
				if(text[1].equalsIgnoreCase("Join")){
					e.setLine(0, "["+ChatColor.AQUA+"FS"+ChatColor.RESET+"]");
					e.setLine(1, ChatColor.GREEN+"Join");
		
					if(plugin.getConfig().getConfigurationSection("area").contains(text[2])){
					
						player.sendMessage(ChatColor.GREEN+ "設置成功");
					
					}else{
						player.sendMessage(ChatColor.RED+ "設置失敗");
					}
				
				}
				if(text[1].equalsIgnoreCase("Quit")){
					e.setLine(0, "["+ChatColor.AQUA+"FS"+ChatColor.RESET+"]");
					e.setLine(1, ChatColor.RED + "Quit");
					player.sendMessage(ChatColor.GREEN+ "設置成功");
				}
			}
		}
	}
}
