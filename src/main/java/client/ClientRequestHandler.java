/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author march
 */
import domain.BMIResult;
import domain.Person;
import java.io.*;
import java.net.Socket;
import middleware.FileConverter;

public class ClientRequestHandler {
    private static final int SERVER_PORT = 12345;

    public BMIResult sendRequestAndGetResponse(Person person) {
        try (Socket socket = new Socket("localhost", SERVER_PORT);
             OutputStream outputStream = socket.getOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
             InputStream inputStream = socket.getInputStream();
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

            // Convert PersonInfo object to bytes
            byte[] requestData = FileConverter.objectToBytes(person);

            // Send request to server
            outputStream.write(requestData);

            // Receive response from server
            byte[] responseData = new byte[4096];
            int bytesRead = inputStream.read(responseData);

            // Convert response bytes to BMIResult object
            BMIResult bmiResult = (BMIResult) FileConverter.bytesToObject(responseData);

            return bmiResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

