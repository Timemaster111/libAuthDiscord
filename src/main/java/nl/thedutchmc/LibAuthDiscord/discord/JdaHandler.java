package nl.thedutchmc.LibAuthDiscord.discord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import nl.thedutchmc.LibAuthDiscord.LibAuthDiscord;
import nl.thedutchmc.LibAuthDiscord.discord.eventListeners.PrivateMessageReceivedEventListener;

public class JdaHandler {

	private LibAuthDiscord plugin;
	
	public JdaHandler(LibAuthDiscord plugin) {
		this.plugin = plugin;
	} 
	
	private static JDA jda;
	
	public void setup(String token) {
		
		try {
			jda = JDABuilder.createDefault(token).build();
			
			jda.awaitReady();
			
		} catch(LoginException | InterruptedException e) {
			plugin.logWarn("There was an issue loggin in to Discord. Please check your internet connection and the bot token!");
		}
		
		jda.addEventListener(new PrivateMessageReceivedEventListener());
	}
	
	public String getBotNameAsTag() {
		return jda.getSelfUser().getAsTag();
	}
	
	public JDA getJda() {
		return JdaHandler.jda;
	}
}
