# 四则运算

|人员姓名|学号|Github项目地址|
| :-------------: | :-------------: | --------------- |
|洪裕莹|3221005111||
|郑雅文|3221007081||

| 课程 | [软件工程](https://edu.cnblogs.com/campus/gdgy/CSGrade21-34) |
| :--: | :----------------------------------------------------------: |
| 要求 | [结对项目](https://edu.cnblogs.com/campus/gdgy/CSGrade21-34/homework/13025) |
| 题目 |         实现一个自动生成小学四则运算题目的命令行程序         |

## 一、题目要求及实现情况

1. 题目：实现一个自动生成小学四则运算题目的命令行程序（也可以用图像界面，具有相似功能）。

2. 说明：

   自然数：0, 1, 2, …。

   - 真分数：1/2, 1/3, 2/3, 1/4, 1’1/2, …。
   - 运算符：+, −, ×, ÷。
   - 括号：(, )。
   - 等号：=。
   - 分隔符：空格（用于四则运算符和等号前后）。
   - 算术表达式：e = n | e1 + e2 | e1 − e2 | e1 × e2 | e1 ÷ e2 | (e),其中e, e1和e2为表达式，n为自然数或真分数。

   - 四则运算题目：e = ，其中e为算术表达式。

3. 需求：

   1. 参数控制生成的题目个数 [√]

      `Myapp.exe -n 10`

   2. 参数控制题目中操作数的范围：自然数[√]、真分数[x]、真分数分母[x]

      `Myapp.exe -r 10`

   3. 生成的题目中计算过程不能产生负数 [√]

   4. 每道题目中出现的运算符个数不超过3个[√]

   5. 程序一次运行生成的题目不能重复 [√]

   6. 程序应能支持一万道题目的生成[√]

## 二、设计及具体实现

### 1、预测及开发

**功能实现**

|功能|描述|
| :-------------: | :-------------: |
|随机生成题目|按照输入的条数及范围的指令，随机生成操作数和运算符，组成符合条件的四则运算表达式|
|进行四则运算|式子运算并生成答案|
|查重|去除生成的重复式子|
|评改功能|判断用户输入答案的对错|
|单元测试与性能分析|测试每个方法是否成功实现运行，辅助查找程序故障|

**PSP表**

|                PSP2.1                 |   Personal Software Process Stages    | 预估耗时（分钟） |
| :-----------------------------------: | :-----------------------------------: | :--------------: |
|             **Planning**              |               **计划**                |      **5**       |
|               Estimate                |       估计这个任务需要多少时间        |       1030       |
|            **Development**            |               **开发**                |     **660**      |
|               Analysis                |       需求分析 (包括学习新技术)       |       120        |
|              Design Spec              |             生成设计文档              |        20        |
|             Design Review             |     设计复审 (和同事审核设计文档)     |        10        |
|            Coding Standard            | 代码规范 (为目前的开发制定合适的规范) |        30        |
|                Design                 |               具体设计                |        60        |
|                Coding                 |               具体编码                |       240        |
|              Code Review              |               代码复审                |        30        |
|                 Test                  | 测试（自我测试，修改代码，提交修改）  |       150        |
|             **Reporting**             |               **报告**                |      **40**      |
|              Test Repor               |               测试报告                |        10        |
|           Size Measurement            |              计算工作量               |        10        |
| Postmortem & Process Improvement Plan |     事后总结, 并提出过程改进计划      |        20        |
|                                       |                 合计                  |       705        |

**程序结构**

![image-20230927101725351](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927101725351.png)

### 2、代码说明

本项目共有6个类：Calculate，Expression，FileIO，Fraction，Grade ，Save

 #### 1.Calculate类

Calculate类通过将中缀表达式转化为后缀表达式，再求值。

* 步骤1：将中缀表达式转换为后缀表达式，其一种常见方法是使用栈（stack）数据结构。

  1. 创建一个空栈，用于存储操作符。

  2. 创建一个空字符串或列表，用于存储后缀表达式。

  3. 从左到右扫描中缀表达式的每个字符（数字、操作符、括号等）。

  4. 对于每个字符，执行以下操作：

     a. 如果是操作数（数字），直接添加到后缀表达式字符串或列表中。

     b. 如果是左括号 "("，将其推入栈中。

     c. 如果是右括号 ")"，则弹出栈中的操作符并将它们添加到后缀表达式，直到遇到左括号为止。然后将左括号从栈中弹出，但不添加到后缀表达式中。

     d. 如果是操作符（+、-、*、/ 等），比较其与栈顶操作符的优先级：

     - 如果栈为空或栈顶是左括号 "("，则直接将当前操作符推入栈中。
     - 否则，比较当前操作符与栈顶操作符的优先级。如果当前操作符的优先级较高或相等，则将栈顶操作符弹出并添加到后缀表达式中，然后将当前操作符推入栈中。
     - 重复上述过程，直到当前操作符的优先级小于栈顶操作符或栈为空。

  5. 扫描完整个中缀表达式后，将栈中剩余的操作符全部弹出并添加到后缀表达式中。

* 步骤2：后缀表达式求值

  1. 创建一个空栈，用于存储操作数。

  2. 从左到右遍历后缀表达式中的每个元素。

  3. 对于每个元素，执行以下操作：

     a. 如果是操作数（数字），将其推入栈中。

     b. 如果是操作符（+、-、*、/ 等），从栈中弹出两个操作数，执行相应的操作符运算，并将结果推回栈中。

  4. 遍历完整个后缀表达式后，栈中应该只剩下一个元素，即最终的计算结果。

  5. 弹出栈中的结果，即为后缀表达式的计算结果。

* 主要代码:

  ```java
  public Fraction calculate(Queue<String> queue) {//考虑分数的计算，直接把数字转化为fraction对象，再压入fraction栈
          Stack<Fraction> fracStack = new Stack<>();
          String str = "";//中间结果字符串
          Save.string = "";//每一次操作开始就把string置为空
  
          while (!queue.isEmpty() ) {
              // 从队列中出队
              String s = queue.remove();
             // Save.save(s);//保存结果
              // 如果是数字，就压入栈中
              if (isDigital(s.charAt(0))) {
                  //parseInt(String s): 返回用十进制参数表示的整数值。
                  Fraction f = new Fraction(Integer.parseInt(s));
                  fracStack.push(f);
                  // 如果是运算符，就从栈中弹出两个元素
              } else if (isOperator(s.charAt(0))) {
                  char c = s.charAt(0);
                  Fraction f = fracStack.pop();//操作数
                  Fraction f1 = new Fraction(f.getNumerator(),f.getDenominator());
                  f = fracStack.pop();//被操作数
                  Fraction f2 = new Fraction(f.getNumerator(),f.getDenominator());
                  /**
                   * 表达式合法性检验
                   */
                  if ( c == '-' ) {
                      f = f2.sub(f1);//若结果为负，break；返回-1，用以标记表达式不合法
                      f.Appointment();//简单约分
                      if (f.getDenominator() < 0 || f.getNumerator() < 0  ) {//分母小于零出现负数
                          fracStack.push(new Fraction(100000));
                          break;
                      }
                  }
  
                  if ( c == '/'){
                      f2.Appointment();
                      if(f1.getDenominator()==0 || f1.getNumerator() ==0){//如果分数为0
                        fracStack.push(new Fraction(100000));
                          break;
                      }
                  }
  
                  /**
                   * 栈顶元素的四则运算
                   */
                  switch (c) {
                      case '+': {
                          fracStack.push(f2.add(f1));
                          break;
                      }
                      case '-': {
                          fracStack.push(f2.sub(f1));
                          break;
                      }
                      case '*': {
                          fracStack.push(f2.muti(f1));
                          break;
                      }
                      case '/': {
                          fracStack.push(f2.div(f1));
                          break;
                      }
                  }//switch case 结束的地方
  
                  /**
                   * 用来查重
                   */
                  str += fracStack.peek().getNumerator()+"/"+fracStack.peek().getDenominator()+" "; //计算中间结果
              }
          }
  
          Save.save(str);//保存中间结果
          if(fracStack.isEmpty() == true || fracStack.peek().getNumerator() == 100000){//注意分子的值
              Fraction f = new Fraction(100000);//不合法的式子
              return f;
          }else
          return  fracStack.pop();
      }    
  ```

  

#### 2.Expression类

Expression类共包含两个方法：
*  `generateExp(Integer limit)`
  
   运算数和运算符是随机生成的，每一次循环生成一个运算数和一个运算符，`limit`控制循环的次数，即运算符的数目。
   
* `legalExp (Integer number,Integer limit)`
  
   在这个方法中对不合法和重复的式子进行剔除，不合法的情况有分母为零，出现负数。在调用`Calculate`方法计算的时候，把含有以上两种情况的式子都会返回`100000`，根据这个信息判断式子是否合法。查重时，通过比对式子和已生成的中间结果，如果重复则重新生成。把符合条件的式子写入到`expression`文件中，答案写在`answer`文件中。
   
* 主要代码：

   ```java
   public void legalExp (Integer number,Integer limit) throws IOException {
          /* 生成合法未重复的表达式，number表示题目数量,limit表示运算数范围*/
           int j = 1;//控制题目生成的数量,从1开始
           String str1 = "";
           String str2 = "";//存放中间结果
           String str3 = "";//算术表达式，写入Expression.txt
           String str4 = "";//答案，写入Answer.txt
           HashMap<String, Integer> answers = new HashMap<String, Integer>();
           FileIO writer = new FileIO();//输入流对象
           Expression exp = new Expression();//表达式对象
           do {
               str1 = exp.generateExp(limit) + "= ";//获得原始表达式
               Calculate cal = new Calculate();
               Fraction f = cal.outcome(str1);//计算结果，未化简
               if (f.getNumerator() == 100000) {
                   // //剔除表达不合法的算术表达式
                   //   System.out.println("表达式出错");
                   continue;
               }
               str2 = f.transferFraction(f);//最终结果，已经化简
               if(answers.containsKey(Save.string)){
                   //System.out.println("key碰撞");
                   continue;
               }else{
                   answers.put(Save.string, null);
                  // System.out.print("NO."+j+" "+str1 + "\n");//输出合法且没有重复的式子
                   System.out.printf("NO.%4d      %s%n",j,str1);//格式化输出
                   str3 += j+"."+"    "+str1+"\n";
                   str4 += j+"."+"    "+str1+str2+"\n";
                   j++;
               }
           } while (j <= number);
           System.out.println("题目生成完毕！");
           writer.fileWrite(str3, Paths.get("arithmetic/textFile/Expression.txt"));//整个字符串
           writer.fileWrite(str4, Paths.get("arithmetic/textFile/Answer.txt"));//整个字符串
       }
   ```

#### 3.Fraction类

这个类是实现分数运算的核心类，封装相关的方法：构造分数函数，四则运算等

   * 主要代码:

     ```java
     // 构建一个分数
     public Fraction( int numerator,int denominator) {
         super();
         this.denominator = denominator;
         this.numerator = numerator;
     }
     // 构建一个可化简为整数的分数
     public Fraction(int numerator) {
         this.denominator = 1;
         this.numerator = numerator;
     }
     // 加法运算
     public Fraction add(Fraction r) {
         int a = r.getNumerator();// 获得分子
         int b = r.getDenominator();// 获得分母
         int newNumerator = numerator * b + denominator * a;
         int newDenominator = denominator * b;
         Fraction result = new Fraction(newNumerator,newDenominator);
         return result;
     }
     
     // 减法运算
     public Fraction sub(Fraction r) {
         int a = r.getNumerator();// 获得分子
         int b = r.getDenominator();// 获得分母
         int newNumerator = numerator * b - denominator * a;
         int newDenominator = denominator * b;
         Fraction result = new Fraction(newNumerator,newDenominator);
         return result;
     }
     
     // 分数的乘法运算
     public Fraction muti(Fraction r) { // 乘法运算
         int a = r.getNumerator();// 获得分子
         int b = r.getDenominator();// 获得分母
         int newNumerator = numerator * a;
         int newDenominator = denominator * b;
         Fraction result = new Fraction(newNumerator,newDenominator);
         return result;
     }
     
     // 分数除法运算
     public Fraction div(Fraction r) {
         int a = r.getNumerator();// 获得分子
         int b = r.getDenominator();// 获得分母
         int newNumerator = numerator * b;
         int newDenominator = denominator * a;
         Fraction result = new Fraction(newNumerator,newDenominator);
         return result;
     }
     ```

#### 4.Grade类

Grade类根据输入的答案进行答题记录，最后输入答题情况到Grade文本，并在控制台打印答题情况，计算正确率。

#### 5.Save类

Save类设置一个private的String变量对计算过程中的中间结果进行保存，用于查重时的中间结果比对。这样就可以不用以函数返回值的形式记录中间结果

#### 6.FileIO类

FileIO类把需要输出内容写入到对应的文本。

### 3、运行结果

**Main.java**

1. 首先输入要生成的题目数及数值范围，本次运行设置题目个数为10，数值范围为5。

   程序生成指定条件的表达式后提示生成完毕，接着程序询问是否进入答题，输出“0”表示“不答题”，进程结束退出

   ![image-20230927111530289](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927111530289.png)

   输出“1”表示进入答题，提示答题开始

   ![image-20230927110803373](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927110803373.png)

2. 进入答题环节，程序打印出表达式和输入提示，用户输入答案后，程序马上判断用户输入是否正确并打印提示

   ![image-20230927110814550](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927110814550.png)

3. 答题完毕后，输出总成绩，包含用户答对题目数、答错题目数和正确率

   ![image-20230927110832457](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927110832457.png)

4. 各个txt文档情况

   1、Answers.txt文件

   在生成题目的同时，计算出所有题目的答案，并存入

   ![image-20230927110909154](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927110909154.png)

   2、Exercises.txt文件

   存入生成的题目

   ![image-20230927111159886](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927111159886.png)

   3、Grade.txt文件

   存入答题正确与错误数量统计结果

   ![image-20230927111314757](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927111314757.png)



## 三、单元测试

### 1、Calculate 类

1. 测试方法：

  `ToSuffixExpression`：中缀表达式转为后缀表达式

  `Calculate`：计算表达式的结果

2. 测试用例1： 3 * 2 + 2 / 1 - 2 - 1 =

   估计：结果5/1 ，后缀 3, 2, *, 2, 1, / , +,2, -,1, -

   实际：结果5/1 ，后缀 3, 2, *, 2, 1, / , +,2, -,1, -
   ![image-20230926200821405](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926200821405.png)

   分析：类中每一个方法都正确实现对应的功能，无错误

3. 测试用例2：1 + ( 5 - 3 - 1 ) =

   估计：结果2/1 ，后缀 1, 5, 3, -, 1, -, +

   实际：结果2/1 ，后缀 1, 5, 3, -, 1, -, +![image-20230926200516439](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926200516439.png)分析：类中每一个方法都正确实现对应的功能，且实际值与估计值一样，无错误


### 2、Expression 类
1. 测试方法：
  - `generateExp` 生成原始表达式
  - `legalExp` 生成合法表达式
2. 测试

  ​    ![image-20230927100034175](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927100034175.png)

分析：能根据要求生成指定数量的题目，两个方法均无错，测试通过

### 3、Fraction 类
1. 测试方法：
  1. `add` 加法
  2. `sub` 减法
  3. `muti` 乘法
  4. `div` 除法
  5. `Appointment` 约分
  6. `transferFraction` 约分化简，真分数、分数形式表示

  共6个方法

2. 测试
  - 结果

    ![image-20230926214514847](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926214514847.png)

    ![image-20230926211730408](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926211730408.png)

  - 分析：以上6个测试方法通过并且覆盖了各种情况，那么可认为测试是正确的，无错误

  
## 四、性能分析
**在随机生成10000道题目**

内存![image-20230926221907719](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926221907719.png) 

GC活动

![image-20230926221947296](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926221947296.png)

类

![image-20230926222014447](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926222014447.png) 

线程

![image-20230926222044373](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926222044373.png)

CPU负载

![image-20230926222113142](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230926222113142.png)

对于代码的性能分析，需要考虑几个关键点：

1. **表达式生成性能：** `generateExp` 方法中使用了 `java.util.Random` 类来生成随机的操作数和运算符。生成随机数的性能通常取决于底层的伪随机数生成算法以及生成的数量。在这里，代码生成了四个操作数和一个运算符，然后根据随机数选择是否添加括号。性能取决于 `limit` 参数的值，因为它限制了生成的操作数的范围。通常来说，生成一个表达式的性能应该是很快的。

2. **表达式计算性能：** 代码中使用了 `Calculate` 类来计算表达式的结果。性能取决于表达式的复杂性和计算的速度。如果表达式很简单，计算性能应该是很好的。但是，如果表达式非常复杂，可能会导致计算性能下降。

3. **查重性能：** 代码使用一个 `HashMap` 来检查生成的表达式是否重复。查重操作通常是很快的，因为 HashMap 具有常量时间复杂度的查找操作。性能取决于生成的表达式数量和 HashMap 的负载因子。

4. **文件写入性能：** 代码将生成的表达式和答案写入文本文件。文件写入操作通常相对较慢，尤其是当写入的数据量很大时。性能取决于文件系统的性能和磁盘 I/O 速度。

5. **主方法性能：** 在主方法中，代码调用了 `legalExp` 方法来生成大量的表达式。性能取决于 `legalExp` 方法的运行时间以及生成的表达式数量。

综合来看，这段代码的性能主要受以下因素影响：生成表达式的复杂性、生成的表达式数量、计算表达式的复杂性、文件写入速度和随机数生成器的性能。要进一步优化性能，可以考虑使用更高效的算法来生成表达式、并行化计算过程、批量写入文件以减少文件 I/O 次数等措施。

**Main覆盖率**

![image-20230927112352942](C:\Users\cici\AppData\Roaming\Typora\typora-user-images\image-20230927112352942.png)

## 五、项目总结与心得

### 1、项目小结

**项目完成情况：**
- 实现了生成题目的功能，并且能够根据指定的参数控制题目的数量和操作数的范围。
- 代码中包含了单元测试，用于验证各个类的功能。
- 代码中使用了类来封装不同的功能，提高了代码的可维护性。

**性能分析：**
- 本项目进行了性能分析，主要关注了表达式生成、表达式计算、查重、文件写入和主方法性能等方面。
- 性能分析是一个重要的步骤，可以帮助我们识别代码中的潜在性能瓶颈，并采取适当的措施来提高性能。

**单元测试：**
- 进行了单元测试，覆盖了不同类的各种方法，有助于验证代码的正确性。
- 确保单元测试包含了各种边界情况和异常情况，以确保代码的健壮性。

**改进方向：**

- 考虑添加更多的注释和文档，以帮助其他人理解我们的代码。特别是对于复杂的算法或数据结构
- 可以使用日志记录来记录程序的运行情况，以便在需要时进行故障排除。
- 进一步优化性能，可以探索并行化生成题目或计算答案的方法，以加快处理速度。
- 考虑将程序的输入参数和输出结果更加友好化，以提高用户体验。

### 2、关于结对项目的心得体会：

​		作为一个结对编程团队，我们在完成这个小学四则运算自动生成程序的项目中积累了许多宝贵的经验和教训。以下是我们从这次结对编程项目中所获得的心得体会：
​		首先，我们深刻体会到了团队协作的价值。在整个项目中，我们密切合作，共同讨论问题，共享知识，互相提供支持。这种密切的合作让我们能够更快地解决问题，找到更好的解决方案。完成这个项目时我们虽然一开始都不怎么懂，但是通过大量查阅以及双方的积极沟通，共享意见，我们很顺利的完成了本次项目。我们学会了倾听对方的意见，协商取舍，以达成一致的决策。这种团队协作的精神不仅在项目中有所体现，在日常工作和生活中也有着积极的影响。
​		其次，通过结对编程，我们的编程技能得到了提升。互相审查代码、讨论设计和实现细节，使我们更加注重代码质量和性能优化。我们分享了各自的编程技巧和经验，学到了新的知识，扩展了自己的技术领域。这种技术交流和分享有助于我们在项目中不断进步，也为今后的工作积累了更多的经验。
​		此外，我们在项目中学会了面对挑战和解决问题。在编程过程中，我们遇到了各种各样的问题，有时是算法的优化，有时是调试的困难，有时是对题目要求的理解。但是，通过相互协作和不断尝试，我们最终找到了解决方案。这让我们更有信心面对未来的挑战，相信只要团队齐心协力，问题总是可以解决的。
​		另外，结对编程教会了我们更好地理解需求和用户需求。在项目开始之前，我们必须仔细分析需求，明确项目的目标和范围。我们需要考虑用户的需求，设计出符合他们期望的功能。这个过程让我们更深入地理解了软件开发的本质，编写出更加贴近用户需求的程序。
​		最后，这次结对编程项目让我们更加注重代码的可维护性和可读性。由于代码需要经常被另一位团队成员阅读和审查，我们不得不编写清晰、简洁的代码，添加注释以解释设计和算法。这不仅有助于项目的顺利进行，也培养了我们良好的编码习惯，提高了代码的质量。
​		总的来说，通过这次结对编程项目，我们不仅完成了一个有挑战性的任务，还积累了宝贵的经验，提升了编程技能，培养了团队协作和解决问题的能力。我们认识到了团队合作的价值，以及通过互相学习和分享来实现个人和团队的成长。我们期待着未来还有更多的结对编程机会，继续发展我们的技能和经验。结对编程，让我们在合作中共同前进，取得更大的成就。

## 六、最终PSP表
|                PSP2.1                 |   Personal Software Process Stages    | 预估耗时（分钟） | 实际耗时（分钟） |
| :-----------------------------------: | :-----------------------------------: | :--------------: | :--------------: |
|             **Planning**              |                 计划                  |      **5**       |      **10**      |
|               Estimate                |       估计这个任务需要多少时间        |       1030       |       1135       |
|            **Development**            |               **开发**                |     **660**      |     **695**      |
|               Analysis                |       需求分析 (包括学习新技术)       |       120        |       150        |
|              Design Spec              |             生成设计文档              |        20        |        30        |
|             Design Review             |     设计复审 (和同事审核设计文档)     |        10        |        15        |
|            Coding Standard            | 代码规范 (为目前的开发制定合适的规范) |        30        |        20        |
|                Design                 |               具体设计                |        60        |        70        |
|                Coding                 |               具体编码                |       240        |       260        |
|              Code Review              |               代码复审                |        30        |        30        |
|                 Test                  | 测试（自我测试，修改代码，提交修改）  |       150        |       120        |
|             **Reporting**             |                 报告                  |      **40**      |      **60**      |
|              Test Repor               |               测试报告                |        10        |        20        |
|           Size Measurement            |              计算工作量               |        10        |        10        |
| Postmortem & Process Improvement Plan |     事后总结, 并提出过程改进计划      |        20        |        30        |
|                                       |                 合计                  |       705        |       765        |
