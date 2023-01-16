import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            try {
                System.out.println(calc(str));
            } catch (RuntimeException e) {
                System.out.println("Error");
            }
        }
    }

    public static String calc(String input) {
        String[] param;
        char oper='?';

        if (input.contains("+")) {
            param = input.split("\\+");
            oper = '+';
        }

        else if (input.contains("-")) {
            param = input.split("-");
            oper = '-';
        }

        else if (input.contains("*")) {
            param = input.split("\\*");
            oper = '*';
        }

        else if (input.contains("/")) {
            param = input.split("/");
            oper = '/';
        }
        else {
            //Исключение о том что выражение не нужного формата
            throw new RuntimeException();
        }

        if (param.length>2) throw new RuntimeException();

        String a = param[0].trim();
        String b = param[1].trim();

        int first = -1, last = -1;

        boolean isIntQ; // являются ли оба числа арабскими цифрами

        if (isInt(a)&isInt(b)) {
            first = Integer.parseInt(param[0].trim());
            last = Integer.parseInt(param[1].trim());
            isIntQ = true;
        } else if (isArab(a)&isArab(b)) {
            first = Arab.valueOf(a).ordinal()+1;
            last = Arab.valueOf(b).ordinal()+1;
            isIntQ = false;
        } else {
            //Исключение что символы не цифры или не однородные цифры
            throw new RuntimeException();
        }

        if (first<1|last<1|first>10|last>10) throw new RuntimeException();


        int result;

        switch (oper) {
            case ('+'):
                result = first + last;
                break;
            case ('-'):
                result = first - last;
                break;
            case ('*'):
                result = first * last;
                break;
            case ('/'):
                result = (int) Math.floor(first / last);
                break;
            default:
                throw new RuntimeException();
        }

        if(isIntQ) return Integer.toString(result);
        else {
            if(result<1) {
                //Исключение, греческие цифры меньше 1
                throw new RuntimeException();
            } else {
                return intToArab(result);
            }
        }
    }

    public static String intToArab(int number) {
        return "I".repeat(number)
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C");
    }

    public static boolean isInt(String a) {
        try {
            Integer.parseInt(a);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isArab(String a) {
        try {
            Arab.valueOf(a);
            return true;
        } catch(IllegalArgumentException ex){
            return false;
        }
    }
}

enum Arab {
    I, II, III, IV, V, VI, VII, VIII, IX, X
}