/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopwatchraggammaximilian;

/**
 *
 * @author maxim
 */
public class Request {
    private boolean master;
    private boolean start;
    private boolean stop;
    private boolean clear;
    private boolean end;

    public Request(boolean master, boolean start, boolean stop, boolean clear, boolean end) {
        this.master = master;
        this.start = start;
        this.stop = stop;
        this.clear = clear;
        this.end = end;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
