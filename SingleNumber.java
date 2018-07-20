import org.ho.yaml.YamlEncoder;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by alexm on 18.07.2018.
 */


public class SingleNumber {
    public static int[] generateRandomNumbersArray(int arrSize) {
        if (arrSize % 2 == 0) {
            throw new IllegalArgumentException("Array size should not be even!!!");
        }
        int[] numbers = new int[arrSize];


        Random random = new Random();
        for (int i = 0; i < numbers.length; i = i + 2) {
            int randomValue = random.nextInt(10);
            numbers[i] = randomValue;
            if (i < numbers.length - 1) {
                numbers[i + 1] = randomValue;
            }
        }

        return numbers;
    }

    public static int[] shuffleArray(int[] array) {
        Random random = new Random();
        int[] shuffledArray = array.clone();

        for (int i = 0; i < shuffledArray.length; i++) {
            int randomIndex = random.nextInt(i + 1);
            //Simple swap
            int element = shuffledArray[randomIndex];
            shuffledArray[randomIndex] = shuffledArray[i];
            shuffledArray[i] = element;
        }
        return shuffledArray;
    }

    public static int findSingleNumber(int[] array) {
        Arrays.sort(array);

        for (int i = 0; i < array.length; i = i + 2) {
            if (i < array.length - 1 && array[i] == array[i + 1]) {
                continue;
            } else {
                return array[i];
            }
        }
        throw new IllegalArgumentException("Method should supply array with not even element!!!");
    }

    public static void yamlConvert(){
        Integer[] array = new Integer[11];

        File file = new File("file.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        YamlEncoder enc = new YamlEncoder(out);
        enc.writeObject(array);
        enc.close();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] numbers = generateRandomNumbersArray(11);
        numbers = shuffleArray(numbers);

        int singleNumber = findSingleNumber(numbers);
        System.out.println(singleNumber);

        yamlConvert();
    }
}
