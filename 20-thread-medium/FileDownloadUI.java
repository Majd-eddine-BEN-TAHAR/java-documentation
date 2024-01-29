/**
 * A Java threading example simulating a file download scenario.
 * The Downloader class extends Thread and simulates downloading a file in the background.
 * The main thread represents a user interface that starts the download and periodically checks its progress.
 *
 * Key Methods Used:
 * - start(): Starts the thread, causing the JVM to call run(), in our case => Starts the downloader thread.
 * - run():  This is where the thread's task is defined, in our case => Defines the downloader's task of downloading a file.
 * - sleep(): Pauses the thread for a specified time, in our case => Simulates the time taken to download the file.
 * - join(): Makes the calling thread wait until the specified thread finishes, not used in our example.
 * - isAlive(): : Checks if the thread is still running, in our case => Checks if the downloader thread is still running.
 *
 * Workflow of the program:
 * 1. UI starts the file download by starting the downloader thread.
 * 2. UI continues its operations, periodically checking the download status.
 * 3. Downloader completes the download after a simulated delay.
 * 4. UI is notified of download completion and informs the user.
 */

 class Downloader extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Downloading file...");
            Thread.sleep(5000); // Simulate time taken to download
            System.out.println("Download Complete!");
        } catch (InterruptedException e) {
            System.out.println("Download interrupted.");
        }
    }
}

public class FileDownloadUI {

    public static void main(String[] args) {
        Downloader downloader = new Downloader();
        downloader.start(); // Start the downloader thread

        while (downloader.isAlive()) {
            System.out.println("Download in progress...");
            try {
                Thread.sleep(1000); // UI thread checks download status every second
            } catch (InterruptedException e) {
                System.out.println("UI interrupted.");
            }
        }

        System.out.println("File download finished. User can now access the file.");
    }
}