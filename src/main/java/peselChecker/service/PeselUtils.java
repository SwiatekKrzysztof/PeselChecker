package peselChecker.service;

import peselChecker.model.Pesel;

import java.time.Month;
import java.util.*;

public class PeselUtils {
    public List<Month> mostPeopleBirthMonth(List<Pesel> pesels){
        int[] monthCount = new int[13];
        for(Pesel pesel: pesels){
            monthCount[pesel.getBirthDate().getMonthValue()]++;
        }
        int maxValue = Arrays.stream(monthCount).max().getAsInt();
        List<Month> months = new ArrayList<>();
        for (int i = 0; i < monthCount.length; i++) {
            if(monthCount[i]==maxValue){
                months.add(Month.of(i));
            }
        }
        return months;
    }

    public int peopleBornInMonth(List<Pesel> pesels, Month month){
        int monthCount =  0;
        for(Pesel pesel: pesels){
            if(pesel.getBirthDate().getMonth()==month){
                monthCount++;
            }
        }
        return monthCount;
    }

    public int sexCouner(List<Pesel> pesels, char sex){
        int sexCount = 0;
        for(Pesel pesel: pesels){
            if(pesel.getSex()==sex){
                sexCount++;
            }
        }
        return sexCount;
    }
    public int[] mostPeopleBirthYear(List<Pesel> pesels){
        LinkedHashMap<Integer, Integer> Years = new LinkedHashMap<>();

        for(Pesel pesel: pesels){
            Integer peselYear = pesel.getBirthDate().getYear();
            if(Years.containsKey(peselYear)){
                Years.put(peselYear,Years.get(peselYear)+1);
            }else{
                Years.put(peselYear,1);
            }
        }
        int maxValue = Years.values().stream().max(Integer::compare).get();
        Set<Integer> keys = getKeysByValue(Years,maxValue);
        return keys.stream().mapToInt(Number::intValue).toArray();
    }
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public List<Pesel> incorrectPesels(List<Pesel> pesels){
        List<Pesel> incorrectPesels = new ArrayList<>();
        for(Pesel pesel: pesels){
            boolean correct = isCheckNumberCorrect(pesel);
            if(!correct){
                System.out.println(checkNumber(pesel.getPeselNumber()));
            }
        }
        return incorrectPesels;
    }
    public boolean isCheckNumberCorrect(Pesel pesel){
        String peselString = String.valueOf(pesel.getPeselNumber());
        int eleventh = parseChar(peselString.charAt(10));

        return eleventh==checkNumber(pesel.getPeselNumber());
    }
    public int checkNumber(long peselNumber){
        String peselString = String.valueOf(peselNumber);
        int[] numbers = new int[11];
        numbers[0] = parseChar(peselString.charAt(0)) * 9;
        numbers[1] = parseChar(peselString.charAt(1)) * 7;
        numbers[2] = parseChar(peselString.charAt(2)) * 3;
        numbers[3] = parseChar(peselString.charAt(3));
        numbers[4] = parseChar(peselString.charAt(4)) * 9;
        numbers[5] = parseChar(peselString.charAt(5)) * 7;
        numbers[6] = parseChar(peselString.charAt(6)) * 3;
        numbers[7] = parseChar(peselString.charAt(7));
        numbers[8] = parseChar(peselString.charAt(8)) * 9;
        numbers[9] = parseChar(peselString.charAt(9)) * 7;
        int checkSum = 0;
        for (int number : numbers) {
            checkSum = checkSum + number;
        }
        return checkSum%10;
    }
    private int parseChar(char a){
        return Integer.parseInt(String.valueOf(a));
    }
}
