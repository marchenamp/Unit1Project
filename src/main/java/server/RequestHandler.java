/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author march
 */
import domain.BMIResult;
import domain.Person;
import java.io.*;
import java.net.Socket;
import middleware.BMICalculator;
import middleware.FileConverter;

public class RequestHandler extends Thread {
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
             OutputStream outputStream = socket.getOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            // Receive request data from client
            byte[] requestData = new byte[4096];
            int bytesRead = inputStream.read(requestData);

            // Convert request bytes to PersonInfo object
            Person person = (Person) FileConverter.bytesToObject(requestData);

            // Calculate BMI
            BMIResult bmiResult = BMICalculator.calculateBMI(person);

            // Convert BMIResult object to bytes
            byte[] responseData = FileConverter.objectToBytes(bmiResult);

            // Send response to client
            outputStream.write(responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}