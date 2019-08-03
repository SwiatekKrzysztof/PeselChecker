package peselChecker.service;

import peselChecker.model.Pesel;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class PeselService {
    public List<Pesel> convertStringListToPeselList(List<String> list){
        List<Pesel> pesels = new ArrayList<>();
        for(String s : list){
            Pesel pesel = translatePesel(s);
            pesels.add(pesel);
            System.out.println(pesel);
        }
        return pesels;
    }

    public Pesel translatePesel(String peselString){
        Pesel pesel = new Pesel();

        pesel.setPeselNumber(Long.parseLong(peselString));
        pesel.setBirthDate(extractDate(peselString));
        pesel.setOrdinalNumber(extractOrdinalNumber(peselString));
        pesel.setSex(extractSex(pesel.getOrdinalNumber()));
        pesel.setCheckNumber(extractCheckNumber(peselString));

        return pesel;
    }
    private LocalDate extractDate(String peselString){
        int year;
        int month;
        int day;
        //Day
        int firstDayDigit = Integer.parseInt(String.valueOf(peselString.charAt(4)));
        int secondDayDigit = Integer.parseInt(String.valueOf(peselString.charAt(5)));
        if(firstDayDigit==0){
            day = secondDayDigit;
        }else{
            day = firstDayDigit*10+secondDayDigit;
        }
        //Month
        int firstMonthDigit = Integer.parseInt(String.valueOf(peselString.charAt(2)));
        int secondMonthDigit = Integer.parseInt(String.valueOf(peselString.charAt(3)));
        if(firstMonthDigit%2.0==0){
            month = secondMonthDigit;
        }else{
            month = 10 + secondMonthDigit;
        }

        //Year
        int[] century = getCentury(firstMonthDigit);
        int thirdYearDigit = Integer.parseInt(String.valueOf(peselString.charAt(0)));
        int forthYearDigit = Integer.parseInt(String.valueOf(peselString.charAt(1)));
        year = century[0]*1000+century[1]*100+thirdYearDigit*10+forthYearDigit;
        try {
            return LocalDate.of(year, month, day);
        }catch(DateTimeException e){
            int dayCorrected = Month.of(month).minLength();
            return LocalDate.of(year, month, dayCorrected);
        }
    }

    private int extractOrdinalNumber(String peselString){
        int first = parseChar(peselString.charAt(6));
        int second = parseChar(peselString.charAt(7));
        int third = parseChar(peselString.charAt(8));
        return 100*first+10*second+third;
    }

    private char extractSex(int ordinalNumber){
        char result = 'M';
        if(ordinalNumber%2==0){
            result = 'F';
        }
        return result;
    }
    private int extractCheckNumber(String peselString){
        return parseChar(peselString.charAt(10));
    }
    private int[] getCentury(int firstMonthDigit){
        int[] century = new int[2];
        if(firstMonthDigit%2!=0) {
            firstMonthDigit--;
        }
        switch (firstMonthDigit){
            case 0:
                century[0]=1;
                century[1]=9;
                break;
            case 2:
                century[0]=2;
                century[1]=0;
                break;
            case 4:
                century[0]=2;
                century[1]=1;
                break;
            case 6:
                century[0]=2;
                century[1]=2;
                break;
        }
        return century;
    }
    private int parseChar(char a){
        return Integer.parseInt(String.valueOf(a));
    }
}
