package imp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

//算法部分
public class algorithm {
        //存放可能的情况
        static Vector<String> vector = new Vector<String>();
        //定义随机产生的四个数
        static int[] poker = new int[4];
        //转换后的四个数
        static int m[]=new int [4];
        static String n[] = new String[4];
        //用来判断是否有解
        static boolean flag = false;
        //存放操作符
        static char[] operator = { '+', '-', '*', '/' };

        public void startAlgorithm(){
            Random rand = new Random();
            //"随机产生四个数字，使用+，-，*，/进行计算,使最后计算结果为24"
            for(int i=0;i<4;i++){
                //随机生成四个不同的int型数
                while(true){
                    int number = rand.nextInt(13)+1;
                    if(!contains(poker,number)){
                        poker[i] = number;
                        break;
                    }
                }
            }
            calculate();
            if(vector.size()==0){//如果vector中没有元素，说明当前这四个数是不能变成24（生成一定能转化为24的四个随机数）
                startAlgorithm();//出现该种情况，则重新调用，重新生成四个随机数
            }
        }

        //清除vector中所有的值
        public void clearvec(){
            vector.clear();
        }

        //返回相应的四个随机数的值
        public String getN(int i){
            return n[i];
        }

        //判断用户是否对
        public boolean isVecnum(String s) {
            for (int i = 0; i < vector.size(); i++) {
                if (s.equals(vector.get(i))){
                    return true;
                }
            }
            return false;
        }
        //判断是否有重复
        public static boolean contains(int[] arr, int key) {
            for (int i=0; i<arr.length; i++) {
                //如果相等返回true
                if (arr[i] == key){
                    return true;
                }
            }
            return false;
        }

        //计算生成24的函数
        public static void calculate(){
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            //存放数字，用来判断输入的4个数字中有几个重复的，和重复的情况
            for (int i = 0; i < poker.length; i++) {
                if(map.get(poker[i]) == null){
                    map.put(poker[i], 1);
                }
                else {
                    map.put(poker[i], map.get(poker[i]) + 1);
                }
            }
            if(map.size() == 4){
                //4个数都不同的情况
                int i,j,k,l;

                for (i=0; i<4; i++)    //4的排列 4!=24，每种情况调用calculation
                    for (j=0; j<4; j++)
                        if (j!=i)           //第2数和第1个数不能重复
                            for (k=0; k<4; k++)
                                if (k!=j && k!=i) //第3数和第1，2个数不能重复
                                    for (l=0; l<4; l++)
                                        if (l!=i && l!=j && l!=k) //第4数和第1，2，3个数不能重复
                                        {
                                            calculation(poker[i],poker[j],poker[k],poker[l]);//调用calculation函数，进行进行加、减、乘、除运算
                                        }
            }
        }

        //进行转换
        public  static void show(int[] m, int p){
            if(m[p]==1){
                n[p]="A";}
            if(m[p]==2){
                n[p]="2";}
            if(m[p]==3){
                n[p]="3";}
            if(m[p]==4){
                n[p]="4";}
            if(m[p]==5){
                n[p]="5";}
            if(m[p]==6){
                n[p]="6";}
            if(m[p]==7){
                n[p]="7";}
            if(m[p]==8){
                n[p]="8";}
            if(m[p]==9){
                n[p]="9";}
            if(m[p]==10){
                n[p]="10";}
            if(m[p]==11){
                n[p]="J";}
            if(m[p]==12){
                n[p]="Q";}
            if(m[p]==13){
                n[p]="K";}
        }
        //把这四个数能成为24的所有例子存到可变数组Vector中
        public static void calculation(int num1, int num2, int num3, int num4){
            for (int i = 0; i < 4; i++){
                //第1次计算，先从四个数中任意选择两个进行计算
                char operator1 = operator[i];
                int firstResult = calcute(num1, num2, operator1);//先选第一，和第二个数进行计算
                int midResult = calcute(num2, num3, operator1);//先选第二和第三两个数进行计算
                int tailResult = calcute(num3,num4, operator1);//先选第三和第四俩个数进行计算
                for (int j = 0; j < 4; j++){
                    //第2次计算，从上次计算的结果继续执行，这次从三个数中选择两个进行计算
                    char operator2 = operator[j];
                    int firstMidResult = calcute(firstResult, num3, operator2);
                    int firstTailResult = calcute(num3,num4,operator2);
                    int midFirstResult = calcute(num1, midResult, operator2);
                    int midTailResult= calcute(midResult,num4,operator2);
                    int tailMidResult = calcute(num2, tailResult, operator2);
                    for (int k = 0; k < 4; k++){
                        //第3次计算，也是最后1次计算，计算两个数的结果，如果是24则输出表达式
                        char operator3 = operator[k];
                        //在以上的计算中num1，num2,num3,num4都是整型数值，但若要输出为带有A，J,Q,K的表达式，则要将这四个数都变为String类型，下同
                        if(calcute(firstMidResult, num4, operator3) == 24){
                            m[0]=num1;
                            m[1]=num2;
                            m[2]=num3;
                            m[3]=num4;
                            for(int p=0;p<4;p++){
                                show(m, p);
                            }
                            vector.add("((" + n[0] + operator1 + n[1] + ")" + operator2 + n[2] + ")" + operator3 + n[3]);
                            flag = true;//若有表达式输出，则将说明有解，下同
                        }
                        if(calcute(firstResult, firstTailResult, operator3) == 24){
                            vector.add("(" + n[0] + operator1 + n[1] + ")" + operator3 + "(" + n[2] + operator2 + n[3] + ")");
                            flag = true;
                        }
                        if(calcute(midFirstResult, num4, operator3) == 24){
                            m[0]=num1;
                            m[1]=num2;
                            m[2]=num3;
                            m[3]=num4;
                            for(int p=0;p<4;p++){
                                show(m, p);
                            }
                            vector.add("(" + n[0] + operator2 + "(" + n[1] + operator1 + n[2] + "))" + operator3 + n[3]);
                            flag = true;
                        }
                        if(calcute(num1,midTailResult, operator3) == 24){
                            m[0]=num1;
                            m[1]=num2;
                            m[2]=num3;
                            m[3]=num4;
                            for(int p=0;p<4;p++){
                                show(m, p);
                            }
                            vector.add( n[0] + operator3 + "((" + n[1] + operator1 + n[2] + ")" + operator2 + n[3] + ")");
                            flag = true;
                        }
                        if(calcute(num1,tailMidResult,operator3) == 24){
                            m[0]=num1;
                            m[1]=num2;
                            m[2]=num3;
                            m[3]=num4;
                            for(int p=0;p<4;p++){
                                show(m, p);
                            }
                            vector.add( n[0] + operator3 + "(" + n[1] + operator2 + "(" + n[2] + operator1 + n[3] + "))");
                            flag = true;
                        }
                    }
                }
            }
        }
        //给定2个数和指定操作符的计算
        public static int calcute(int count1, int count2, char operator) {
            if (operator == '+') {
                return count1 + count2;
            }
            else if (operator == '-') {
                return count1 - count2;
            }
            else if (operator == '*') {
                return count1 * count2;
            }
            else if ((operator == '/' )&& (count2 != 0) && (count1%count2==0)) {
                return count1 / count2;
            }
            else {
                return -1000;//返回一个比较大的负数，则就不会变成24，说明不成立
            }
        }
    }

