package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) throws IOException {
        String serialized = car.serialize();
        Files.writeString(path, serialized, StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws IOException {
        String serialized = Files.readString(path);
        return Car.deserialize(serialized);
    }
}
// END
