class Date {
    int dd;
    int mm;
    int yyyy;

    // Constructor (method)
    public Date(int day, int month, int year) {
        this.dd = day;
        this.mm = month;
        this.yyyy = year;
    }

}

class DemoDate {
    static boolean isEqual(Date d1, Date d2) {
        return (d1.dd == d2.dd &&
                d1.mm == d2.mm &
                        d1.yyyy == d2.yyyy);
    }

    public static void main(String[] args) {
//Date day=new Date(23,12,1971);
//System.out.println("Date:"+day.dd+"/"+day.mm+"/"+day.yyyy);	

        Date day1 = new Date(23, 12, 1971);
        Date day2 = day1; // beware not copying here: shallow copying
        Date day3 = new Date(23, 12, 1971);
        System.out.println(isEqual(day1, day3));
        System.out.println(day1);
        System.out.println(day2);
        System.out.println(day3);
    }
}
