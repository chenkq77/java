package com.jtv.server;

import java.rmi.RemoteException;

import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) throws RemoteException {
		IGwServiceProxy pro = new IGwServiceProxy();
		JSONObject obj = new JSONObject();
		// text
		obj.put("sql", "select to_char(createtime,'yyyyMMdd') createtime,title from (select a.*,rownum r from (select * from weather  order by  createtime desc) a) where r =1");
		obj.put("type", "jtvzzqgd");
		String res = pro.publicInfo(obj.toString(), "zzrain");
		System.out.println(res);
	}

}
