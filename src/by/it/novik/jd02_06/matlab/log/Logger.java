package by.it.novik.jd02_06.matlab.log;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Kate Novik.
 */
public class Logger implements Runnable{
    //Поле объекта Logger Singleton
    private static Logger instance;
    //Поле ошибки для записи в файл
    private static String exception = null;
    //Флаг окончания потока
   // public static boolean isEnd = false;

    private Logger() {}

    /**
     * Получение объекта Logger
     * @return объект Logger
     */
    public static Logger getInstance () {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Установить значение поля ошибка
     * @param exception Значение ошибки в виде строки
     */
    public static void setException(String exception) {
        Logger.exception = exception;
    }

    public static String getException() {
        return exception;
    }

    /**
     * Запись ошибок в файл
     */
    public static void writeLogInFile () {

            BufferedWriter bf = null;
            try {
                do {
                    if (exception != null) {
                        bf = CreateWriter.createWriter();
                        String line = DateTime.getDateTime().concat(" ").concat(exception).concat("\n");
                        bf.append(line);
                        bf.flush();
                        exception = null;
                    }
                }
                while (true);

            } catch (IOException e) {
                System.out.println("Ошибка записи!" + e);
            } finally {
                if (bf != null) {
                    try {
                        bf.close();
                    } catch (IOException e) {
                        System.out.println("Ошибка закрытия!" + e);
                    }
                }
            }


    }

    @Override
    public void run() {

            writeLogInFile();

    }
}
