package builder;

public class BuilderMain {
    public static void main(String[] args) {
        Pizza pizza= new NewyorkPizza.Builder(NewyorkPizza.Size.SAMLL).addTopping(Pizza.Topping.PEPPER)
                .addTopping(Pizza.Topping.ONION).build(); //실제론 뉴욕피자
        Pizza pizza2=new CalzonePizza.Builder().addTopping(Pizza.Topping.HAM).addTopping(Pizza.Topping.PEPPER)
                .sauceInside().build();;  //sauceInside 는 칼조네에서만 필요한거  //sauceInside는 true로 바뀐 calzonePizza가 생성

        System.out.println(pizza);
        System.out.println(pizza2);
        

    }
}
