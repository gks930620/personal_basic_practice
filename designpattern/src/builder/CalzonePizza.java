package builder;

public class CalzonePizza extends Pizza{
        private final boolean sauceInside;
        public static class Builder extends Pizza.Builder{
            private boolean sauceInside=false;
            public Builder sauceInside(){
                sauceInside=true;
                return this;
            }
            public CalzonePizza build(){
                return new CalzonePizza(this);
            }
            protected  Builder self() {return this;}
        }

        private CalzonePizza(Builder builder){
            super(builder);
            sauceInside= builder.sauceInside;
        }
        public String toString(){
            return toppins.toString() + "sauceInsdie: " + sauceInside;
        }


}
