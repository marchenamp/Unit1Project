/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domain.BMIResult;
import domain.Person;
import middleware.BMICalculator;
import middleware.FileConverter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class RequestHandler extends Thread {
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             OutputStream outputStream = socket.getOutputStream();
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            byte[] lengthBytes = new byte[4];
            bufferedInputStream.read(lengthBytes);
            int length = ByteBuffer.wrap(lengthBytes).getInt();

            byte[] requestData = new byte[length];
            bufferedInputStream.read(requestData);

            Person person = (Person) FileConverter.bytesToObject(requestData);

            BMIResult bmiResult = BMICalculator.calculateBMI(person);

            byte[] responseData = FileConverter.objectToBytes(bmiResult);

            bufferedOutputStream.write(ByteBuffer.allocate(4).putInt(responseData.length).array());

            bufferedOutputStream.write(responseData);
            bufferedOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

