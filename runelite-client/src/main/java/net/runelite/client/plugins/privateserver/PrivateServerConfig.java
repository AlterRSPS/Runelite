package net.runelite.client.plugins.privateserver;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("privateserver")

public interface PrivateServerConfig extends Config
{

    @ConfigItem(
            keyName = "codebase",
            name = "URL (codebase)",
            description = "IP address or URL of RSPS you wish to connect to.",
            position = 0
    )
    default String codebase()
    {
        return "http://127.0.0.1";
    }

    @ConfigItem(
            keyName = "modulus",
            name = "Key (modulus)",
            description = "RSA key used by the RSPS you wish to connect to.",
            position = 1
    )
    default String modulus()
    {
        return "";
    }

}