package plamen;

/**
 * Created with IntelliJ IDEA.
 * User: plamen
 * Date: 12/03/13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */

class AccountException extends Exception{
    AccountException(){
        super("Account balance cannot be negative!");
    }
    protected AccountException(String message){
        super(message);
    }
}

class CurrentAccountException extends AccountException{
    CurrentAccountException(){
        super("Current Account is overdrawn");
    }
    protected CurrentAccountException(String message){
        super(message);
    }
}

class JointCurrentAccountException extends CurrentAccountException{
    JointCurrentAccountException(){
        super("Joint Account overcharges occur");
    }

    protected JointCurrentAccountException(String massage){
        super(massage);
    }
}

public class JavaExceptionsHandler {

    static double getAccountBalance(Double withdraw) throws AccountException{
        double deposit = 100.0;
        double balance = deposit - withdraw;
        if (balance < Double.valueOf("0"))
            throw new AccountException();
        return balance;
    }

    static double getCurrentAccountBalance(Double withdraw) throws CurrentAccountException{
        double deposit = 100.0;
        double balance = deposit - withdraw;
        if (balance < Double.valueOf("0"))
            throw new CurrentAccountException();
        return balance;
    }

    static double getJointCurrentAccountBalance(Double withdraw) throws JointCurrentAccountException{
        double deposit = 100.0;
        double balance = deposit - withdraw;
        if (balance < Double.valueOf("0"))
            throw new JointCurrentAccountException("Joint Current Acount Balance negative!");
        return balance;
    }

    public static void main(String[] args) {
        try{
            double accountBalance = getAccountBalance(Double.valueOf("100"));
            double currentAccountBalance = getCurrentAccountBalance(Double.valueOf("34"));
            double jointCurrentAccountBalance = getJointCurrentAccountBalance(Double.valueOf("104"));
            System.out.println("The account balance is: " + accountBalance + " : " + currentAccountBalance + " : " + jointCurrentAccountBalance);
        } // if you uncomment the catch part will get a compiler error that exceptions has already been caught!
        //catch(Exception e)  {
        //    System.out.println(e.getMessage());
        //}
        catch (AccountException e) {
            System.out.println(e.getMessage());
        }
    }
}
