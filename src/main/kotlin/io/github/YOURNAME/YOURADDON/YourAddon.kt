package io.github.YOURNAME.YOURADDON

import io.github.seggan.sf4k.AbstractAddon

object YourAddon : AbstractAddon() {

    override suspend fun onEnableAsync() {
        // Code to run on server startup

        YourAddonItems // Referencing the items object to initialize it
    }

    override suspend fun onDisableAsync() {
        // Code to run on server shutdown
    }

    override fun getBugTrackerURL(): String? = null
}