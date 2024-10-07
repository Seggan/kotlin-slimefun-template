package io.github.YOURNAME.YOURADDON

import io.github.seggan.sf4k.item.builder.ItemRegistry
import io.github.seggan.sf4k.item.builder.MaterialType
import io.github.seggan.sf4k.item.builder.buildSlimefunItemDefaultId
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import org.bukkit.Material

object YourAddonItems : ItemRegistry(YourAddon) {

    // Has a default id of YOUR_ADDON_EXAMPLE_ITEM
    val EXAMPLE_ITEM by buildSlimefunItemDefaultId<SlimefunItem> {
        category = TODO()
        name = "Example Item"
        material = MaterialType.Material(Material.STONE)
        recipeType = TODO()
        recipe = emptyArray()

        +"This is an example item's lore"
    }
}