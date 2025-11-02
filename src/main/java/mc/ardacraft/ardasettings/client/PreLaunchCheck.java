package mc.ardacraft.ardasettings.client;

import mc.ardacraft.ardasettings.ArdaSettings;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;

import javax.swing.*;
import java.util.List;

public class PreLaunchCheck {
    private static void popUpScreen(String message){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored){}

        JOptionPane.showMessageDialog(null, message);
    }

    public static void checkSpecs(){
        SystemInfo si = new SystemInfo();
        GlobalMemory memory = si.getHardware().getMemory();
        CentralProcessor cpu = si.getHardware().getProcessor();
        List<GraphicsCard> gpu = si.getHardware().getGraphicsCards();

        long totalRamMB = memory.getTotal() / (1024 * 1024);
        int coreCount = cpu.getPhysicalProcessorCount();
        long totalVRamMB = gpu.isEmpty() ? 0 : gpu.get(0).getVRam() / (1024 * 1024);


        if (totalRamMB < 65536) {
            ArdaSettings.LOGGER.info("Not enough ram");
            popUpScreen("Your computer does not have enough RAM to run Ardacraft properly.");
        }
    }
}
