# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block.
   ```java
   // your code here
   public class Example {
      public static void main(string[] args) {
         String string1 = new String("Jana");
         String string2 = new String("Jana");
   
         System.out.println(string1 == string2);  // false because they have different memory locations.
         System.out.println(string1.equals(string2));  // true because they have the same content. 
   
   ```
   == is to check whether they are the same object which means whether they point to the same memory location. 

   .equals() is to check whether the two objects have the same content. 



2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner? 

We can use `String.CASE_INSENSITIVE_ORDER` to sort a list of strings in a case insensitive manner.

```
public class Example {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "Banana");

        fruits.sort(String.CASE_INSENSITIVE_ORDER);

        System.out.println(fruits);
    }
}

// Output: [apple, Banana]
```

We can also use `compareToIgnoreCase()` like s1.compareToIgnoreCase(s2).

From my code, in the `getGameNames()` mehtod of the `GameList` class, there is an exmpale of using `String.CASE_INSENSITIVE_ORDER`:
```
@Override
public List<String> getGameNames() {
    // convert a collection of games into a list of names, sorted alphabetically
    return listOfGames.stream()
            .map(BoardGame::getName)
            .sorted(String.CASE_INSENSITIVE_ORDER)
            .collect(Collectors.toList());
}
```
Also, there is a case-insensitive comparison in the `filterString()` method of the `Filters` class:
```
switch (op) {
        case EQUALS:
            return gameData.equalsIgnoreCase(value);
        case CONTAINS:
            return gameData.toLowerCase().contains(value.toLowerCase());   
```




3. In our version of the solution, we had the following code (snippet)
    ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
    ```
    Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point. 

The order affects the result, for example, if you check > first and then >= after, when you encounter something like x>=5, the wrong order will mistakenly identify it as > instead of >=, so the correct order of checking should be first to check the more specific operators like >= and <= then check the more general operators like > and <. This will ensure that complex comparison operators can be correctly recognized.    


4. What is the difference between a List and a Set in Java? When would you use one over the other? 

In Java, there are some differences between a List and a Set.

List:
- can have duplicat elements
- elements have an insertion order
- can be accessed by index
- when we want to maintain the order of the elements or need duplicates elements, we can use List.

Set:
- not allow duplicate elements
- don't have order
- deduplication and quick search
- we can use set when unique elements are required or removing duplicate elements. 


5. In [GamesLoader.java](src/main/java/student/GamesLoader.java), we use a Map to help figure out the columns. What is a map? Why would we use a Map here? 
   
In Java, a Map is something that stores key-value pairs, so you can quickly look up a value by its key. In `GamesLoader.java`, we use map to dynamically map CSV field names to their actual locations in the file. This approach allows the code to be flexible in handling CSV files in different orders and to extract the required data quickly and safely.



6. [GameData.java](src/main/java/student/GameData.java) is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?

In Java, `enum` is used when we know all possible values at compile time. We use enum for this application because firstly, if the column names of the CSV file change, they only need to be changed in one place. Moreover, the `GameData` enumeration centralizes the definitions of all game data columns, making the code more organized. Then, by adding the columnName field and related methods, this enumeration provides a mapping between the enumeration constant names such as NAME and RATING and the actual column names in the CSV file such as rating and objectname. 




7. Rewrite the following as an if else statement inside the empty code block.
    ```java
    switch (ct) {
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                case INVALID:
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
    ``` 

    ```java
    // your code here, don't forget the class name that is dropped in the switch block..
   public class MyClass {
      public void myMethod() {
         if (ct == CMD_QUESTION || ct == CMD_HELP) {
            processHelp();
         } else if (ct == INVALID) {
            CONSOLE.printf("%s%n", ConsoleText.INVALID);
         } else {
            CONSOLE.printf("%s%n", ConsoleText.INVALID);
        }
      }
   }
   
   ```

## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
// your consoles output here
*******歡迎使用桌遊競技場規劃工具*******

這是一個幫助您規劃想要在桌遊競技場(Board Game Arena)
上玩的遊戲的工具。

要開始使用，請在下方輸入您的第一個指令，
或者輸入 ? 或 help 來查看指令選項。
> help

    To work with the BGArenaPlanner, you can filter the BGA games list,  
    add games to your list, remove games from your list, and save your list to a file.
    Filters are progressive, so you can add multiple filters to narrow down the list.

    The following commands are available:
    exit - exit the program
    help or ? [list | filter] - show this help message, Options list - show help for the list command, filter - show help for the filter command.
    
> exit
Goodbye, have fun playing.
```

Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?


As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 


Besides expanding market share, as a non-native English speaker, I think this is an act of respecting the culture of users. 

The internet remains dominated by a few languages despite its global nature, creating significant disparities in content accessibility. While nearly 8% of internet users speak Spanish, only 4% of online content is available in this language. Similarly, around 5% of internet users speak some form of Arabic, yet merely 1% of content is written in Arabic. Even more concerning, some of the world's most widely spoken languages, including Hindi, Portuguese, and Bengali, have minimal representation online. This imbalance not only represents missed market opportunities but also demonstrates a lack of cultural respect toward non-English speaking users worldwide.
Improper localization carries serious consequences beyond limited market reach. Poor translation quality can create misunderstandings, erode user trust, reduce engagement, and ultimately damage brand reputation in target markets. When organizations ignore regional differences, they fail to account for linguistic variations between regions using the same language, overlook cultural preferences and local expressions, and risk alienating specific segments of their potential user base. Inadequate cultural understanding may lead to incorporating content considered offensive or inappropriate, missing cultural nuances that impact how users interact with products, and unintentional cultural insensitivity that can be difficult to overcome once established.
To implement effective localization strategies, organizations should invest in qualified translators with both language fluency and industry-specific knowledge, avoiding machine translation for critical content and considering transcreation rather than direct translation for marketing materials. Thorough cultural research is essential, involving partnerships with native speakers or cultural experts, investigation of cultural norms, taboos, and preferences, and testing content with locals before wide release. Technical preparation should include designing with multilingual support from the beginning, accommodating various scripts and text directions (LTR/RTL), and implementing flexible layouts that can adapt to text expansion or contraction in different languages. Finally, ongoing maintenance through regular review of localized content, updating translations when main content changes, and collecting and incorporating user feedback from each locale ensures sustained quality and relevance.
Proper localization extends far beyond simple translation—it represents respect for users' cultural identity while dramatically improving both market share and user experience. The investment in thoughtful localization demonstrates commitment to global users and can become a significant competitive advantage in an increasingly connected world. By addressing language disparities and cultural considerations, organizations can create more inclusive digital environments that truly serve diverse global audiences.

Resources:
https://toppandigital.com/us/blog-usa/why-the-most-popular-world-languages-arent-reflected-online/
https://poeditor.com/blog/localization-problems/