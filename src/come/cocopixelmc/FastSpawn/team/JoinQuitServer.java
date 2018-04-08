package come.cocopixelmc.FastSpawn.team;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import come.cocopixelmc.FastSpawn.main;

public class JoinQuitServer implements Listener {
	
	@SuppressWarnings("unused")
	private final main plugin;

	public JoinQuitServer(main plugin){
	    this.plugin=plugin;
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
    //Join
    public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if(Teams.getPlayerInWhereArea().containsKey(player)){
			Teams.QuitTeam(player);
		}
	}
}
