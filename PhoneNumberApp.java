package com.romaintestard.lab;

import java.io.*;

public class PhoneNumberApp {

    public static void main(String[] args){
        // This will read a text file and will retriebe phone number

        String filename = "C:\\Users\\Romain\\Desktop\\Dev web\\JavaPrograms\\files\\files\\phoneNumber.txt";
        File file = new File(filename);
        String[] phoneNums = new String[8];
        String phoneNum = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (int i = 0; i<phoneNums.length; i++) {
                phoneNums[i] = br.readLine();
            }
            br.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found");
            } catch (IOException e) {
                System.out.println("Error: could not read file");
            }



        // Valid phone number: 10 digits long
        // 10 digits long
        // Area code cannot start with 1,2,3,4,5,6,7,8,9
        // There can be a 112 in the phone
        for (int i = 0; i<phoneNums.length; i++){
            phoneNum = phoneNums[i];
            try {
                if (phoneNum.length() != 10) {
                    throw new TenDigitsException(phoneNum);
                }
                if(!phoneNum.substring(0, 1).equals("0")) {
                    throw new AreaCodeException(phoneNum);
                }
                if(phoneNum.contains("112")) {
                    throw new EmergencyException(phoneNum);
                }
            } catch (TenDigitsException e) {
                System.out.println("Error: Phone number is not ten digits");
                System.out.println(e.toString());
            } catch (AreaCodeException e) {
                System.out.println("Error: Phone number must start with 0");
                System.out.println(e.toString());
            } catch (EmergencyException e) {
                System.out.println("Error: Invalid 112 sequence found");
                System.out.println(e.toString());
            }
        }

    }
}

class TenDigitsException extends Exception {
    String num;
    TenDigitsException(String num) {
        this.num = num;
    }
    public String toString() {
        return ("TenDigitsException: " + num);
    }
}

class AreaCodeException extends Exception {
    String num;
    AreaCodeException(String num) {
        this.num = num;
    }
    public String toString() {
        return ("AreaCodeException: " + num);
    }
}

class EmergencyException extends Exception {
    String num;
    EmergencyException(String num) {
        this.num = num;
    }
    public String toString() {
        return ("EmergencyException: " + num);
    }
}