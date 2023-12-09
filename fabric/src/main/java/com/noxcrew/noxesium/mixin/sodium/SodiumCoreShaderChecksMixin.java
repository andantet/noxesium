package com.noxcrew.noxesium.mixin.sodium;

import me.jellysquid.mods.sodium.client.compatibility.checks.ResourcePackScanner;
import me.jellysquid.mods.sodium.client.gui.console.message.MessageLevel;
import net.minecraft.server.packs.PackResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(ResourcePackScanner.class)
public class SodiumCoreShaderChecksMixin {
    @Inject(method = "lambda$checkIfCoreShaderLoaded$1", at = @At("HEAD"), cancellable = true)
    private static void onCoreShaderCheck(HashMap<String, MessageLevel> detectedResourcePacks, PackResources resourcePack, CallbackInfo ci) {
        String packId = resourcePack.packId();
        if (packId.equals("server") || packId.startsWith("server/")) {
            ci.cancel();
        }
    }
}
