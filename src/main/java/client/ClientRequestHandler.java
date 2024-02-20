/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import domain.BMIResult;
import domain.Person;
import middleware.FileConverter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientRequestHandler {
    private static final int SERVER_PORT = 12345;

    public BMIResult sendRequestAndGetResponse(Person person) {
        try (Socket socket = new Socket("localhost", SERVER_PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            byte[] requestData = FileConverter.objectToBytes(person);

            outputStream.write(ByteBuffer.allocate(4).putInt(requestData.length).array());

            outputStream.write(requestData);
            outputStream.flush();

            byte[] lengthBytes = new byte[4];
            inputStream.read(lengthBytes);
            int length = ByteBuffer.wrap(lengthBytes).getInt();

            byte[] responseData = new byte[length];
            inputStream.read(responseData);

            BMIResult bmiResult = (BMIResult) FileConverter.bytesToObject(responseData);

            return bmiResult;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

