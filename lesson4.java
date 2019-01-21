import java.lang.reflect.Field;
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
    static int []Checkmap = new int [(SIZE*2)+6];

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
          for (int  i: Checkmap) {
            if (i > 3 ){
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
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }
    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        Checkmap[x]++;
        Checkmap[y+SIZE]++;
        int NewX = x+1;
        int NewY = y+1;

        if (NewX == NewY){                          //  основная диагональ
            Checkmap[(SIZE*2)]++;
        }
        int d;
        d = (SIZE-NewX)+1;                          // побочная диагональ
        if (NewX == d){
            Checkmap[(SIZE*2)+1]++;
        }
        if (NewX == y){                             // верхняя диагональ
            Checkmap[(SIZE*2)+2]++;
        }
        if (x == NewY){                             // нижняя диагональ
            Checkmap[(SIZE*2)+3]++;
        }
        int Pd_1 = SIZE-NewY;                       // вернхнаяя побочная диагональ
        if (NewX == Pd_1){
            Checkmap[(SIZE*2)+4]++;
        }
        int Pd_2 = (SIZE - NewY)+2;                 // нижняя побочная диагональ
        if (NewX== Pd_2){
            Checkmap[(SIZE*2)+5]++;
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
