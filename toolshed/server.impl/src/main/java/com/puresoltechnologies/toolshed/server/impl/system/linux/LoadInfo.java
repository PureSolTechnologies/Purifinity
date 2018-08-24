package com.puresoltechnologies.toolshed.server.impl.system.linux;

public class LoadInfo {

    private final int cpuId;
    private final long user;
    private final long nice;
    private final long system;
    private final long idle;
    private final long iowait;
    private final long irq;
    private final long softirq;
    private final long steal;
    private final long quest;
    private final long questNice;
    private final long total;

    public LoadInfo(int cpuId, long user, long nice, long system, long idle, long iowait, long irq, long softirq,
	    long steal, long quest, long questNice) {
	super();
	this.cpuId = cpuId;
	this.user = user;
	this.nice = nice;
	this.system = system;
	this.idle = idle;
	this.iowait = iowait;
	this.irq = irq;
	this.softirq = softirq;
	this.steal = steal;
	this.quest = quest;
	this.questNice = questNice;
	this.total = user + nice + system + idle + iowait + irq + softirq + steal + quest + questNice;
    }

    public int getCpuId() {
	return cpuId;
    }

    public long getUser() {
	return user;
    }

    public long getNice() {
	return nice;
    }

    public long getSystem() {
	return system;
    }

    public long getIdle() {
	return idle;
    }

    public long getIowait() {
	return iowait;
    }

    public long getIrq() {
	return irq;
    }

    public long getSoftirq() {
	return softirq;
    }

    public long getSteal() {
	return steal;
    }

    public long getQuest() {
	return quest;
    }

    public long getQuestNice() {
	return questNice;
    }

    public long getTotal() {
	return total;
    }
}
