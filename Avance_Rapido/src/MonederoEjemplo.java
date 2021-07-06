public class MonederoEjemplo {
    public static void main(String[] args) throws Exception {
        /* tipos de monedas que hay*/
        double valores[] = {2,1,0.5,0.2,0.1,0.05,0.02,0.01}; 

        /* array a devolver */
        int cambio[] = new int[valores.length];

        /* cantidad a alcanzar*/
        double cantidad = 5.89;

        /* sumatorio de las monedas*/
        double sumatorio_monedas = 0;

        /* monedas que vas a coger*/
        int cantidad_monedas_cada_tipo = 0;

        /* Cantidades es un valor infinito, en otros problemas añadiremos otra 
        variables que sea cantidades con un array de valores finitos que dirán 
        el numero de monedas existentes para cada tipo*/

        /* mientras que estemos en los limites del array monedas*/
        for (int i = 0; i < valores.length; i++) {

            while (sumatorio_monedas <= cantidad) {
                /* siempre que no lleguemos a la cantidad*/
                sumatorio_monedas = sumatorio_monedas + valores[i]; /* actualizamos el valor de monedas */
                /* incrementamos los movimientos de las monedas a coger*/
                cantidad_monedas_cada_tipo++;
            }

            /* en el caso de que sea mayor, vamos restando los valores que nos pasamos*/
            sumatorio_monedas = sumatorio_monedas - valores[i];
            cantidad_monedas_cada_tipo = cantidad_monedas_cada_tipo - 1;

            /* los metemos en nuestra solución*/
            cambio[i] = cantidad_monedas_cada_tipo;
            cantidad_monedas_cada_tipo = 0;
        }

        for (int i = 0; i < cambio.length; i++) {
            System.out.print(cambio[i] + " ");
        }

    }
}
