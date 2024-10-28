import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

/**
 * На вход программы поступает имя файла, директория и текст на сохранение.
 * Вам нужно провести валидацию входных данных.[]
 * Провести сохранение файла, вернуть размер сохраненного файла и время записи.[]
 * При необходимости перезаписать сохраненный файл.[]
 * По запросу пользователя (по имени файла и директории) произвести поиск файла и вернуть пользователю содержимое.[]
 */


public class FileManager {

    public static String saveFile(String path, String text, String fileName) {
        try {
            if (path != null && text != null) {
                File file = new File(path, fileName);
                File fileWithPass = new File(path);
                if (fileWithPass.isFile()) {
                    OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(fileWithPass));
                    streamWriter.write(text);
                    streamWriter.close();
                    return new String("Время записи: " + new Date().toString() + "\nРазмер файла: " + fileWithPass.length());
                } else{
                    file.createNewFile();
                    OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(file));
                    streamWriter.write(text);
                    streamWriter.close();
                    return new String("Время записи: " + new Date().toString() + "\nРазмер файла: " + file.length());
                }
            }
        } catch (IOException e) {
            System.out.println("Недоступные данные пути " + path);
            return null;
        }

        return null;
    }
    public static boolean findFile(String fileName, String directory) {
        if (directory != null && fileName != null) {
            File files = new File(directory);
            if (files.isDirectory()) {
                for (File file : files.listFiles()) {
                    if (file.getName().equals(fileName)) {
                        try {
                            System.out.println(Files.readAllLines(file.toPath()));
                        } catch (IOException e) {
                            System.out.println("Oшибочный путь");
                            throw new RuntimeException(e);
                        }
                        return true;
                    }
                }
            }
        }
        System.out.println("В данной директории нет файла с таким названием " + fileName);
        return false;
    }


}
