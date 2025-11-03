package mc.ardacraft.ardasettings.client;

import mc.ardacraft.ardasettings.ArdaSettings;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;

import javax.swing.*;
import java.util.List;

public class PreLaunchCheck {
    public record Specs (int coreCount, long RamMB, long VRamMB) {}

    private static void popUpScreen(String message){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored){}

        JOptionPane.showMessageDialog(null, message, "Performance Warning!", JOptionPane.WARNING_MESSAGE);
    }

    public static Specs checkSpecs(){
        // Currently unused but useful for later
        SystemInfo si = new SystemInfo();
        GlobalMemory memory = si.getHardware().getMemory();
        CentralProcessor cpu = si.getHardware().getProcessor();
        List<GraphicsCard> gpu = si.getHardware().getGraphicsCards();

        long totalRamMB = memory.getTotal() / (1024 * 1024);
        int coreCount = cpu.getPhysicalProcessorCount();
        long totalVRamMB = gpu.isEmpty() ? 0 : gpu.get(0).getVRam() / (1024 * 1024);

        return new Specs(coreCount, totalRamMB, totalVRamMB);
    }

    public static void checkAllocatedRam(){
        Specs minSpecs = new Specs(4, 8888, 4096);
        long allocatedMB = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        if (allocatedMB < minSpecs.RamMB){
            ArdaSettings.LOGGER.info("RAM allocated is not sufficient to run Ardacraft.");
//            popUpScreen("Your Minecraft instance has less then 8GB of memory allocated. ArdaC   raft needs at least 8GB allocated to function. \nPlease allocate at least 8GB of memory. \nIf you are unsure on how to do this, please check the instructions at: https://www.ardacraft.me/join");
//            System.exit(0);
            PanelDialog.showAllocatedRamDialog();

        }
    }
}
