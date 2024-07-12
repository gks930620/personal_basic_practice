package builder;

import java.util.Objects;

public class NewyorkPizza extends  Pizza{
    public enum Size {SAMLL,MEDIUM, LARGE};
    private final Size size;
    public static class Builder extends  Pizza.Builder{
        private final Size size;
        public Builder(Size size){
            this.size= Objects.requireNonNull(size);
        }
        public NewyorkPizza build(){
            return new NewyorkPizza(this);   //실제 객체생성은 여기서.
        }

        public Builder self(){return this;}

    }
    NewyorkPizza(Builder builder) {  //pizza는 토핑만, newyork은 size만
        super(builder);
        size=builder.size;
    }
}
