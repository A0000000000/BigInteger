package sum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseStringException{
        System.out.println("输入两个数求和或差");
        System.out.println("数字输入格式\"xxx,xxxx,......,xxxx\"");
        Scanner sc = new Scanner(System.in);
        System.out.println("功能列表:");
        System.out.println("1.第一个数加第二个数");
        System.out.println("2.第一个数减第二个数");
        System.out.println("0.结束程序");
        while(true){
            System.out.print("请输入你的选择:");
            int select = sc.nextInt();
            sc.nextLine();
            if(select == 0)
                break;
            System.out.print("请输入第一个数:");
            String s1 = sc.nextLine();
            System.out.print("请输入第二个数:");
            String s2 = sc.nextLine();
            System.out.print("计算结果为:");
            switch (select){
                case 1:
                    System.out.println(new List(s1).add(new List(s2)));
                    break;
                case 2:
                    System.out.println(new List(s1).sub(new List(s2)));
                    break;
                default:
                    System.out.println("输入错误, 请重新选择");
                    break;
            }
        }
        System.out.println("感谢使用.");
    }
}
