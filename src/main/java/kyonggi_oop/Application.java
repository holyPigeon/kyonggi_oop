package kyonggi_oop;

import kyonggi_oop.config.AppConfig;
import kyonggi_oop.config.Config;
import kyonggi_oop.controller.KioskController;

public class Application {
    public static void main(String[] args) {
        Config appConfig = AppConfig.getInstance();
        KioskController kioskController = appConfig.kioskController();
        kioskController.run();
    }
}