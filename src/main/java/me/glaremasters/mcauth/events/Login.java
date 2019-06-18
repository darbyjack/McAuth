package me.glaremasters.mcauth.events;

import me.glaremasters.mcauth.McAuth;
import me.glaremasters.mcauth.database.DatabaseProvider;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.List;

public class Login implements Listener {

    private McAuth mcAuth;
    private DatabaseProvider database;
    private String random = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public Login(McAuth mcAuth) {
        this.mcAuth = mcAuth;
        this.database = mcAuth.getDatabase();
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        String uuid = event.getUniqueId().toString();
        String token = randomAlphaNumeric(10);
        String name = event.getName();
        String ip = event.getAddress().toString().substring(1).split(":")[0];

        if (database.hasToken(uuid)) {
            database.setToken(token, uuid);
        } else {
            database.insertUser(uuid, token);
        }

        StringBuilder sb = new StringBuilder();
        List<String> kickMessage = mcAuth.getConfig().getStringList("kick-message");
        for (String line : kickMessage) {
            sb.append(color(line).replace("{uuid}", uuid).replace("{token}", token).replace("{name}", name).replace("{ip}", ip) + "\n");
        }
        event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, sb.toString());
    }

    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * random.length());
            builder.append(random.charAt(character));
        }
        return builder.toString();
    }

    public String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
