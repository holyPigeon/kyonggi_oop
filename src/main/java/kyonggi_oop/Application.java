package kyonggi_oop;

import kyonggi_oop.config.AppConfig;
import kyonggi_oop.config.Config;
import kyonggi_oop.controller.LibraryController;

public class Application {
    public static void main(String[] args) {
        Config appConfig = AppConfig.getInstance();
        LibraryController libraryController = appConfig.libraryController();
        libraryController.run();
    }
}