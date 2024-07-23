/**
 * 상위 함수보다 하위 함수가 더 오래 살아있는 경우를 closure라고 한다
 * 
 */

function getNumber(){
    var number=5;
    function innerGetNumber(){
        return number;
    }
    return innterGetNumber();
}


//console.log(getNumber());  

function getNumber(){
    var number=5;

    function innerGetNumber(){
        return number;
    }
    return innerGetNumber;  //실행 x 함수 자체만 반환 
}

const runner= getNumber();  //getNumber는 콜스택에서 사라짐

console.log(runner); 
console.log(runner()); // inner만 그대로 실행가능




/**
 * closure 예시 
 * 
 * 1 데이터캐싱
 */

function cacheFunction( ){
    var number =10*10 ;  // 한번만 하면 됨  
    function innerCacheFunction(newNumb){
        return number*newNumb;
    }
    return innerCacheFunction;
}
const runner2= cacheFunction();
console.log(runner2(10)); 
console.log(runner2(20)); 


function cacheFunction2(){
    var number=99;

    function increament(){
        number++;
        return number;
    }
    return increament;
}
const runner3= cacheFunction2();
console.log(runner3());      //함수에 number=99에 접근불가한 것을 함수내에서 접근가능하게

/**
 * 정보은닉
 */

function Idol(name, year){
    this.name=name;
    var _year=year;

    this.sayNameAndYear=function(){
        return `안녕하세요 저는 ${this.name} , ${_year}`;
    }
}

const yuJin= new Idol('안유진' , 2003);
console.log(yuJin.sayNameAndYear()); //잘 나옴
console.log(yuJin._year); //안 나옴






