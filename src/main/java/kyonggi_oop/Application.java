package kyonggi_oop;

import kyonggi_oop.controller.LibraryController;

public class Application {
    public static void main(String[] args) {
        LibraryController libraryController = new LibraryController();
        libraryController.run();
    }
}