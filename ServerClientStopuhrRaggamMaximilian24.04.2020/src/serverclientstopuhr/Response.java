/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclientstopuhr;

/**
 *
 * @author maxim
 */
public class Response {
    private boolean master;
    private boolean count;
    private boolean running;
    private long time;

    public Response(boolean master, boolean count, boolean running, long time) {
        this.master = master;
        this.count = count;
        this.running = running;
        this.time = time;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
