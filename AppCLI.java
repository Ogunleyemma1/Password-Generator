package de.buw.se;

import java.util.HashSet;
import java.util.Set;

public class AppCLI{

    private static long collectEntropy() {
        long entropy = 0;
        entropy ^= System.nanoTime();
        return entropy;
    }

    private static int generateRandomNumber(int max) {
        long entropy = collectEntropy();
        entropy ^= (entropy << 13);
        entropy ^= (entropy >> 7);
        entropy ^= (entropy << 17);
        return (int) Math.abs(entropy % max);
    }

    public static String generatePassword(int length, boolean includeLowerCase, boolean includeUpperCase, boolean includeNumbers, boolean includeSpecialCharacters) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+[]{}|;:'><>?";
        StringBuilder characters = new StringBuilder();
        if (includeLowerCase) characters.append(lowerCase);
        if (includeUpperCase) characters.append(upperCase);
        if (includeNumbers) characters.append(numbers);
        if (includeSpecialCharacters) characters.append(specialChars);
        if (characters.length() == 0) {
            return "";
        }
        Set<String> usedCharacterTypes = new HashSet<>();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = generateRandomNumber(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
            if (lowerCase.indexOf(randomChar) >= 0) {
                usedCharacterTypes.add("lowercase");
            } else if (upperCase.indexOf(randomChar) >= 0) {
                usedCharacterTypes.add("uppercase");
            } else if (numbers.indexOf(randomChar) >= 0) {
                usedCharacterTypes.add("numbers");
            } else if (specialChars.indexOf(randomChar) >= 0) {
                usedCharacterTypes.add("special");
            }
        }
        if ((includeLowerCase && !usedCharacterTypes.contains("lowercase")) ||
            (includeUpperCase && !usedCharacterTypes.contains("uppercase")) ||
            (includeNumbers && !usedCharacterTypes.contains("numbers")) ||
            (includeSpecialCharacters && !usedCharacterTypes.contains("special"))) {
            return generatePassword(length, includeLowerCase, includeUpperCase, includeNumbers, includeSpecialCharacters);
        }
        return password.toString();
    }
}
