package edu.eci.arsw.blacklistvalidator;
import java.util.LinkedList;
import java.util.List;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class ThreadSearch extends Thread {
	private String ip;
	private int a;
	private int b;
	private LinkedList<Integer> blackListOcurrences;
	private HostBlacklistsDataSourceFacade skds;
	private int checkedListsCount;
	
	public ThreadSearch(String ip, int a, int b) {
		this.ip = ip;
		this.a = a;
		this.b = b;
		blackListOcurrences = new LinkedList<>();
		skds=HostBlacklistsDataSourceFacade.getInstance();
		checkedListsCount = 0;
	}
	
	public void run() {
		for(int i = a; i < b; i++) {
			if (skds.isInBlackListServer(i, ip)) {
				blackListOcurrences.add(i);
				checkedListsCount += 1;
			}
		}
		
	}
	
	public LinkedList<Integer> getblacklistOcurrences() {
		return blackListOcurrences;
	}
	
	public int getCheckedListsCount() {
		return checkedListsCount;
	}
	
}
