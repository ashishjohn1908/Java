package FinApps;

import BaseStats.inputmod;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:00:01
 * To change this template use File | Settings | File Templates.
 */
public class SelectPortfolio extends Portfolio {

    /**
     * Method main2
     * 1
     *
     * @param args 33
     *             3a  String[]
     */
    public static void main(String[] args) {
        int vals = 0;
        double data = 0.0;
        int numshares = 0;
        int numshare = 0;
        double price = 0.0;
        double initialvalue = 0.0;
        double expectret = 0.0;
        double endprice = 0.0;
        String name = " ";
        SelectPortfolio tester = new SelectPortfolio();

        System.out.println("CHOOSE TYPE of DATA INPUT");
        System.out.print("ENTER 1 For Raw Data(monthly %):  2  For % Expected Returns:  3 For Expected End Price:");
        int inputtype = inputmod.readInt();
        switch (inputtype) {
            case 1:
                System.out.println("Enter the NUMBER of securities to be processed");
                int numelements = inputmod.readInt();
                tester.insertnumsec(numelements);
                System.out.println("Enter the NUMBER of monthly returns to be processed for all  ");
                vals = inputmod.readInt();
                tester.datasize(vals);
                for (int i = 0; i < numelements; i++) {
                    System.out.println("Enter the NAME of the securiy  to be processed");
                    name = inputmod.readString();
                    tester.insertstring(name);//adds new securities to the list//
                    tester.offsetsize(1);//default value.. can accommodate a series of other non-data headers//

                    for (int j = 0; j < vals; j++) {
                        System.out.println("Enter the EXPECTED Monthly % Return for the security  " + name);
                        data = inputmod.readDouble();
                        tester.insertdata(data);//adds data to the list//
                    }

                }


                //tester.retanalysis();
                tester.propanalysis();
                break;

            case 2:
                System.out.println("Enter the number of securities to be processed");
                int nums = inputmod.readInt();
                for (int i = 0; i < nums; i++) {
                    System.out.println("Enter the NAME of securiy to be processed");
                    name = inputmod.readString();
                    System.out.println("Enter the NUMBER of issues purchased for  " + name);
                    numshares = inputmod.readInt();
                    System.out.print("Enter the INITIAL PRICE of securities for  " + name);
                    initialvalue = inputmod.readDouble();
                    System.out.println("Enter the EXPECTED % RETURN for the security  " + name);
                    expectret = inputmod.readDouble();
                    tester.folioreturns(name, numshares, initialvalue, expectret);//Adds data to the list//
                }

                tester.retInitprice();
                break;
            case 3:
                System.out.println("Enter the NUMBER of securities to be processed");
                int numelem = inputmod.readInt();
                for (int i = 0; i < numelem; i++) {
                    System.out.println("Enter the NAME of securiy to be processed");
                    name = inputmod.readString();
                    System.out.println("Enter the NUMBER of issues purchased for  " + name);
                    numshare = inputmod.readInt();
                    System.out.print("Enter the INITIAL PRICE of securities for  " + name);
                    initialvalue = inputmod.readDouble();
                    System.out.print("Enter the EXPECTED END PRICE of securities for  " + name);
                    endprice = inputmod.readDouble();
                    tester.folioendvals(name, numshare, initialvalue, endprice);//Adds data to the list//
                }


                tester.retendvals();

                break;
            default:
                System.out.println("Enter the type of securities !!!!!!!!!  ");
	}


	}

	
}


