package de.aholzbaur.mycyclingrecorder.data;

public class Settings {
    private boolean enableLocation = false;
    private boolean enableDistance = false;
    private boolean useLocationForDistance = false;
    private boolean enableSpeed = false;
    private boolean useLocationForSpeed = false;
    private boolean enableCadence = false;

    private static final Settings settings = new Settings();

    private Settings() {

    }

    public static final Settings getInstance() {
        return settings;
    }

    public boolean isEnableLocation() {
        return enableLocation;
    }

    public void setEnableLocation(boolean enableLocation) {
        this.enableLocation = enableLocation;
    }

    public boolean isEnableDistance() {
        return enableDistance;
    }

    public void setEnableDistance(boolean enableDistance) {
        this.enableDistance = enableDistance;
    }

    public boolean isUseLocationForDistance() {
        return useLocationForDistance;
    }

    public void setUseLocationForDistance(boolean useLocationForDistance) {
        this.useLocationForDistance = useLocationForDistance;
    }

    public boolean isEnableSpeed() {
        return enableSpeed;
    }

    public void setEnableSpeed(boolean enableSpeed) {
        this.enableSpeed = enableSpeed;
    }

    public boolean isUseLocationForSpeed() {
        return useLocationForSpeed;
    }

    public void setUseLocationForSpeed(boolean useLocationForSpeed) {
        this.useLocationForSpeed = useLocationForSpeed;
    }

    public boolean isEnableCadence() {
        return enableCadence;
    }

    public void setEnableCadence(boolean enableCadence) {
        this.enableCadence = enableCadence;
    }
}
