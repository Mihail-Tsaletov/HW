import java.io.*;
import java.nio.file.Files;
import java.util.Date;

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
                if (!fileWithPass.isDirectory()) {
                    return getString(path, text, fileWithPass);
                } else {
                    file.createNewFile();
                    return getString(path, text, file);
                }
            }
        } catch (IOException e) {
            throw new ErrorPathFile("По данному пути не удалось сохранить файл: " + path);
        }
        System.out.println("_________________________\nСохранить не удалось\nПуть: " + path + "\nТекст: " + text + "\nИмя файла: " + fileName + "\n_________________________");
        return null;
    }

    private static String getString(String path, String text, File file) {
        try (OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(file))) {
            streamWriter.write(text);
            return new String("Время записи: " + new Date().toString() + "\nРазмер файла: " + file.length());
        } catch (Exception e) {
            throw new ErrorPathFile(path);
        }
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
