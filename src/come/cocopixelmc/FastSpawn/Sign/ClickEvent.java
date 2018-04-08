package come.cocopixelmc.FastSpawn.Sign;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import come.cocopixelmc.FastSpawn.main;
import come.cocopixelmc.FastSpawn.team.Teams;

public class ClickEvent implements Listener {

private final main plugin;
	
	public ClickEvent(main plugin){
	    this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		
		Block block = e.getClickedBlock();
		try{
		if (block.getState() instanceof Sign){
			
			//System.out.println("work 1");//debug
			
			Sign sign = (Sign) block.getState();
			String[] line = sign.getLines();
			Player player = e.getPlayer();
			
			if(line[0].contains("FS")){
				
				//System.out.println("work 2");//debug
				
				//join
				if(line[1].equals(ChatColor.GREEN+"Join")){
					
					//System.out.println("work 3");//debug
					
					if(!Teams.getPlayerInWhereArea().containsKey(player)){
						if(plugin.getConfig().getConfigurationSection("area").contains(line[2])){
							Teams.JoinTeam(player, line[2]);
						}else{
							player.sendMessage(ChatColor.RED + "場地不存在");
						}
					}else{
						player.sendMessage(ChatColor.RED + "You is in game");
					}
				}
				
				if(line[1].equals(ChatColor.RED+"Quit")){
					
					//System.out.println("work 3");//debug
					
					if(Teams.getPlayerInWhereArea().containsKey(player)){
						Teams.QuitTeam(player);
					}else{
						player.sendMessage(ChatColor.RED + "You not in game");
					}
				}
			}
		}
		}catch(NullPointerException e1){
			
		}
	}
}
