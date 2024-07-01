package Lesson8;

public class Task1 {

    // Основной метод для тестирования
    public static void main(String[] args) {
        String[][] correctArray = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"}
        };

        String[][] incorrectArray = {
            {"1", "2", "3"},
            {"4", "5", "6", "7"},
            {"8", "9", "10"},
            {"11", "12"}
        };

        String[][] arrayWithInvalidData = {
            {"1", "2", "3", "f"},
            {"S", "6", "7", "8"},
            {"9", "#", "11", "12"},
            {"13", "14", "15", "16"}
        };

        testArray(1, correctArray);
        testArray(2, incorrectArray);
        testArray(3, arrayWithInvalidData);
    }

    // Определяем исключения
    public static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    public static class MyArrayDataException extends Exception {
        public MyArrayDataException(String message) {
            super(message);
        }
    }

    // Метод для проверки размера массива
    public static void validateArraySize(String[][] array) throws MyArraySizeException {
        if (array.length != 4) {
            throw new MyArraySizeException("Длина массива должна быть 4.");
        }
        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArraySizeException("Строк должно быть 4.");
            }
        }
    }

    // Метод для суммирования элементов массива,
    // с учетом обработки исключений MyArrayDataException
    public static int sumArray(String[][] array) throws MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int num = Integer.parseInt(array[i][j]);
                    sum += num;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в строке " +
                        (i + 1) + ", столбец " + (j + 1));
                }
            }
        }
        return sum;
    }

    // Метод для тестирования массива
    private static void testArray(int testNumber, String[][] array) {
        try {
            validateArraySize(array);
            System.out.println("Размер массива " + testNumber + " корректен.");

            int sum = sumArray(array);
            System.out.println("Сумма элементов массива " + testNumber + ": " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка (исключение) массива " + testNumber + ": " + e.getMessage());
        }
    }
}