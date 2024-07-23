class IdolModel{ //name,year  필드가 없어도 괜찮
    //그래도 정의하는게 더 좋은 코드가 아닐까..
    name;
    year;
    constructor(name,year){
        this.name=name;
        this.year=year;
    }
    sayName(){
        return `안녕 난 ${this.name}이야`;
    }// js는 this를 꼭 넣어줘야..

    get nameAndYear(){
        return `${this.name} -${this.year}`;
    }
    set name(name){
        this.name=name;
    }
}
const yuJin=new IdolModel('안유진', 2003);
console.log(yuJin.nameAndYear);   //get키워드를 붙인 함수에서는 함수처럼 실행하지않음

yuJin.name='레이';
console.log(yuJin.nameAndYear);

class IdolModel2{
    #name; //private
    year;
    constructor(name,year){
        this.#name=name;
        this.year=year;
    }
    get name(){
        return this.#name;
    }

    set name(name){
        this.#name=name;
    }
}
const yuJin2= new IdolModel2('안유진',2003);
console.log(yuJin2);
console.log(yuJin2.name);

yuJin2.name='레이';
console.log(yuJin2.name);






