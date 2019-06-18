package me.glaremasters.mcauth;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import me.glaremasters.mcauth.database.DatabaseProvider;
import me.glaremasters.mcauth.database.mysql.MySQL;
import me.glaremasters.mcauth.events.Login;
import org.bukkit.plugin.java.JavaPlugin;

public final class McAuth extends JavaPlugin {

    private static TaskChainFactory taskChainFactory;
    private DatabaseProvider database;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        taskChainFactory = BukkitTaskChainFactory.create(this);
        database = new MySQL(this);
        database.init();
        getServer().getPluginManager().registerEvents(new Login(this), this);
    }

    @Override
    public void onDisable() {

    }

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public DatabaseProvider getDatabase() {
        return database;
    }
}
