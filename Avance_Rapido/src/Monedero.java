public class Monedero {

    public static boolean cambioMinimo(int[] valores, int[] cantidades, int cantidad, int[] cambio){
        int cantidadMonedasCadaTipo = 0;
        int dineroQueLlevo = 0;

        for(int i=0; i<valores.length; i++) {

            while(cantidades[i] > 0 && cantidad >= dineroQueLlevo){
                dineroQueLlevo = dineroQueLlevo + valores[i]; 
                cantidadMonedasCadaTipo++;
                cantidades[i] = cantidades[i]-1;
            }

            dineroQueLlevo = dineroQueLlevo - valores[i]; 
            cantidadMonedasCadaTipo = cantidadMonedasCadaTipo-1;

            cambio[i] = cantidadMonedasCadaTipo;
            cantidadMonedasCadaTipo=0;

        }

        if(dineroQueLlevo == cantidad) {
            for(int i=0; i<cambio.length; i++) {
                System.out.print(cambio[i] + "  ");
            }

            return true;
        }else {
            for(int i=0; i<cambio.length; i++) {
                if(i == 0) cambio[i] = -1;
                else cambio[i] = 0;
                System.out.print(cambio[i] + "  ");
            }
            return false;
        }



    }

    public static void main(String[] args) {

        int[] valores = {100, 50, 20, 10, 5, 1};
        int[] cantidades = {5, 10, 16, 25, 50, 100};
        int cantidad = 137;
        int[] numMonedas = new int[valores.length];

        System.out.println(cambioMinimo(valores, cantidades, cantidad, numMonedas));

    }
}