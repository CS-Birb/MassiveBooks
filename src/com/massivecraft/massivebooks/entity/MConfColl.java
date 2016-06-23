package com.massivecraft.massivebooks.entity;

import java.util.Map.Entry;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.xlib.gson.JsonObject;

public class MConfColl extends Coll<MConf>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MConfColl i = new MConfColl();
	public static MConfColl get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void setActive(boolean active)
	{
		super.setActive(active);
		if ( ! active) return;
		MConf.i = this.get(MassiveCore.INSTANCE, true);
		this.createUpdatePermissionNodes();
	}
	
	@Override
	public synchronized void loadFromRemoteFixed(String id, Entry<JsonObject, Long> entry)
	{
		super.loadFromRemoteFixed(id, entry);
		if ( ! this.isActive()) return;
		this.createUpdatePermissionNodes();
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public void createUpdatePermissionNodes()
	{
		for (MConf mconf : this.getAll())
		{
			mconf.createUpdatePermissionNodes();
		}
	}
	
}
