package come.cocopixelmc.FastSpawn;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import come.cocopixelmc.FastSpawn.Sign.ClickEvent;
import come.cocopixelmc.FastSpawn.Sign.Signs;
import come.cocopixelmc.FastSpawn.spawn.Spawn;
import come.cocopixelmc.FastSpawn.spawn.Spawn2;
import come.cocopixelmc.FastSpawn.team.JoinQuitServer;
import come.cocopixelmc.FastSpawn.team.Teams;

public class main extends JavaPlugin implements Listener{
	
	@Override
    public void onEnable() {
		
		getLogger().info("Loading...");//load
		
		//TitleAPI
		if (Bukkit.getPluginManager().isPluginEnabled("TitleAPI")){
			Bukkit.getPluginManager().registerEvents(this, this);
		} else {
			Load.NoneTitleAPI();
			throw new RuntimeException("TitleAPI is not find!! ");
		}
		
		//BarAPI
		if (Bukkit.getPluginManager().isPluginEnabled("BossBarAPI")){
			Bukkit.getPluginManager().registerEvents(this, this);
		} else {
			Load.NoneBarAPI();
			throw new RuntimeException("BarAPI is not find!! ");
		}
			
		//ActionBarAPI
		if (Bukkit.getPluginManager().isPluginEnabled("ActionBarAPI")){
			Bukkit.getPluginManager().registerEvents(this, this);
		} else {
			Load.NoneActionBarAPI();
			throw new RuntimeException("ActionBarAPI is not find!! ");
		}
		
		//class
		new Spawn(this);
		new Spawn2(this);
		new Teams(this);
		new Load(this);
		new BlockCmd(this);
		new JoinQuitServer(this);
		new Signs(this);
		new ClickEvent(this);
		getCommand("fastspawn").setExecutor(new Cmd(this));
		getCommand("fs").setExecutor(new Cmd(this));
		
		//config
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		
		//Team
		Teams.createTeam();
		
		//sendEnabled
		Load.sendEnabled();
	}
	
	@Override
    public void onDisable() {
    	getLogger().info("Unloading...");
    	Load.sendDisabled();
    }

}
