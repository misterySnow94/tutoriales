package Wilson.ProblemWilson.store;

import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService {
    @Value("${staticresourceloader.fileLocation.path}")
    private String uploadLocation;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadLocation));
        } catch (IOException e) {
            throw new RuntimeException("no se pudo almacenar la informacion", e);
        }
    }

    public String store(MultipartFile file, String id) {
        // crea un archivo correspondiente en caso de que no exista el archivo
        crearArchivo(id);
        try {
            String filename = StringUtils.cleanPath(id + "/" + file.getOriginalFilename());
            if (file.isEmpty()) {
                throw new RuntimeException(
                        "Fallo, al parecer el archivo esta vacio" + file.getOriginalFilename());
            }
            // chekeo de seguridad
            if (filename.contains("..")) {
                throw new RuntimeException(
                        "Fallo, no se pueden cargar archivos que puedan salirse de la carpeta de guardado"
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadLocation).resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fallo, al guardar alguno de los archivos ", e);
        }
        System.out.println("guardado");
        return id+ "/" + file.getOriginalFilename();
    }

    public void crearArchivo(String documento) {
        try {
            File directorio = new File(uploadLocation + "/" + documento);
            directorio.mkdirs();
        } catch (Exception e) {
            throw new RuntimeException("Fallo, al crear el archivo: " + documento, e);
        }
    }

    public void iniciarArchivo(String documento) {
        try {
            File directorio = new File(documento);
            directorio.mkdirs();
        } catch (Exception e) {
            throw new RuntimeException("Fallo, al crear el archivo: " + documento, e);
        }
    }

    public boolean eliminarArchivos(String archivo) {
        File fileDel = new File(uploadLocation + "/" + archivo);
        if (fileDel.isDirectory()) {
            if (fileDel.list().length == 0)
                fileDel.delete();
            else {
                for (String temp : fileDel.list()) {
                    eliminarArchivos(archivo+"/"+temp);
                }
                if (fileDel.list().length == 0)
                    fileDel.delete();
            }
        } else {
            fileDel.delete();
            return true;
        }
        return false;
    }
}