public class Tankwatch {
    private int fillPortions;
    private boolean systemOnline;
    private boolean alarm1;
    private boolean alarm2;

    Tankwatch(){

    }

    public int getFillPortions() {
        return fillPortions;
    }

    public void setFillPortions(int fillPortions) {
        this.fillPortions = fillPortions;
    }

    public boolean isSystemOnline() {
        return systemOnline;
    }

    public void setSystemOnline(boolean systemOnline) {
        this.systemOnline = systemOnline;
    }

    public boolean isAlarm1() {
        return alarm1;
    }

    public void setAlarm1(boolean alarm1) {
        this.alarm1 = alarm1;
    }

    public boolean isAlarm2() {
        return alarm2;
    }

    public void setAlarm2(boolean alarm2) {
        this.alarm2 = alarm2;
    }
}
