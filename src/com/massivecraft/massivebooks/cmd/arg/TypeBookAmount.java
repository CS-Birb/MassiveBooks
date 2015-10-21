package com.massivecraft.massivebooks.cmd.arg;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.type.TypeAbstract;
import com.massivecraft.massivecore.cmd.type.TypeAbstractNumber;
import com.massivecraft.massivecore.cmd.type.TypeInteger;

public class TypeBookAmount extends TypeAbstract<Integer>
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static final Integer ENSURE = Integer.MIN_VALUE;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeBookAmount i = new TypeBookAmount();
	public static TypeBookAmount get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Integer read(String arg, CommandSender sender) throws MassiveException
	{
		if (StringUtils.startsWithIgnoreCase(arg, "e")) return ENSURE;
		int ret = TypeInteger.get().read(arg, sender);
		if (ret <= 0) throw new MassiveException().addMessage(Lang.TIMES_MUST_BE_POSITIVE);
		return ret;
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return TypeAbstractNumber.TAB_LIST;
	}

}