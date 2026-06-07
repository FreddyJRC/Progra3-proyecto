/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.concurrent.*;
/**
 *
 * @author Estudio
 */
public class RequestsProcessor {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final Semaphore semaphore = new Semaphore(5);
    private static final RequestsProcessor instance = new RequestsProcessor();
    
    private RequestsProcessor() {}
    
    public static RequestsProcessor getInstance() { 
        return instance; 
    }
    
    public Future<?> submit(Runnable task) {
        return executor.submit(() -> {
            try {
                semaphore.acquire();
                Thread.sleep(800);
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        });
    }
    
    public void shutdown() {
        executor.shutdown();
    }
}
