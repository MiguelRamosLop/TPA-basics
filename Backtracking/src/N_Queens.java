public class N_Queens {

    private static int N = 4; 

    /* funcion para mostrar el estado final del tablero*/
    public static void mostrar_tablero(int board[][]) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + board[i][j] + " ");
                
            }
            System.out.println();
        }
    }

    /* para saber si la posicion nxm a insertar la reina esta en danger o no de obtener una X solucion*/
    public static boolean danger (int board[][], int n, int m) {

        boolean is_in_danger = false;
        /* solo miraremos las n ya que en este paso las reinas estan colocadas en las m - (m-1)*/
        int p_i, p_j;

        /* chequeamos las rows (n)*/
        for (p_i = 0; p_i < m; p_i++) {
           if (board[m][p_i] == 1) {
                is_in_danger = false;
                return is_in_danger;
           } 
        }
        /* chequeamos las diagonales de arriba*/
        for (p_i = n, p_j = m; p_i >= 0 && p_j >= 0; p_i--, p_j--) {
            if (board[p_i][p_j] == 1) {
                is_in_danger = false;
                return is_in_danger;
           } 
        }
        /* chequeamos las diagonales de abajo*/
        for (p_i = n, p_j = m; p_j >= 0 && p_i <= N; p_i++, p_j--) {
            if (board[p_i][p_j] == 1) {
                is_in_danger = false;
                return is_in_danger;
           } 
        }

        is_in_danger = true;
        return is_in_danger;
    }

    public static boolean n_queens_rec(int board[][], int m) {
        boolean res = true; 
        if (m >= N) {
            /* ya hemos recorrido todas las col posicionando las reinas*/
            res = true;
            return res;
        } else {

            for (int i = 0; i < N; i++) {
                if (danger(board, i, m) == true) {
                    board[i][m] = 1;

                    if (n_queens_rec(board, m+1) == true) {
                        res = true; 
                        return res;
                    }

                    board[i][m] = 0;
                }
            }
        }
        res = false;
        return res;
    }


    public static void main(String[] args) throws Exception {
        
        int board[][] = { { 0, 0, 0, 0 },
                          { 0, 0, 0, 0 },
                          { 0, 0, 0, 0 },
                          { 0, 0, 0, 0 } };
                          
        if (n_queens_rec(board, 0) == false) {
            System.out.print("Solution does not exist");
        } else {
            mostrar_tablero(board);
        }
        
}


}
