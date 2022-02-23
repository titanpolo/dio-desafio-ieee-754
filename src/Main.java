
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        //obtendo string
        String x = Double.toString(leitor.nextDouble());

        //convertendo para double
        double pontoFlutuante = Double.parseDouble(x);

        //verificando parte inteira zero
        boolean inteiroZero = pontoFlutuante < 1 && pontoFlutuante > -1;

        String substring1;

        String sinalMant = "";

        //removendo o sinal de menos caso exista
        if (x.contains("-")) {
            sinalMant=sinalMant.concat("-");
            substring1 = x.substring(1);
        } else {
            sinalMant=sinalMant.concat("+");
            substring1 = x;
        }

        //splitting
        String[] separado = substring1.split(Pattern.quote("."));

        //obtendo expoente
        int expoente = 1;

        StringBuilder mantissa = new StringBuilder();

        int tamanho1 = separado[0].length();
        int tamanho2 = 0;
        if(substring1.contains(".")) tamanho2 = separado[1].length();

        String sinalExp = "";

        if(inteiroZero) {
            sinalExp=sinalExp.concat("-");
            for (int i = 0; i < tamanho2; i++) {
                if (separado[1].charAt(i) == '0') expoente++;
                else {
                    for (int j=i; j < tamanho2; j++) {
                        if(mantissa.length() == 6) break;
                        mantissa.append(separado[1].charAt(j));
                    }
                    if(mantissa.length()<5){
                        for (int j = mantissa.length(); j < 6; j++){
                            mantissa.append("0");
                        }
                    }
                    break;
                }
            }
        } else {
            sinalExp=sinalExp.concat("+");
            expoente=separado[0].length()-1;
            if(tamanho1>6) mantissa.append(separado[0].substring(0,6));
            else {
                mantissa.append(separado[0]);
                int faltante = 6 - tamanho1;
                for (int i=0; i < faltante; i++){
                    if(separado[1].length()<faltante) mantissa.append("0");
                    else mantissa.append(separado[1].charAt(i));
                }
            }

        }


        String expStr = "";

        if(expoente<10) expStr=expStr.concat("0");
        expStr=expStr.concat(Integer.toString(expoente));

        String mantissaStr = mantissa.toString();

        if(!(separado[1].length()+1==expoente)){
            float mantissaFlt = Float.parseFloat(mantissaStr);
            mantissaFlt = mantissaFlt/10;
            mantissaFlt = Math.round(mantissaFlt);

            mantissaStr = Float.toString(mantissaFlt);
            mantissaStr = mantissaStr.substring(0,mantissaStr.length()-2);
        } else {
            mantissaStr = "00000";
            sinalExp="+";
            expStr="00";
        }

        System.out.println(sinalMant+mantissaStr.charAt(0)+"."+mantissaStr.substring(1)+"E"+sinalExp+expStr);

    }
}