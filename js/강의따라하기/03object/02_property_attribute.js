
/**
 * 액세서 프로퍼티 - 다른값을 설정할 때 호출되는 함수로 구성된 프로퍼티 get/set 등 
 * 데이터 프로퍼티 - 키와 값으로 형성된 실절적ㄱ값을 가지고있는 프로퍼티 get/set빼고 대부분
 * 
 */

const yuJin={name : '안유진' , year : 2003};

console.log(Object.getOwnPropertyDescriptor(yuJin,'name'));
console.log(Object.getOwnPropertyDescriptor(yuJin,'year'));

//value,writeable, enumberable, configurable 다 알아야함.

console.log(Object.getOwnPropertyDescriptors(yuJin));

console.log("-------------------------------------")

const yuJin2={
    name:'안유진', year : 2003,
    get age(){
        return new Date().getFullYear() - this.year;
    },
    set age(age){
        this.year= new Date().getFullYear-age;
    },
}
console.log(yuJin2);

console.log(yuJin2.age);


yuJin2.height=173;















