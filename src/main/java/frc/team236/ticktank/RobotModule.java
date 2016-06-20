package frc.team236.ticktank;

import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.IterativeModule;

public class RobotModule extends IterativeModule {

    public static Logger logger;

    @Override
    public String getModuleName() {
        return "TickTank";
    }

    @Override
    public String getModuleVersion() {
        return "1.0.1";
    }

    @Override
    public void robotInit() {
        logger = new Logger("TickTank", Logger.ATTR_DEFAULT);
    }
}
