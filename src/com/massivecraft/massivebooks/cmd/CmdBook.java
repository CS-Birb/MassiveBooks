package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdBook extends MassiveBooksCommand
{
	// SubCommands
	// TODO
	
	public CmdBook()
	{
		super();
		
		// Aliases
		this.setAliases(ConfServer.aliasesBook);
		
		// Help SubCommand 
		this.addSubCommand(HelpCommand.get());
		
		// Add SubCommands
		// TODO
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.CMD_BOOK.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}