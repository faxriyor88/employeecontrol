package com.example.employeecontrol.controller;

import com.example.employeecontrol.dto.LoginDTO;
import com.example.employeecontrol.jwt.JwtProwider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import java.io.*;

import java.util.UUID;


@RestController
@RequestMapping("/api")

public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProwider jwtProwider;

    // Boshqaruvchini sistemaga kirishi
    @PostMapping("/login")
    public ResponseEntity<?> loginToSystem(/*HttpServletRequest request*/@RequestBody LoginDTO loginDTO) {
        try {
//            Gson gson = new Gson();
//            Part login = request.getPart("login");
//            LoginDTO loginDTO = gson.fromJson(new InputStreamReader(login.getInputStream()), LoginDTO.class);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            String token = jwtProwider.generateToken(loginDTO.getUsername());
            System.out.println(token);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bunday boshqaruvchi topilmadi");
        }

    }

    private static byte[] getByteArray(InputStream is) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        BufferedOutputStream os = new BufferedOutputStream(b);
        while (true) {
            int i = is.read();
            if (i == -1) break;
            os.write(i);
        }
        os.flush();
        os.close();
        return b.toByteArray();
    }

    public void saver(Part filePart) throws IOException {
        String image = convertStreamToString(filePart.getInputStream());
        String[] strings = image.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String savefileimage = UUID.randomUUID().toString() + "." + extension;
        File file = new File("imagelocation/" + savefileimage);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

