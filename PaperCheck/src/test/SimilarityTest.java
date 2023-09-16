package test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

//余弦相似度算法测试
public class SimilarityTest {
    LinkedHashMap<Character, int[]> vectorMap = new LinkedHashMap<Character, int[]>();

    int[] tempArray = null;

    public SimilarityTest(String string1, String string2) {
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

    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    // 点乘法
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }

    // 求平方和
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
    public static void main(String[] args) {
        String s1 = "这只皮靴号码大了。那只号码合适";
        String s2 = "这只皮靴号码不小，那只更合适";
        SimilarityTest similarity1 = new SimilarityTest(s1, s2);
        System.out.println(similarity1.sim());
    }
}
