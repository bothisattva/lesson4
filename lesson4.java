

import java.util.Random;
import java.util.Scanner;

public class lesson4 {
    public static int SIZE = 5;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    static int [] CheckMap = new int [(SIZE*2)+5];

    public static void main(String[] args) {

        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin()== true) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin()== true) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
    public static boolean checkWin() {
        for (int i = 0; i <CheckMap.length ; i++) {
            if (CheckMap[i]> 3 ){
                return  true;
            }
        }
        return false;
    }
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    public static void aiTurn() {
        int x;
        int y;
        for (int i = 0; i < CheckMap.length; i++) {
            if (CheckMap[i] > 2) {
                if (i >= 0 && i <= 4) {
                    for (int j = 0; j < map[i][j]; j++) {
                        if (map[i][j] != DOT_X && map[i][j] != DOT_O) {
                            map[i][j] = DOT_O;
                        }
                    }
                }
            }
            if (i >= 5 && i <= 9) {
                int NewJ;
                for (int j = 0; j < map.length; j++) {
                    NewJ =  SIZE- j;
                    if (map[j][NewJ] != DOT_X && map[j][NewJ] != DOT_O) {
                        map[j][NewJ] = DOT_O;
                    }
                }
            }
            if (i == 10) {
                for (int j = 0; j < map.length; j++) {
                        if (map[j][j] != DOT_X && map[j][j] != DOT_O) {
                             map[j][j] = DOT_O;
                            }
                     }
                }
            if (i == 11) {
                for (int j = 0; j < map.length; j++) {
                        if (map[j][j-1] != DOT_X && map[j][j-1] != DOT_O) {
                            map[j][j-1] = DOT_O;
                        }
                    }

            }
            if (i == 12) {
                for (int j = 0; j < map.length; j++) {
                    if (map[j][SIZE-j] != DOT_X && map[j][SIZE-j] != DOT_O) {
                        map[j][SIZE-j] = DOT_O;
                    }
                }

            }
            if (i == 11) {
                for (int j = 0; j < map.length; j++) {
                    if (map[j][j+1] != DOT_X && map[j][j+1] != DOT_O) {
                        map[j][j+1] = DOT_O;
                    }
                }

            }
            if (i == 11) {
                for (int j = 0; j < map.length-1; j++) {
                    if (map[j][SIZE-j-1] != DOT_X && map[j][SIZE-j-1] != DOT_O) {
                        map[j][SIZE-j-1] = DOT_O;
                    }
                }

            }
            if (i == 11) {
                for (int j = 0; j < map.length; j++) {
                    if (map[j][SIZE-j+1] != DOT_X && map[j][SIZE -j+1] != DOT_O) {
                        map[j][SIZE-j+1] = DOT_O;
                    }
                }

            }
             else {

                do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
                } while (!isCellValid(x, y));
                 System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
                map[y][x] = DOT_O;
              }
        }
    }
    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        CheckMap[x]++;
        CheckMap[y+SIZE]++;
        int NewX = x+1;
        int NewY = y+1;

        if (NewX == NewY){                          //  основная диагональ
            CheckMap[(SIZE*2)]++;
        }
        int d;
        d = (SIZE-NewX)+1;                          // побочная диагональ
        if (NewX == d){
            CheckMap[(SIZE*2)+1]++;
        }
        if (NewX == y){                             // верхняя диагональ
            CheckMap[(SIZE*2)+2]++;
        }
        if (x == NewY){                             // нижняя диагональ
            CheckMap[(SIZE*2)+3]++;
        }
        int Pd_1 = SIZE-NewY;                       // вернхнаяя побочная диагональ
        if (NewX == Pd_1){
            CheckMap[(SIZE*2)+4]++;
        }
        int Pd_2 = (SIZE - NewY)+2;                 // нижняя побочная диагональ
        if (NewX== Pd_2){
            CheckMap[(SIZE*2)+5]++;
        }
         // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
