package rxtd.rainmeter.resources;

import rxtd.rainmeter.SkinUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceFactory {
    public Resource fromStream(StreamProvider streamProvider, String path, Path type, boolean isShared) {
        return new FileResource(path, null, type, isShared, stream -> streamProvider.getStream().transferTo(stream));
    }

    public Resource jarStream(String internalPath, String path, Path type, boolean isShared) {
        return this.fromStream(() -> this.getClass().getResourceAsStream(internalPath), path, type, isShared);
    }

    public Resource jarFont(String internalPath, String fontFaceName) {
        Path path = Paths.get(internalPath);
        return new FileResource("Fonts/" + path.getFileName(), fontFaceName, Paths.get("font"), true, stream -> this.getClass().getResourceAsStream(internalPath).transferTo(stream));
    }

    public Resource jarScript(String internalPath, String scriptName, boolean isShared) {
        return this.jarScript(internalPath, scriptName, isShared, StandardCharsets.UTF_8);
    }

    public Resource jarScript(String internalPath, String scriptName, boolean isShared, Charset fileCharset) {
        String fileName = "Scripts/" + scriptName + (scriptName.endsWith(".lua") ? "" : ".lua");
        return new FileResource(fileName, null, Paths.get("script"), isShared, stream -> {
            InputStream is = this.getClass().getResourceAsStream(internalPath);
            Reader reader = new InputStreamReader(is, fileCharset);
            Writer writer = new OutputStreamWriter(stream, SkinUtils.getStandardCharset());

            reader.transferTo(writer);
            writer.flush();
            is.close();
        });
    }

    public Resource fromFile(Path file, String path, Path type, boolean isShared) {
        return this.fromStream(() -> {
            try {
                return Files.newInputStream(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, path, type, isShared);
    }
}
