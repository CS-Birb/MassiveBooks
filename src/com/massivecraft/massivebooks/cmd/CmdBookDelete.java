package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.entity.MConf;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeMBook;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBookDelete extends MassiveBooksCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdBookDelete()
	{
		// Parameters
		this.addParameter(TypeMBook.get(), "title", true);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.DELETE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesBookDelete;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MBook mbook = this.readArg();
		
		ItemStack target = mbook.getItem();
		mbook.detach();
		
		message(Lang.getSuccessDelete(target));
	}

}
