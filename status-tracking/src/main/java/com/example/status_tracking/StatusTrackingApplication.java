package com.example.status_tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class StatusTrackingApplication {

    private static Process fastapiProcess;

    public static void main(String[] args) {
        startFastAPI(); // ✅ Start FastAPI first
        SpringApplication.run(StatusTrackingApplication.class, args);

        // ✅ Shutdown FastAPI when Spring Boot stops
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (fastapiProcess != null) {
                System.out.println("🛑 Stopping FastAPI...");
                fastapiProcess.destroy();
            }
        }));
    }

    private static void startFastAPI() {
        try {
            File fastapiDir = new File("C:\\Users\\DELL\\Downloads\\status-tracking\\defect-detection-api"); // ✅ Update path
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "uvicorn main:app --host 0.0.0.0 --port 8000 --reload");
            builder.directory(fastapiDir);
            builder.redirectErrorStream(true); // ✅ Capture errors

            fastapiProcess = builder.start();
            System.out.println("✅ FastAPI process started!");

            // ✅ Capture FastAPI output for debugging
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(fastapiProcess.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("FastAPI: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            System.err.println("❌ Failed to start FastAPI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
