package com.noxcrew.noxesium.mixin.sodium;

import me.jellysquid.mods.sodium.client.gui.console.message.MessageLevel;
import me.jellysquid.mods.sodium.client.util.workarounds.InGameChecks;
import net.minecraft.server.packs.PackResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(InGameChecks.class)
public class SodiumInGameCoreShaderChecksMixin {
    @Inject(method = "lambda$checkIfCoreShaderLoaded$1", at = @At("HEAD"), cancellable = true)
    private static void onCoreShaderCheck(HashMap<String, MessageLevel> detectedResourcePacks, PackResources resourcePack, CallbackInfo ci) {
        String packId = resourcePack.packId();
        if (packId.equals("server") || packId.startsWith("server/")) {
            ci.cancel();
        }
    }
}
