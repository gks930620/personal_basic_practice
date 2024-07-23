/**
 * Prototype
 */

const testObj={};  //객체 선언

//__proto__ 모든 객체에 존재하는 프로퍼티
//상위클래스에 해당되는 값  
console.log(testObj.__proto__);

function IdolModel(name,year){
    this.name=name;
    this.year=year;
}

console.log(IdolModel.prototype);
console.dir(IdolModel.prototype,{
    showHidden : true
});


//circular reference 
console.log(IdolModel.prototype.constructor===IdolModel); //같은 메모리주소 참조
console.log(IdolModel.prototype.constructor.prototype===IdolModel.prototype);

const yuJin= new IdolModel('안유진' , 2003);
console.log(yuJin.__proto__);
console.log(yuJin.__proto__===IdolModel.prototype); //강의 8분 30초부터 참고 그림으로보자
console.log(testObj.__proto__ === Object.prototype);


//강의 그림 꼭 이해하고 외워
console.log(IdolModel.__proto__===Function.prototype);
//IdolModel의 부모는 Function이다
console.log(Function.prototype.__proto__===Object.prototype)
console.log(IdolModel.prototype.__proto__===Object.prototype);

console.log(yuJin.toString());


function IdolModel2(name,year){
    this.name=name;
    this.year=year;

    this.sayHello=function (){
        return `${this.name}이 인사를합니다.`;
    }
}

const yuJin2= new IdolModel2('안유진',2003);
const wonYong2= new IdolModel2('장원영',2004);
console.log(yuJin2.sayHello());
console.log(wonYong2.sayHello());
console.log(yuJin2.sayHello===wonYong2.sayHello);   //this.sayHello로 만들었음 
console.log(yuJin2.hasOwnProperty('sayHello'));  //sayHello는 상속받은게 아니라  고유의 것 


function IdolModel3(name,year){
    this.name=name;
    this.year=year;

   
}
IdolModel3.prototype.sayHello=function(){
    return `${this.name}이 인사를합니다.`;
}

const yuJin3= new IdolModel3('안유진',2003);
const wonYong3= new IdolModel3('장원영',2004);
console.log(yuJin3.sayHello===wonYong3.sayHello);  
console.log(yuJin3.hasOwnProperty('sayHello'));  //sayHello는 상속받은게 아니라  고유의 것 


IdolModel3.sayStaticHello= function(){
    return "안녕하세요 저는 static method 입니다";
}

console.log(IdolModel3.sayStaticHello());


/**
 * Overriding
 */

function IdolModel4(name,year){
    this.name=name;
    this.year=year;
    this.sayHello=function(){
        return "안녕하세요 나는 인스턴스 메소드";
    }
}
IdolModel4.prototype.sayHello=function(){
    return "나는 프로토타입메서드";
}
const yuJin4 = new IdolModel4('안유진' , 2003);
console.log(yuJin4.__proto__.sayHello());  //직접 실행
console.log(yuJin4.sayHello());  //상위클래스의 sayHello 실행  오버라이딩안되어있다면 똑같이 상위클래스의 sayHello 실행


/**
 * getPropertytypeOf, set..
 * 
 * 인스턴스의 __proto__ 변경 vs 함수의 prototype 변경
 */

function IdolModel(name,year){
    this.name=name;
    this.year=year;
}
IdolModel.prototype.sayHello=function(){
    return `${this.name}이 인사를 합니다 `;
}

function FemaleIdolModel(name,year){
    this.name=name;
    this.year=year;
    this.dance=function(){
        return `${this.name}이 춤을 춥니다.`;
    }
}

const gaEul= new IdolModel('가을', 2004);
const ray= new FemaleIdolModel('레이', 2004);
console.log(gaEul.__proto__);
console.log(gaEul.__proto__===IdolModel.prototype);
console.log(Object.getPrototypeOf(gaEul)==IdolModel.prototype );

console.log(gaEul.sayHello());

console.log(ray.dance());
console.log(Object.getPrototypeOf(ray)==FemaleIdolModel.prototype );
//console.log(ray.sayHello());  안됨
Object.setPrototypeOf(ray,IdolModel.prototype);  //상속하는 대상을 바꿔버림
console.log(ray.sayHello());  
console.log(ray.dance());  //여전히 실행가능  why? 

console.log(ray.constructor);
console.log(ray.constructor===FemaleIdolModel);          //female로 만들었지만, protoType변경해서 Idol이 나옴
console.log(ray.constructor===IdolModel);
console.log(Object.getPrototypeOf(ray)===IdolModel.prototype);

//객체의 프로토 타입 변경처럼
//함수의 프로토 타입도 직접 변경 가능

FemaleIdolModel.prototype=IdolModel.prototype;
const eSeo= new FemaleIdolModel('이서',2007);
console.log(Object.getPrototypeOf(eSeo) === FemaleIdolModel.prototype);
console.log(Object.getPrototypeOf(eSeo) ===IdolModel.prototype);
console.log(IdolModel.prototype===FemaleIdolModel.prototype);
















