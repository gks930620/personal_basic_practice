/**
 * Inheriatnce
 */

class IdolModel{
    name;
    year;
    constructor(name,year){
        this.name=name;
        this.year=year;
    }
}
class FemaleIdolModel extends IdolModel{
    dance(){
        return `여자아이돌이 섹시춤을 춥니다`
    }
}

class MaleIdolModel extends IdolModel{
    sing(){
        return `남자아이돌이 노래를 부릅니다`;
    }
}


 const yuJin= new FemaleIdolModel("안유진", 2003);
console.log(yuJin);


const jiMin= new MaleIdolModel('지민', 1995);
console.log(jiMin);

console.log(yuJin.dance());
console.log(jiMin.sing());

console.log(yuJin instanceof IdolModel); //하위클래스는 상위클래스의 객체이다. 반대는 성립x






