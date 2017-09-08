package net.kevinmendoza.sedimentarysequences.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import com.google.common.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.utilities.Debug;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;
import net.kevinmendoza.sedimentarysequences.configuration.ConfigBind;
import net.kevinmendoza.sedimentarysequences.configuration.StratigraphicDefaults;
import net.kevinmendoza.sedimentarysequences.stratigraphy.StratigraphyHub;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Plugin(
id=SedSequencesMain.ID,
name=SedSequencesMain.NAME,
version=SedSequencesMain.VERSION,
url="http://www.kevinmendoza.net/geoworld-a-minecraft-geology-addon/",
authors = {"El_Minadero"},
description = "A Geologic Minecraft Mod",
dependencies = @Dependency(id = "geoworld", optional = false))
public class SedSequencesMain implements GeoWorldPlugin  {
	public static final String ID = "sedimentarysequences";
	public static final String NAME = "Sedimentary Sequences";
	public static final String VERSION = "1.0.1a";
	public static SedSequencesMain PluginMain;
	private StratigraphicDefaults defaults;
	@Inject
	private Logger logger; 
	@Inject 
	private PluginContainer container;
	@Inject @DefaultConfig(sharedRoot = true)
	private File file; 
	@Inject @DefaultConfig(sharedRoot = true) 
	private ConfigurationLoader<CommentedConfigurationNode> loader;
	
	public SedSequencesMain() {
		PluginMain = this;
	}
	@Listener
	public void onGamePreInitialization(GamePreInitializationEvent event) throws IOException, ObjectMappingException {
	 createConfigs();
	}
	
	@Listener
	public void onGameReload(GameReloadEvent event) throws IOException, ObjectMappingException {
		createConfigs();
	}
	
	public IGeology getGeology(long seed) {
		return StratigraphyHub.GetSedimentarySequence(seed);
	}

	public void createConfigs() throws IOException, ObjectMappingException {
		ConfigurationNode node = loader.createEmptyNode();
		node.setValue(StratigraphicDefaults.type, defaults == null ? (defaults= new StratigraphicDefaults()) : defaults);
		loader.save(node);
	}
	
	public PluginContainer GetPluginContainer() {
		return container;
	}

	public Logger GetLog() {
		return logger;
	}

	@Override
	public String GetPluginID() { return ID; }
	
}
