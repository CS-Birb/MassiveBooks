package com.massivecraft.massivebooks.cmd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

public class CmdBookList extends MassiveBooksCommand
{
	public CmdBookList()
	{
		super();
		this.addAliases(ConfServer.aliasesBookList);
		this.addOptionalArg("page", "1");
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	@Override
	public void perform()
	{
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		if (pageHumanBased == null) return;
		
		// Create Messages
		List<String> lines = new ArrayList<String>();
		
		Collection<MBook> mbooks = MBookColl.get().getAll();
		for (MBook mbook : mbooks)
		{
			lines.add(Lang.descDisplayName(mbook.getItem()));
		}
		
		// Send them
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, String.valueOf(mbooks.size())+" Saved Books", sender));	
	}
}