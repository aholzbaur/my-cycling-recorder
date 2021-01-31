package de.aholzbaur.mycyclingrecorder.data;

public class Settings {
    private boolean useGpsLocation = false;
    private boolean useGpsSpeedDistance = false;

    private static final Settings instance = new Settings();

    public static Settings getInstance() {
        return instance;
    }

    public boolean isUseGpsLocation() {
        return this.useGpsLocation;
    }

    public void setUseGpsLocation(boolean useGpsLocation) {
        this.useGpsLocation = useGpsLocation;
    }

    public boolean isUseGpsSpeedDistance() {
        return this.useGpsSpeedDistance;
    }

    public void setUseGpsSpeedDistance(boolean useGpsSpeedDistance) {
        this.useGpsSpeedDistance = useGpsSpeedDistance;
    }
}