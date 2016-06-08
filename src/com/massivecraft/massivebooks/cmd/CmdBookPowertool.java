package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Const;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanTrue;

public class CmdBookPowertool extends MassiveBooksCommand
{
	public CmdBookPowertool()
	{
		// Aliases
		this.addAliases("pt", "powertool");
		
		// Parameters
		this.addParameter(TypeBooleanTrue.get(), "true/false", "toggle");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.POWERTOOL));
		this.addRequirements(RequirementIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		ItemStack item = TypeBookInHand.getWritten().read(sender);
		
		boolean currentState = BookUtil.containsFlag(item, Const.POWERTOOL);
		boolean targetState = this.readArg(!currentState);
		
		// Detect NoChange
		if (targetState == currentState)
		{
			if (targetState)
			{
				message(Lang.POWERTOOL_ALREADY_TRUE);
			}
			else
			{
				message(Lang.POWERTOOL_ALREADY_FALSE);
			}
			return;
		}
		
		// Check other-perm if another author
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.POWERTOOL_OTHER.has(sender, true)) return;
		
		// Apply & Inform
		if (targetState)
		{
			BookUtil.addFlag(item, Const.POWERTOOL);
			message(Lang.POWERTOOL_CHANGED_TO_TRUE);
		}
		else
		{
			BookUtil.removeFlag(item, Const.POWERTOOL);
			message(Lang.POWERTOOL_CHANGED_TO_FALSE);
		}
	}

}
