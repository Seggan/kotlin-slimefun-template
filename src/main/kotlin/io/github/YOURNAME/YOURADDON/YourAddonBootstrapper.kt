package io.github.YOURNAME.YOURADDON

import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.bootstrap.PluginProviderContext
import org.bukkit.plugin.java.JavaPlugin

@Suppress("UnstableApiUsage")
class YourAddonBootstrapper : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
        // Any bootstrapping code you need to do
    }

    override fun createPlugin(context: PluginProviderContext): JavaPlugin {
        return YourAddon
    }
}