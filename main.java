import java.util.Scanner;

public class Main
{
  private static int a = 0, b = 0; private static boolean flag = false; private static String[] split;
	private static String[] romans = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        split = input.split(" ");
        if (split.length != 3) {
            throw new IllegalArgumentException("Некорректный ввод. Пример ввода: a + b");
	    }
	    if (!isOpOkay(split[1])){
	        throw new IllegalArgumentException("Некорректный ввод. Допустимые операции: + - * /");
	    }
	    if (isRoman(split[0]) && isRoman(split[2])) {
	        a = toArabic(split[0]);
	        b = toArabic(split[2]);
	        flag = true;
	    }
	    else {
	        try {
	           a = Integer.parseInt(split[0]);
               b = Integer.parseInt(split[2]);
               if (a < 1 || a > 10 || b < 1 || b > 10) {
	                throw new IllegalArgumentException("Некорректный ввод. Числа должны быть от 1 до 10(от I до X) включительно.");
	           }
	        } catch (NumberFormatException e) {
	             throw new IllegalArgumentException("Некорректный ввод. Числа должны соответствовать только римской или только арабской нотации.");
	        }
	    }
	    if (b > a && flag && split[1].equals("-"))
	        throw new IllegalArgumentException("Результат меньше чем 1.");
        if (!flag){
            System.out.println(doJob(a,b));
        } else {
            System.out.println(toRoman(doJob(a,b)));
        }
	}
    private static boolean isRoman(String input) {
        return input.equals("I") || input.equals("II") || input.equals("III") || input.equals("IV") || input.equals("V") || input.equals("VI") || input.equals("VII") || input.equals("VIII") || input.equals("IX") || input.equals("X");
    }
    private static boolean isOpOkay(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }
    private static int doJob(int a, int b) {
        if (split[1].equals("+"))
            return a+b;
        else if (split[1].equals("-"))
            return a-b;
        else if (split[1].equals("*"))
            return a*b;
        else if (split[1].equals("/"))
            return a/b;
        else return 0;
    }
    private static int toArabic(String num) {
        int i = 0;
        for (i = 0; i < 10; i++) {
            if (num.equals(romans[i])) break; 
        }
        return i + 1;
    }
    public static String toRoman(int number) {
        String result = "";
        int i = 19;
        int d = number % 10;
        if (number <= 10) {
            result = romans[number - 1];  
        }
        else {
            if (d == 0) {
                result = romans[(number / 10) + 8];
            } else {
                result = romans[(number / 10) + 8] + romans[d - 1];   
            }
        }
        return result;
    }
}
