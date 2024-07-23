/**
 * this 
 * 
 * JS는 Lexical Scope를 사용하기 때문에 함수의 상위스코프가 
 * 정의 시점에 평가된다.
 *
 * *** 하지만 this키워드는 바인딩이 객체가 생성되는 시점에 결정
 */

const testFunction= function(){return this;}; //함수자체

console.log(testFunction()==global);



const yuJin= {
    name : '안유진'
    ,year : 2003
    ,sayHello : function(){
        return `안녕하세요. ${this.name} 입니다`;
    }
    ,

}


console.log(yuJin.sayHello());
function Person(name,year){
    this.name=name;
    this.year=year;
    this.sayHello = function(){
        return `안녕하세요 저는 ${this.name}입니다`;
    }
}

const yuJin2 = new Person('안유진' , 2003);
console.log(yuJin2.sayHello());

Person.prototype.dance= function(){
    //return `${this.name}이 춤을 춥니다`;  상위클래스에 this를 해도 자동으로 실행하는 객체가 this가 된다
    function dance2(){
        return `${this.name}이 춤을 춥니다`;   //다른곳에는 this가 안됨
    }
    return dance2();
}

console.log(yuJin2.dance());  

/**
 * this 키워드가 어떤걸 가르키냐는 세가지만 기억하면 된다.
 * 1. 일반 함수 호출할 땐 this가 최상위 객체(global or window)를 가리킨다.
 * 2. 메서드로 호출할 땐 호출된 객체를 가리킨다.
 * 3. new 키워드를 사용해서 객체를 생성했을 땐 객체를 가리킨다.
 * 
 */


/**
 *  1) apply()
 *  2) call()
 *  3) bind()
 */

function returnName(){
    return this.name;
}

console.log(returnName());

const yuJin3={
    name : '안유진',

}

console.log(returnName.apply(yuJin3));
console.log(returnName.call(yuJin3));

/**
 *  1) call : 컴마를 기반으로 아규먼트 순서대로 
 *  2) apply : 아규먼트를 리스트로 입력
 * 
 */

function multiply(x,y,z){
    return `${this.name} / 결과값 : ${x*y*z}`;
}
console.log( multiply.call(yuJin3,3,4,5));
console.log( multiply.apply(yuJin3,[3,4,5]));   //첫번쨰는 실행대상 객체 

/**
 *  bind() 바인딩만 하고 나중에 실행
 */

const laterFunc= multiply.bind(yuJin3,3,4,5);
console.log(laterFunc);
console.log(laterFunc());







