package main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SimilarityMain {
    public LinkedHashMap<Character, int[]> vectorMap = new LinkedHashMap<Character, int[]>();
    int[] tempArray = null;
    public SimilarityMain(String string1, String string2) {
        for (Character character1 : string1.toCharArray()) {
            if (vectorMap.containsKey(character1)) {
                vectorMap.get(character1)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(character1, tempArray);
            }
        }
        for (Character character2 : string2.toCharArray()) {
            if (vectorMap.containsKey(character2)) {
                vectorMap.get(character2)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(character2, tempArray);
            }
        }
    }

    // 求余弦相似度
    public double sim() {
        double result = 0;
        //cos c =  a*b/(|a|*|b|)
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }

    // 计算两个向量的模的乘积的平方根
    public double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    // 计算两个向量的点积（点乘法）
    public double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }

    // 计算两个向量的平方和，用于计算模
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0.00;  //向量1的模
        double result2 = 0.00;  //向量2的模
        double resultproduct = 0.00;//向量积
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        resultproduct = result1 * result2;
        return resultproduct;
    }
}