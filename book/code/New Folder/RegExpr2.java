//listing 7
// Use find() to find a subsequence. 
import java.util.regex.*; 
 
class RegExpr2 { 
  public static void main(String args[]) { 
    Pattern pat = Pattern.compile("Java"); 
    Matcher mat = pat.matcher("Java SE 6"); 
 
    System.out.println("Looking for Java in Java SE 6."); 
 
    if(mat.find()) System.out.println("subsequence found"); 
    else System.out.println("No Match"); 
  } 
}