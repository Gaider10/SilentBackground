package me.andrew.silentbackground;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class SilentBackgroundMod implements ModInitializer {
	private static final String CONFIG_PATH = "config/silentbackground.txt";

	public static final HashMap<SoundCategory, Float> backgroundVolumeLevels = new HashMap<>();

	@Override
	public void onInitialize() {
        load();
	}

	public static void setBackgroundVolume(SoundCategory soundCategory, float value) {
		backgroundVolumeLevels.put(soundCategory, value);
		save();
	}

	public static float getModifiedVolume(SoundCategory soundCategory) {
		MinecraftClient mc = MinecraftClient.getInstance();
		float volume = mc.options.getSoundVolume(soundCategory);
		if(!mc.isWindowFocused()) {
			volume *= backgroundVolumeLevels.get(soundCategory);
		}
		return volume;
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH));

			reader.lines().forEach(line -> {
				int sep = line.indexOf(":");
				if(sep == -1) return;

				SoundCategory soundCategory = SoundCategory.valueOf(line.substring(0, sep));
				float value = Float.parseFloat(line.substring(sep + 1));

				backgroundVolumeLevels.put(soundCategory, value);
			});

			reader.close();
		} catch (Exception e) {
			System.out.println("Exception loading " + CONFIG_PATH);
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_PATH));

			for(SoundCategory soundCategory : backgroundVolumeLevels.keySet()) {
				writer.write(soundCategory.name() + ":" + backgroundVolumeLevels.get(soundCategory) + "\n");
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("Exception saving " + CONFIG_PATH);
		}
	}

	static {
		for(SoundCategory soundCategory : SoundCategory.values()) {
			backgroundVolumeLevels.put(soundCategory, 1.0f);
		}
	}
}
