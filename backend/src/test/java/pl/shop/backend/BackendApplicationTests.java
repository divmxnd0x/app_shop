package pl.shop.backend;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void gridFsImageTest() {
        GridFsResource image = productService.getImage(new ObjectId("65c68d3c8414a39a373e17f9"));
        Assertions.assertTrue(image.exists());
        try (var is = image.getInputStream()) {
            try (var tomatoStream = BackendApplicationTests.class.getResourceAsStream("tomato.png")) {
                byte[] fsBytes = is.readAllBytes();
                Assertions.assertNotNull(tomatoStream);
                byte[] tomatoBytes = tomatoStream.readAllBytes();
                Assertions.assertArrayEquals(fsBytes, tomatoBytes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
