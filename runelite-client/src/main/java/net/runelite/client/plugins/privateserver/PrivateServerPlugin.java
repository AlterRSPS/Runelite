package net.runelite.client.plugins.privateserver;

import com.google.inject.Provides;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.RuneLite;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
        name = "Private Server",
        description = "Settings for connecting to non official servers",
        tags = {"RSPS", "Server", "Private"},
        enabledByDefault = true
)
@Singleton
@Slf4j
public class PrivateServerPlugin extends Plugin
{
    @Inject
    private Client client;
    @Inject
    private PrivateServerConfig config;
    @Inject
    private EventBus eventBus;

    @Provides
    PrivateServerConfig getConfig(ConfigManager configManager)
    {
        return configManager.getConfig(PrivateServerConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {
        updateConfig();
        log.info("If you will use this client for your live client you are a moron.");
    }

    @Override
    protected void shutDown() throws Exception
    {
        eventBus.unregister(this);
    }

    private void updateConfig()
    {
        if (!config.modulus().equals(""))
        {
            client.setModulus(new BigInteger(config.modulus(), 16));
        } else {
            String message = "You have to change the Key(Modulus) to RSPS that you wish to connect. After it loads and you changed the Key just re-enable the Private Server plugin.\n";
            JOptionPane.showMessageDialog(new JFrame(), message, "[Private Server Plugin] - Default RSA key",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}