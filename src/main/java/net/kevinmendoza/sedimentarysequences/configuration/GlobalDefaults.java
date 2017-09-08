package net.kevinmendoza.sedimentarysequences.configuration;

import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class GlobalDefaults implements IGlobalDefaults {
	public static final TypeToken<GlobalDefaults> type = TypeToken.of(GlobalDefaults.class);

	@Setting
	private Order order;
	
	public GlobalDefaults() {
		order = Order.FIRST;
	}
	@Override
	public Order getOrder() {
		return order;
	}
}
