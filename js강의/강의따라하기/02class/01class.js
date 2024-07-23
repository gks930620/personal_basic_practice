// class IdolModel{
//     name ;
//     year ;
//     constructor(name,year){
//         this.name=name;
//         this.year=year;
//     }
// }

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

}
const yuJin=new IdolModel('안유진',2003);
const gaeul=new IdolModel('김가을',2002);
console.log(yuJin);
console.log(gaeul);
console.log(yuJin.sayName() );






