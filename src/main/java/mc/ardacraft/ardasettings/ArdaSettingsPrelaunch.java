package mc.ardacraft.ardasettings;

import mc.ardacraft.ardasettings.client.PreLaunchCheck;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class ArdaSettingsPrelaunch  implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch(){
        PreLaunchCheck.checkAllocatedRam();
    }
}
