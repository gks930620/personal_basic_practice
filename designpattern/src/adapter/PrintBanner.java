package adapter;

public class PrintBanner implements Print { //adapter
    private Banner banner;
    public PrintBanner(String string) {
        banner=new Banner(string);
    }
    @Override
    public void printWeek() {
        banner.showWithParen();
    }
    @Override
    public void printStrong() {
        banner.showWithAster();
    }   //Adapter역할


}
