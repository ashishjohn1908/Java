class DateCh5 {
    int dd;
    int mm;
    int yyyy;

    static final String[] months = {
            "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October",
            "November", "December"
    };

    // Constructor
    DateCh5(int day, int month, int year) {
        this.dd = day;
        this.mm = month;
        this.yyyy = year;
    }

}

class DemoDateCh5 {

    static void Display(Date day) {
        System.out.println("Date:" + day.dd + "/" + day.mm + "/" + day.yyyy);
    }

    public static void main(String[] args) {


        Date day1 = new Date(23, 12, 1971);
        Display(day1);
        day1.mm = 6;
        Display(day1);
        System.out.println(day1);
    }
}